package rebelkeithy.mods.metallurgy.metals;

import java.lang.reflect.Method;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import rebelkeithy.mods.metallurgy.core.metalsets.ISwordHitListener;

public class NetherSwordHitListener implements ISwordHitListener
{
    private static int slowness = 2;
    private static int regen = 10;
    private static int weakness = 18;
    private static int poison = 19;
    private static int wither = 20;

    @Override
    public boolean hitEntity(ItemStack itemstack, EntityLivingBase entityliving, EntityLivingBase player)
    {

        if (Math.random() < 0.7)
        {
            return false;
        }

        if (MetallurgyMetals.netherSet.getOreInfo("Ignatius").isEnabled() && itemstack.getItem().itemID == MetallurgyMetals.netherSet.getOreInfo("Ignatius").getSword().itemID)
        {
            entityliving.setFire(2);
        }
        else if (MetallurgyMetals.netherSet.getOreInfo("Shadow Iron").isEnabled()
                && itemstack.getItem().itemID == MetallurgyMetals.netherSet.getOreInfo("Shadow Iron").getSword().itemID)
        {
            entityliving.addPotionEffect(new PotionEffect(weakness, 80, 0));
        }
        else if (MetallurgyMetals.netherSet.getOreInfo("Vyroxeres").isEnabled() && itemstack.getItem().itemID == MetallurgyMetals.netherSet.getOreInfo("Vyroxeres").getSword().itemID)
        {
            entityliving.addPotionEffect(new PotionEffect(poison, 80, 0));
        }
        else if (MetallurgyMetals.netherSet.getOreInfo("Ceruclase").isEnabled() && itemstack.getItem().itemID == MetallurgyMetals.netherSet.getOreInfo("Ceruclase").getSword().itemID)
        {
            entityliving.addPotionEffect(new PotionEffect(slowness, 80, 0));
        }
        else if (MetallurgyMetals.netherSet.getOreInfo("Kalendrite").isEnabled() && itemstack.getItem().itemID == MetallurgyMetals.netherSet.getOreInfo("Kalendrite").getSword().itemID)
        {
            player.addPotionEffect(new PotionEffect(regen, 80, 0));
        }
        else if (MetallurgyMetals.netherSet.getOreInfo("Vulcanite").isEnabled() && itemstack.getItem().itemID == MetallurgyMetals.netherSet.getOreInfo("Vulcanite").getSword().itemID)
        {
            entityliving.setFire(4);
        }
        else if (MetallurgyMetals.netherSet.getOreInfo("Sanguinite").isEnabled() && itemstack.getItem().itemID == MetallurgyMetals.netherSet.getOreInfo("Sanguinite").getSword().itemID)
        {
            entityliving.addPotionEffect(new PotionEffect(wither, 80, 0));
        }
        else if (MetallurgyMetals.netherSet.getOreInfo("Shadow Steel").isEnabled()
                && itemstack.getItem().itemID == MetallurgyMetals.netherSet.getOreInfo("Shadow Steel").getSword().itemID)
        {
            entityliving.addPotionEffect(new PotionEffect(weakness, 80, 1));
        }
        else if (MetallurgyMetals.netherSet.getOreInfo("Inolashite").isEnabled() && itemstack.getItem().itemID == MetallurgyMetals.netherSet.getOreInfo("Inolashite").getSword().itemID)
        {
            entityliving.addPotionEffect(new PotionEffect(poison, 80, 0));
            entityliving.addPotionEffect(new PotionEffect(slowness, 80, 0));
        }
        else if (MetallurgyMetals.netherSet.getOreInfo("Amordrine").isEnabled() && itemstack.getItem().itemID == MetallurgyMetals.netherSet.getOreInfo("Amordrine").getSword().itemID)
        {
            player.heal(3);
        }

        return false;
    }

    @ForgeSubscribe
    public void onDeathEvent(LivingDeathEvent event)
    {
        if (event.source.getEntity() instanceof EntityPlayer)
        {
            final EntityPlayer player = (EntityPlayer) event.source.getEntity();
            if (player.getCurrentEquippedItem() == null)
            {
                return;
            }

            if (MetallurgyMetals.netherSet.getOreInfo("Midasium").isEnabled())
            {
                if (player.getCurrentEquippedItem().itemID == MetallurgyMetals.netherSet.getOreInfo("Midasium").getSword().itemID)
                {
                    try
                    {
                        final Method m = EntityLiving.class.getDeclaredMethod("dropFewItems", Boolean.TYPE, Integer.TYPE);
                        m.setAccessible(true);
                        m.invoke(event.entityLiving, true, 0);
                        // m.setAccessible(false);
                    } catch (final Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
