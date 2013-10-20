package rebelkeithy.mods.metallurgy.integration.appeng;

import rebelkeithy.mods.metallurgy.api.plugin.IPlugin;

import com.google.common.collect.ImmutableList;

import cpw.mods.fml.common.Loader;

public class MetallurgyPlugin implements IPlugin
{
    @Override
    public Iterable<?> getForgeSubscribers()
    {
        return ImmutableList.of(new CrusherRecipeHandler());
    }

    @Override
    public boolean isActive()
    {
        return Loader.isModLoaded("AppliedEnergistics");
    }

    @Override
    public String toString()
    {
        return "Native Metallurgy Applied Energistics Support Plugin";
    }
}
