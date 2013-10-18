package rebelkeithy.mods.metallurgy.core.plugin.event;

import java.io.File;
import java.util.logging.Logger;

import net.minecraftforge.event.Event;
import rebelkeithy.mods.metallurgy.core.MetallurgyCore;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class NativePluginPreInitEvent extends Event
{
    private final boolean debug;
    private final File sourceFile;
    private final File configDir;
    private final String version;
    private final Logger logger;
    private final MetallurgyCore instance;

    public NativePluginPreInitEvent(final FMLPreInitializationEvent event,
            final MetallurgyCore instance, final String version, final boolean debug)
    {
        sourceFile = event.getSourceFile();
        configDir = new File(event.getModConfigurationDirectory(), "Metallurgy3");
        this.version = version;
        logger = event.getModLog();
        this.instance = instance;
        this.debug = debug;
    }

    public File getMetallurgyConfigDir()
    {
        return configDir;
    }

    public MetallurgyCore getMetallurgyInstance()
    {
        return instance;
    }

    public Logger getMetallurgyLog()
    {
        return logger;
    }

    public File getMetallurgySourceFile()
    {
        return sourceFile;
    }

    public String getMetallurgyVersion()
    {
        return version;
    }

    public boolean isDebugMode()
    {
        return debug;
    }
}
