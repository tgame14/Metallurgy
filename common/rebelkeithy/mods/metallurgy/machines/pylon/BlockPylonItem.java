package rebelkeithy.mods.metallurgy.machines.pylon;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class BlockPylonItem extends ItemBlock
{
    public BlockPylonItem(int i)
    {
        super(i);
        setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int i)
    {
        return i;
    }

    @Override
    public String getUnlocalizedName(ItemStack itemstack)
    {
        String name = BlockPylon.names[itemstack.getItemDamage()] ;
        
        name = name.replaceAll("\\s", "");
        name = name.substring(0,1).toLowerCase() + name.substring(1);

        return getUnlocalizedName() + "." + name;
    }
}
