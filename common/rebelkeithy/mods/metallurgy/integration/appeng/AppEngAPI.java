package rebelkeithy.mods.metallurgy.integration.appeng;

import java.lang.reflect.Field;

import net.minecraft.item.ItemStack;

class AppEngAPI
{
    private final Class<?> materials;

    AppEngAPI() throws ClassNotFoundException
    {
        materials = Class.forName("appeng.api.Materials");
    }

    public ItemStack getQuartz() throws IllegalArgumentException, IllegalAccessException,
            NoSuchFieldException, SecurityException
    {
        final Field field = materials.getField("matQuartz");
        return ((ItemStack) field.get(null)).copy();
    }

    public ItemStack getQuartzDust() throws IllegalArgumentException, IllegalAccessException,
            NoSuchFieldException, SecurityException
    {
        final Field field = materials.getField("matQuartzDust");
        return ((ItemStack) field.get(null)).copy();
    }
}
