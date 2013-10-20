package rebelkeithy.mods.metallurgy.integration.treecapitator;

import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.ForgeSubscribe;
import rebelkeithy.mods.metallurgy.api.IOreInfo;
import rebelkeithy.mods.metallurgy.api.plugin.IPlugin;
import rebelkeithy.mods.metallurgy.api.plugin.event.PluginInitEvent;
import rebelkeithy.mods.metallurgy.core.MetallurgyCore;
import rebelkeithy.mods.metallurgy.core.metalsets.MetalSet;

import com.google.common.collect.ImmutableList;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLInterModComms;

public class MetallurgyTreeCapPlugin implements IPlugin
{
    @Override
    public Iterable<?> getForgeSubscribers()
    {
        return ImmutableList.of(this);
    }

    @ForgeSubscribe
    public void init(final PluginInitEvent event)
    {
        StringBuilder axeList = null;

        for (final MetalSet metalSets : MetallurgyCore.getMetalSetList())
            for (final IOreInfo metalSet : metalSets.getOreList().values())
            {
                final Item axe = metalSet.getAxe();
                if (metalSet.isEnabled() && axe != null)
                    if (axeList == null) axeList = new StringBuilder(String.valueOf(axe.itemID));
                    else
                    {
                        axeList.append("; ");
                        axeList.append(axe.itemID);
                    }
            }

        if (axeList != null && axeList.length() > 0)
        {

            final NBTTagCompound tpModCfg = new NBTTagCompound();
            tpModCfg.setString("modID", "Metallurgy3Base");
            tpModCfg.setString("axeIDList", axeList.toString());

            FMLInterModComms.sendMessage("TreeCapitator", "ThirdPartyModConfig", tpModCfg);
        }
    }

    @Override
    public boolean isActive()
    {
        return Loader.isModLoaded("TreeCapitator");
    }

    @Override
    public String toString()
    {
        return "Native Metallurgy Treecapitator Support Plugin";
    }
}
