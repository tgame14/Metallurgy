package rebelkeithy.mods.metallurgy.core;

import java.io.Closeable;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.oredict.OreDictionary;
import rebelkeithy.mods.metallurgy.core.metalsets.ItemMetallurgy;

import com.google.common.base.Splitter;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;
import com.google.common.io.CharStreams;
import com.google.common.io.InputSupplier;
import com.google.common.io.LineProcessor;

import cpw.mods.fml.common.registry.LanguageRegistry;

public class MetalInfoDatabase
{
    private static Map<String, String> nameValuePairsFromCSVLine(final Iterable<String> fields,
            final List<String> header)
    {
        final Map<String, String> data = Maps.newHashMap();

        int i = 0;
        for (String field : fields)
        {
            if (field.isEmpty() || field.equals("-")) field = "0";
            data.put(header.get(i++), field);
        }
        return data;
    }

    private final Table<String, String, Map<String, String>> metalSets = HashBasedTable.create();
    private final Map<String, Item> items = Maps.newHashMap();
    private Map<String, String> oreDictNames = Collections.emptyMap();

    MetalInfoDatabase()
    {
        // Limit visibility -- only same package (ie. MetallurgyCore) can create
    }

    public Map<String, Map<String, String>> getDataForSet(final String name)
    {
        return ImmutableMap.copyOf(metalSets.row(name));
    }

    public ItemStack getItem(final String itemName)
    {
        if (items == null || !items.containsKey(itemName)) return null;

        return new ItemStack(items.get(itemName));
    }

    public <R extends Readable & Closeable> void loadItemData(final Configuration config,
            final InputSupplier<R> input, final CreativeTabs tab) throws IOException
    {
        final List<String> lines = read(input);
        final List<String> header = Lists.newArrayList();
        for (final String line : lines)
        {
            final Iterable<String> fields = Splitter.on(',').split(line);
            if (header.isEmpty()) for (final String field : fields)
                header.add(field);
            else
                parseItemDataLine(fields, header, config, tab);
        }
    }

    public <R extends Readable & Closeable> void loadMetalSet(final InputSupplier<R> input)
            throws IOException
    {
        final List<String> lines = read(input);
        final List<String> header = Lists.newArrayList();
        for (final String line : lines)
        {
            final Iterable<String> fields = Splitter.on(',').split(line);
            if (header.isEmpty()) for (final String field : fields)
                header.add(field);
            else
                parseMetalSetLine(fields, header);
        }
    }

    private void parseItemDataLine(final Iterable<String> fields, final List<String> header,
            final Configuration config, final CreativeTabs tab)
    {
        final Map<String, String> data = nameValuePairsFromCSVLine(fields, header);
        int id = Integer.parseInt(data.get("Item ID"));

        final String itemName = data.get("Item Name");
        final String setName = data.get("Set Name");

        id = config.get("Item IDs", itemName, id).getInt();
        final Item item =
                new ItemMetallurgy(id).setTextureName("Metallurgy:" + setName + "/" + itemName)
                        .setUnlocalizedName("Metallurgy:" + setName + "/" + itemName)
                        .setCreativeTab(tab);
        LanguageRegistry.addName(item, itemName);

        items.put(itemName, item);
        if (!data.get("Ore Dictionary Name").equals("0"))
        {
            if (oreDictNames.isEmpty()) oreDictNames = Maps.newHashMap();
            oreDictNames.put(itemName, data.get("Ore Dictionary Name"));
        }
    }

    private void parseMetalSetLine(final Iterable<String> fields, final List<String> header)
    {
        final Map<String, String> data = nameValuePairsFromCSVLine(fields, header);
        metalSets.put(data.get("Metal Set"), data.get("Name"), data);
    }

    private <R extends Readable & Closeable> List<String> read(final InputSupplier<R> input)
            throws IOException
    {
        return CharStreams.readLines(input, new LineProcessor<List<String>>()
        {
            final List<String> result = Lists.newArrayList();

            @Override
            public List<String> getResult()
            {
                return result;
            }

            @Override
            public boolean processLine(final String line)
            {
                result.add(line);
                return true;
            }
        });
    }

    void registerItemsWithOreDict()
    {
        for (final Map.Entry<String, String> entry : oreDictNames.entrySet())
            OreDictionary.registerOre(entry.getValue(), items.get(entry.getKey()));
        oreDictNames = Collections.emptyMap();
    }
}
