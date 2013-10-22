package rebelkeithy.mods.metallurgy.integration.computercraft;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import dan200.computer.api.IHostedPeripheral;
import dan200.turtle.api.ITurtleAccess;
import dan200.turtle.api.ITurtleUpgrade;
import dan200.turtle.api.TurtleSide;
import dan200.turtle.api.TurtleUpgradeType;
import dan200.turtle.api.TurtleVerb;

public class ComputerCraftTurtle implements ITurtleUpgrade
{
    private final int id;
    private final String name;
    private final Item tool;

    ComputerCraftTurtle(final int id, final String name, final Item tool)
    {
        this.id = id;
        this.name = name;
        this.tool = tool;
    }

    @Override
    public IHostedPeripheral createPeripheral(final ITurtleAccess turtle, final TurtleSide side)
    {
        return null;
    }

    @Override
    public String getAdjective()
    {
        return name;
    }

    @Override
    public ItemStack getCraftingItem()
    {
        return new ItemStack(tool);
    }

    @Override
    public Icon getIcon(final ITurtleAccess turtle, final TurtleSide side)
    {
        return tool.getIconFromDamage(0);
    }

    @Override
    public TurtleUpgradeType getType()
    {
        return TurtleUpgradeType.Tool;
    }

    @Override
    public int getUpgradeID()
    {
        return id;
    }

    @Override
    public boolean isSecret()
    {
        return false;
    }

    @Override
    public boolean useTool(final ITurtleAccess turtle, final TurtleSide side,
            final TurtleVerb verb, final int direction)
    {
        return false;
    }
}
