package rebelkeithy.mods.metallurgy.integration.computercraft;

import rebelkeithy.mods.metallurgy.api.plugin.IPlugin;

import com.google.common.collect.ImmutableList;

import cpw.mods.fml.common.Loader;

public class MetallurgyPlugin implements IPlugin
{
    @Override
    public Iterable<?> getForgeSubscribers()
    {
        return ImmutableList.of(new EventHandler());
    }

    @Override
    public boolean isActive()
    {
        return Loader.isModLoaded("ComputerCraft");
    }

    @Override
    public String toString()
    {
        return "Native Metallurgy ComputerCraft Support Plugin";
    }
}
