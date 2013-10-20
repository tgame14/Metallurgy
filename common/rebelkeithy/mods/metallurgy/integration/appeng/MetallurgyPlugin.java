package rebelkeithy.mods.metallurgy.integration.appeng;

import net.minecraft.src.ModLoader;
import rebelkeithy.mods.metallurgy.api.plugin.IPlugin;

import com.google.common.collect.ImmutableList;

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
        return ModLoader.isModLoaded("AppliedEnergistics");
    }

    @Override
    public String toString()
    {
        return "Native Metallurgy Applied Energistics Support Plugin";
    }

}
