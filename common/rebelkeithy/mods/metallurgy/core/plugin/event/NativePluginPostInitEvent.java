package rebelkeithy.mods.metallurgy.core.plugin.event;

import java.io.File;
import java.util.logging.Logger;

import net.minecraftforge.event.Event;
import rebelkeithy.mods.metallurgy.core.MetalInfoDatabase;

public class NativePluginPostInitEvent extends Event
{
    private final Logger logger;
    private final File configDir;
    private final boolean debug;
    private final MetalInfoDatabase dbMetal;

    public NativePluginPostInitEvent(final Logger logger, final File configDir,
            final MetalInfoDatabase dbMetal, final boolean debug)
    {
        this.logger = logger;
        this.configDir = configDir;
        this.debug = debug;
        this.dbMetal = dbMetal;
    }

    public MetalInfoDatabase getMetalDatabase()
    {
        return dbMetal;
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
