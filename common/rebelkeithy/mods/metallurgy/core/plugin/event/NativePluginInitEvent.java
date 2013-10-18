package rebelkeithy.mods.metallurgy.core.plugin.event;

import java.io.File;
import java.util.logging.Logger;

import net.minecraftforge.event.Event;

public class NativePluginInitEvent extends Event
{
    private final Logger logger;
    private final File configDir;
    private final boolean debug;

    public NativePluginInitEvent(final Logger logger, final File configDir, final boolean debug)
    {
        this.logger = logger;
        this.configDir = configDir;
        this.debug = debug;
    }

    public File getMetallurgyConfigDir()
    {
        return configDir;
    }

    public Logger getMetallurgyLog()
    {
        return logger;
    }

    public boolean isDebugMode()
    {
        return debug;
    }
}
