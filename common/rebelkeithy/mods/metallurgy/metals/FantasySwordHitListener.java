package rebelkeithy.mods.metallurgy.metals;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import rebelkeithy.mods.metallurgy.core.metalsets.ISwordHitListener;
import rebelkeithy.mods.metallurgy.core.metalsets.MetalSet;

import com.google.common.collect.Maps;

public class FantasySwordHitListener implements ISwordHitListener
{
    private class Effect
    {
        int potionID;
        int amplifier;
        boolean ignite;

        Effect(final int potionID, final int amplifier, final boolean ignite)
        {
            this.potionID = potionID;
            this.amplifier = amplifier;
            this.ignite = ignite;
        }
    }

    private final Logger logger;
    private final Map<Integer, Effect> effects = Maps.newHashMap();
    private final Map<Integer, Integer> deathEffects = Maps.newHashMap();
    private Method dropFewItems = null;

    FantasySwordHitListener(final Logger logger, final MetalSet fantasySet)
    {
        this.logger = logger;

        final int speed = 1;
        final int haste = 3;
        final int strength = 5;
        final int resistance = 11;
        final int fireResist = 12;
        final int blindness = 15;
        final int wither = 20;

        setEffect(fantasySet, "Deep Iron", blindness, 0, false);
        setEffect(fantasySet, "Black Steel", blindness, 1, false);
        setEffect(fantasySet, "Oureclase", resistance, 0, false);
        setEffect(fantasySet, "Mithril", haste, 0, false);
        setEffect(fantasySet, "Quicksilver", speed, 0, false);
        setEffect(fantasySet, "Haderoth", haste, 0, true);
        setEffect(fantasySet, "Orichalcum", resistance, 1, false);
        setEffect(fantasySet, "Celenegil", resistance, 3, false);
        setEffect(fantasySet, "Adamantine", fireResist, 0, true);
        setEffect(fantasySet, "Atlarus", strength, 1, false);
        setEffect(fantasySet, "Tartarite", wither, 1, true);

        setDeathEffect(fantasySet, "Astral Silver", 1);
        setDeathEffect(fantasySet, "Carmot", 2);

        try
        {
            dropFewItems =
                    EntityLiving.class
                            .getDeclaredMethod("func_70628_a", Boolean.TYPE, Integer.TYPE);
        }
        catch (final NoSuchMethodException e)
        {
            logger.info("FantasySwordHitListener: Could not find obfuscated version of dropFewItems. Using deobfuscated version.");
            try
            {
                dropFewItems =
                        EntityLiving.class.getDeclaredMethod("dropFewItems", Boolean.TYPE,
                                Integer.TYPE);
            }
            catch (NoSuchMethodException | SecurityException e1)
            {
                logger.info("FantasySwordHitListener: Could not find deobfuscated version of dropFewItems.");
            }
        }
        catch (final SecurityException e)
        {}

        if (dropFewItems != null) dropFewItems.setAccessible(true);
    }

    @Override
    public boolean hitEntity(final ItemStack itemstack, final EntityLivingBase entityliving,
            final EntityLivingBase player)
    {
        if (Math.random() >= 0.7)
        {
            final Effect effect = effects.get(itemstack.getItem().itemID);
            if (effect != null)
            {
                entityliving
                        .addPotionEffect(new PotionEffect(effect.potionID, 80, effect.amplifier));
                if (effect.ignite) entityliving.setFire(4);
            }
        }
        return false;
    }

    @ForgeSubscribe
    public void onDeathEvent(final LivingDeathEvent event)
    {
        if (dropFewItems == null) return;

        if (event.source.getEntity() instanceof EntityPlayer)
        {
            final EntityPlayer player = (EntityPlayer) event.source.getEntity();
            if (player.getCurrentEquippedItem() == null) return;

            final Integer effect = deathEffects.get(player.getCurrentEquippedItem().itemID);
            if (effect != null && effect > 0)
                try
                {
                    dropFewItems.invoke(event.entityLiving, true, 0);
                    if (effect > 1)
                        if (Math.random() > 5) dropFewItems.invoke(event.entityLiving, true, 0);
                }
                catch (IllegalAccessException | IllegalArgumentException
                        | InvocationTargetException e)
                {
                    logger.log(
                            Level.INFO,
                            "Disabling fantasy weapon death effects. Problem with \"dropFewItems\": ",
                            e);
                    dropFewItems = null;
                }
        }
    }

    private void setDeathEffect(final MetalSet fantasySet, final String name, final int effect)
    {
        if (fantasySet.getOreInfo(name).isEnabled())
            deathEffects.put(fantasySet.getOreInfo(name).getSword().itemID, effect);
    }

    private void setEffect(final MetalSet fantasySet, final String name, final int potionID,
            final int amplifier, final boolean ignite)
    {
        if (fantasySet.getOreInfo(name).isEnabled())
            effects.put(fantasySet.getOreInfo(name).getSword().itemID, new Effect(potionID,
                    amplifier, ignite));
    }
}
