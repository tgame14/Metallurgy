package rebelkeithy.mods.metallurgy.core.plugin.event;

import java.io.File;
import java.util.logging.Logger;

import net.minecraftforge.event.Event;
import net.minecraftforge.event.EventBus;
import rebelkeithy.mods.metallurgy.core.MetallurgyCore;
import rebelkeithy.mods.metallurgy.core.database.MetalInfoDatabase;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public abstract class NativePluginStartupEvent extends Event
{
    public static class Init extends NativePluginStartupEvent
    {
        public Init(final EventBus bus, final File configDir, final Logger logger,
                final MetalInfoDatabase dbMetal)
        {
            super(bus, configDir, logger, dbMetal);
        }
    }

    public static class Post extends NativePluginStartupEvent
    {
        public Post(final EventBus bus, final File configDir, final Logger logger,
                final MetalInfoDatabase dbMetal)
        {
            super(bus, configDir, logger, dbMetal);
        }
    }

    public static class Pre extends NativePluginStartupEvent
    {
        private final File sourceFile;
        private final MetallurgyCore instance;
        private final String version;

        public Pre(final FMLPreInitializationEvent event, final MetallurgyCore instance,
                final EventBus bus, final String version, final MetalInfoDatabase dbMetal)
        {
            super(bus, new File(event.getModConfigurationDirectory(), "Metallurgy3"), event
                    .getModLog(), dbMetal);
            sourceFile = event.getSourceFile();
            this.version = version;
            this.instance = instance;
        }

        public MetallurgyCore getMetallurgyInstance()
        {
            return instance;
        }

        public File getMetallurgySourceFile()
        {
            return sourceFile;
        }

        public String getMetallurgyVersion()
        {
            return version;
        }
    }
    private final EventBus bus;

    private final File configDir;

    private final Logger logger;

    private final MetalInfoDatabase dbMetal;

    NativePluginStartupEvent(final EventBus bus, final File configDir, final Logger logger,
            final MetalInfoDatabase dbMetal)
    {
        this.bus = bus;
        this.configDir = configDir;
        this.logger = logger;
        this.dbMetal = dbMetal;
    }

    public final MetalInfoDatabase getMetalDatabase()
    {
        return dbMetal;
    }

    public final EventBus getMetallurgyBus()
    {
        return bus;
    }

    public final File getMetallurgyConfigDir()
    {
        return configDir;
    }

    public final Logger getMetallurgyLog()
    {
        return logger;
    }
}
