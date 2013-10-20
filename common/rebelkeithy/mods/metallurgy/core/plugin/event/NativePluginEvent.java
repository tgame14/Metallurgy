package rebelkeithy.mods.metallurgy.core.plugin.event;

import net.minecraftforge.event.Event;
import net.minecraftforge.event.EventBus;

public abstract class NativePluginEvent extends Event
{
    private final EventBus bus;

    NativePluginEvent(final EventBus bus)
    {
        this.bus = bus;
    }

    public final EventBus getMetallurgyBus()
    {
        return bus;
    }
}
