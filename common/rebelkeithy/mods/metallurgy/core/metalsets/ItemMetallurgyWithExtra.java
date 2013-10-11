package rebelkeithy.mods.metallurgy.core.metalsets;

import java.util.List;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;

public class ItemMetallurgyWithExtra extends ItemMetallurgy {

	private EnumToolMaterial material;

	public ItemMetallurgyWithExtra(int id, EnumToolMaterial toolEnum) {
		super(id);
		this.material = toolEnum;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean advancedItemTooltips) {
		list.add("Material: " + this.material.toString());
		
		if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))
		{
			list.add("Mining Level: " + this.material.getHarvestLevel());
			list.add("Speed: " + this.material.getEfficiencyOnProperMaterial());
			list.add("Damage: " + this.material.getDamageVsEntity());
			list.add("Enchantability: " + this.material.getEnchantability());
			
			if(!advancedItemTooltips)
			{
				list.add("Durability: " + (itemStack.getMaxDamage() - itemStack.getItemDamage()));
			}
		}
	}
}
