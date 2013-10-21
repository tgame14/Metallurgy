package rebelkeithy.mods.metallurgy.core.database;

import java.io.Closeable;
import java.io.IOException;
import java.util.Map;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;
import com.google.common.io.CharStreams;
import com.google.common.io.InputSupplier;
import com.google.common.io.LineProcessor;

public class MetalInfoDatabase
{
    private final Table<String, String, Map<String, String>> metalSets = HashBasedTable.create();
    private final Map<String, Item> items = Maps.newHashMap();

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
        read(input, new ItemLineProcessor(config, tab, items));
    }

    public <R extends Readable & Closeable> void loadMetalSet(final InputSupplier<R> input)
            throws IOException
    {
        read(input, new MetalSetLineProcessor(metalSets));
    }

    private <R extends Readable & Closeable, T> T read(final InputSupplier<R> input,
            final LineProcessor<T> lineProcessor) throws IOException
    {
        return CharStreams.readLines(input, lineProcessor);
    }
}
