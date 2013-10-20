package rebelkeithy.mods.metallurgy.integration;

import java.util.logging.Logger;

import net.minecraft.item.Item;
import rebelkeithy.mods.metallurgy.core.metalsets.OreInfo;
import rebelkeithy.mods.metallurgy.metals.MetallurgyMetals;
import dan200.turtle.api.ITurtleUpgrade;
import dan200.turtle.api.TurtleAPI;

public class ComputerCraftIntegration
{
    public static void addTurtles()
    {
        OreInfo info;

        info = MetallurgyMetals.fantasySet.getOreInfo("Orichalcum");
        if (info.isEnabled() && info.getPickaxe() != null)
        {
            createTurtle(101, "Orichalcum Mining", info.getPickaxe());
        }
        if (info.isEnabled() && info.getSword() != null)
        {
            createTurtle(111, "Orichalcum Melee", info.getSword());
        }

        info = MetallurgyMetals.fantasySet.getOreInfo("Adamantine");
        if (info.isEnabled() && info.getPickaxe() != null)
        {
            createTurtle(102, "Adamantine Mining", info.getPickaxe());
        }
        if (info.isEnabled() && info.getSword() != null)
        {
            createTurtle(112, "Adamantine Melee", info.getSword());
        }

        info = MetallurgyMetals.fantasySet.getOreInfo("Celenegil");
        if (info.isEnabled() && info.getPickaxe() != null)
        {
            createTurtle(103, "Celenegil Mining", info.getPickaxe());
        }
        if (info.isEnabled() && info.getSword() != null)
        {
            createTurtle(113, "Celenegil Melee", info.getSword());
        }

        info = MetallurgyMetals.fantasySet.getOreInfo("Atlarus");
        if (info.isEnabled() && info.getPickaxe() != null)
        {
            createTurtle(104, "Atlarus Mining", info.getPickaxe());
        }
        if (info.isEnabled() && info.getSword() != null)
        {
            createTurtle(114, "Atlarus Melee", info.getSword());
        }

        info = MetallurgyMetals.fantasySet.getOreInfo("Tartarite");
        if (info.isEnabled() && info.getPickaxe() != null)
        {
            createTurtle(105, "Tartarite Mining", info.getPickaxe());
        }
        if (info.isEnabled() && info.getSword() != null)
        {
            createTurtle(115, "Tartarite Melee", info.getSword());
        }

        info = MetallurgyMetals.netherSet.getOreInfo("Inolashite");
        if (info.isEnabled() && info.getPickaxe() != null)
        {
            createTurtle(106, "Inolashite Mining", info.getPickaxe());
        }
        if (info.isEnabled() && info.getSword() != null)
        {
            createTurtle(116, "Inolashite Melee", info.getSword());
        }

        info = MetallurgyMetals.netherSet.getOreInfo("Kalendrite");
        if (info.isEnabled() && info.getPickaxe() != null)
        {
            createTurtle(107, "Kalendrite Mining", info.getPickaxe());
        }
        if (info.isEnabled() && info.getSword() != null)
        {
            createTurtle(117, "Kalendrite Melee", info.getSword());
        }

        info = MetallurgyMetals.netherSet.getOreInfo("Amordrine");
        if (info.isEnabled() && info.getPickaxe() != null)
        {
            createTurtle(108, "Amordrine Mining", info.getPickaxe());
        }
        if (info.isEnabled() && info.getSword() != null)
        {
            createTurtle(118, "Amordrine Melee", info.getSword());
        }

        info = MetallurgyMetals.netherSet.getOreInfo("Vulcanite");
        if (info.isEnabled() && info.getPickaxe() != null)
        {
            createTurtle(109, "Vulcanite Mining", info.getPickaxe());
        }
        if (info.isEnabled() && info.getSword() != null)
        {
            createTurtle(119, "Vulcanite Melee", info.getSword());
        }

        info = MetallurgyMetals.netherSet.getOreInfo("Sanguinite");
        if (info.isEnabled() && info.getPickaxe() != null)
        {
            createTurtle(110, "Sanguinite Mining", info.getPickaxe());
        }
        if (info.isEnabled() && info.getSword() != null)
        {
            createTurtle(120, "Sanguinite Melee", info.getSword());
        }
    }

    public static void createTurtle(int id, String name, Item tool)
    {

        ITurtleUpgrade toolTurtle = new ComputerCraftTurtle(id, name, tool);
        TurtleAPI.registerUpgrade(toolTurtle);

    }

    public static void init(Logger log)
    {
        try
        {
            Class.forName("dan200.turtle.api.TurtleAPI");

            log.info("Adding ComputerCraft Turtles");
            addTurtles();
        } catch (final Exception e)
        {
        	 log.info("Skipping ComputerCraft Turtles");
        }
    }
}
