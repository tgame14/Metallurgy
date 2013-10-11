package rebelkeithy.mods.metallurgy.core.metalsets;

import java.util.List;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ItemMetallurgyArmor extends ItemArmor
{

    public String textureFile;
	private EnumArmorMaterial material;

    public ItemMetallurgyArmor(int par1, EnumArmorMaterial par2EnumArmorMaterial, int par3, int par4)
    {
        super(par1, par2EnumArmorMaterial, par3, par4);
        this.material = par2EnumArmorMaterial;
    }
    
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean advancedItemTooltips) {
		list.add("Material: " + this.material.toString());
		
		if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))
		{
			list.add("Absorption: " + this.material.getDamageReductionAmount(this.armorType));
			list.add("Enchantability: " + this.material.getEnchantability());
			
			if(!advancedItemTooltips)
			{
				list.add("Durability: " + (itemStack.getMaxDamage() - itemStack.getItemDamage()));
			}
		}
	}

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, int layer)
    {
        return "metallurgy:armor/" + textureFile + ".png";
    }

    public ItemMetallurgyArmor setTextureFile(String texture)
    {
        textureFile = texture;
        return this;
    }

    @Override
    public ItemMetallurgyArmor setTextureName(String par1Str)
    {
        super.setTextureName(par1Str);
        return this;
    }

}
