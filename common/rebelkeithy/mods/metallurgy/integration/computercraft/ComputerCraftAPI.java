package rebelkeithy.mods.metallurgy.integration.computercraft;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.google.common.base.Optional;

import dan200.turtle.api.ITurtleUpgrade;

class ComputerCraftAPI
{
    private Optional<Method> registerUpgrade = Optional.absent();

    ComputerCraftAPI() throws ClassNotFoundException, NoSuchMethodException, SecurityException
    {
        final Class<?> cls = Class.forName("dan200.turtle.api.TurtleAPI");
        registerUpgrade = Optional.of(cls.getMethod("registerUpgrade", ITurtleUpgrade.class));
    }

    void registerUpgrade(final ITurtleUpgrade upgrade) throws IllegalAccessException,
            IllegalArgumentException, InvocationTargetException
    {
        if (registerUpgrade.isPresent()) registerUpgrade.get().invoke(null, upgrade);
    }
}
