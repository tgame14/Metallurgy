package rebelkeithy.mods.metallurgy.core.metalsets;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.Configuration;
import rebelkeithy.mods.metallurgy.api.IMetalSet;
import rebelkeithy.mods.metallurgy.api.IOreInfo;
import rebelkeithy.mods.metallurgy.core.MetalInfoDatabase;
import rebelkeithy.mods.metallurgy.core.MetallurgyCore;

public class MetalSet implements IMetalSet
{
    private final String setName;
    private final Map<String, IOreInfo> metals;
    private Configuration config;

    public MetalSet(String setName, Map<String, Map<String, String>> baseData, CreativeTabs tab, final MetalInfoDatabase dbMetal, File configDir)
    {
        this.setName = setName;

        metals = new HashMap<String, IOreInfo>();

        for (final Map<String, String> metalInfo : baseData.values())
        {
            metals.put(metalInfo.get("Name"), new OreInfo(metalInfo, tab, dbMetal));
        }

        MetallurgyCore.getMetalSetList().add(this);

        initConfig(configDir);
        init();
    }

    @Override
    public OreInfo getOreInfo(int meta)
    {
        for (final IOreInfo oreInfo : metals.values())
        {
            if (((OreInfo) oreInfo).getOreMeta() == meta)
            {
                return (OreInfo) oreInfo;
            }
        }
        return null;
    }

    @Override
    public OreInfo getOreInfo(String name)
    {
        if (metals.containsKey(name))
        {
            return (OreInfo) metals.get(name);
        }
        else
        {
            return OreInfo.EMPTY;
        }
    }

    @Override
    public Map<String, IOreInfo> getOreList()
    {
        return metals;
    }

    public void init()
    {
        for (final IOreInfo oreInfo : metals.values())
        {
            ((OreInfo) oreInfo).init();
        }
    }

    public void initConfig(File configDir)
    {
        final File cfgFile = new File(configDir, "Metallurgy" + setName + ".cfg");
        config = new Configuration(cfgFile);

        for (final IOreInfo oreInfo : metals.values())
        {
            ((OreInfo) oreInfo).initConfig(config);
        }

        if (config.hasChanged())
            config.save();
    }

    public void load()
    {
        for (final IOreInfo oreInfo : metals.values())
        {
            ((OreInfo) oreInfo).load();
        }
    }

    public void registerNames()
    {
        for (final IOreInfo oreInfo : metals.values())
        {
            ((OreInfo) oreInfo).registerNames();
        }
    }

}
