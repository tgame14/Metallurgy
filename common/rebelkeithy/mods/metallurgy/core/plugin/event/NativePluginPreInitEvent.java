package rebelkeithy.mods.metallurgy.core.plugin.event;

import java.io.File;
import java.util.logging.Logger;

import net.minecraftforge.event.Event;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class NativePluginPreInitEvent extends Event
{
    private final File sourceFile;
    private final File configDir;
    private final String version;
    private final Logger logger;

    public NativePluginPreInitEvent(final FMLPreInitializationEvent event, final String version)
    {
        sourceFile = event.getSourceFile();
        configDir = new File(event.getModConfigurationDirectory(), "Metallurgy");
        this.version = version;
        logger = event.getModLog();
    }

    public File getMetallurgyConfigDir()
    {
        return configDir;
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
}
