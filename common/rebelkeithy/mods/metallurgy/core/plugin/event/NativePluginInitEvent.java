package rebelkeithy.mods.metallurgy.core.plugin.event;

import java.io.File;
import java.util.logging.Logger;

import net.minecraftforge.event.Event;
import rebelkeithy.mods.metallurgy.core.database.MetalInfoDatabase;

public class NativePluginInitEvent extends Event
{
    private final Logger logger;
    private final File configDir;
    private final MetalInfoDatabase dbMetal;

    public NativePluginInitEvent(final Logger logger, final File configDir,
            final MetalInfoDatabase dbMetal)
    {
        this.logger = logger;
        this.configDir = configDir;
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
}
