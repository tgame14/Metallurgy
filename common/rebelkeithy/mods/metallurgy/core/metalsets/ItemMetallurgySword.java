package rebelkeithy.mods.metallurgy.core.metalsets;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class ItemMetallurgySword extends ItemSword
{
    private final List<ISwordHitListener> hlList = new ArrayList<ISwordHitListener>();
    String subText;
	private EnumToolMaterial material;

    public ItemMetallurgySword(int par1, EnumToolMaterial par2EnumToolMaterial)
    {
        super(par1, par2EnumToolMaterial);
        this.material = par2EnumToolMaterial;
    }

    public void addHitListener(ISwordHitListener hl)
    {
        hlList.add(hl);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer par2EntityPlayer, List list, boolean advancedItemTooltips)
    {
		list.add("Material: " + this.material.toString());
		
		if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))
		{
			list.add("Damage: " + this.material.getDamageVsEntity());

			list.add("Enchantability: " + this.material.getEnchantability());
			
			if(!advancedItemTooltips)
			{
				list.add("Durability: " + (itemStack.getMaxDamage() - itemStack.getItemDamage()));
			}
		}
    	
        if (subText != null)
        {
            for (final String string : subText.split("-"))
            {
                list.add("\u00A7" + string);
            }
        }
    }

    @Override
    public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase)
    {
        for (final ISwordHitListener hl : hlList)
        {
            hl.hitEntity(par1ItemStack, par2EntityLivingBase, par3EntityLivingBase);
        }

        return super.hitEntity(par1ItemStack, par2EntityLivingBase, par3EntityLivingBase);
    }

    public void setSubText(String text)
    {
        subText = text;
    }

    @Override
    public ItemMetallurgySword setTextureName(String par1Str)
    {
        super.setTextureName(par1Str);
        return this;
    }
}
