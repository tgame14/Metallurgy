package rebelkeithy.mods.metallurgy.integration.computercraft;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Logger;

import net.minecraft.item.Item;
import net.minecraftforge.event.ForgeSubscribe;
import rebelkeithy.mods.metallurgy.api.IOreInfo;
import rebelkeithy.mods.metallurgy.api.plugin.event.PluginPostInitEvent;
import rebelkeithy.mods.metallurgy.core.MetallurgyCore;
import rebelkeithy.mods.metallurgy.core.metalsets.MetalSet;
import rebelkeithy.mods.metallurgy.core.plugin.event.NativePluginStartupEvent;
import dan200.turtle.api.ITurtleUpgrade;

public class EventHandler
{
    private int miningTurtleID = 101;
    private int meleeTurtleID = 111;
    private ComputerCraftAPI api = null;
    private Logger logger = null;

    private void createMeleeTurtle(final String name, final Item sword)
    {
        createTurtle(meleeTurtleID++, name + "Melee", sword);
    }

    private void createMiningTurtle(final String name, final Item pickaxe)
    {
        createTurtle(miningTurtleID++, name + "Mining", pickaxe);
    }

    private void createTurtle(final int id, final String name, final Item tool)
    {
        final ITurtleUpgrade turtle = new ComputerCraftTurtle(id, name, tool);
        try
        {
            api.registerUpgrade(turtle);
            logger.fine(String.format("Added %s turtle to ComputerCraft.", name.toLowerCase()));
        }
        catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
        {
            logger.fine(String.format(
                    "Could not add %s turtle to ComputerCraft. Problem with API.",
                    name.toLowerCase()));
        }
    }

    @ForgeSubscribe
    public void init(final PluginPostInitEvent event)
    {
        // All of this is in this type of handler because it runs *after* the native mods.

        if (api == null) try
        {
            api = new ComputerCraftAPI();
        }
        catch (ClassNotFoundException | NoSuchMethodException | SecurityException e)
        {
            logger.warning("Cannot add ComputerCraft support. API has changed or is corrupted.");
            api = null;
        }

        if (api != null) for (final MetalSet metalSets : MetallurgyCore.getMetalSetList())
            for (final IOreInfo metalSet : metalSets.getOreList().values())
                if (metalSet.isEnabled())
                {
                    final Item pickaxe = metalSet.getPickaxe();
                    if (pickaxe != null) createMiningTurtle(metalSet.getName(), pickaxe);
                    final Item sword = metalSet.getSword();
                    if (sword != null) createMeleeTurtle(metalSet.getName(), sword);
                }
    }

    @ForgeSubscribe
    public void preInit(final NativePluginStartupEvent.Pre event)
    {
        logger = event.getMetallurgyLog();
    }
}
