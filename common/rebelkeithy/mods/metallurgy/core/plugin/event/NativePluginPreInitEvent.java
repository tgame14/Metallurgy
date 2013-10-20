package rebelkeithy.mods.metallurgy.core.plugin.event;

import java.io.File;
import java.util.logging.Logger;

import net.minecraftforge.event.EventBus;
import rebelkeithy.mods.metallurgy.core.MetallurgyCore;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class NativePluginPreInitEvent extends NativePluginEvent
{
    private final File sourceFile;
    private final File configDir;
    private final String version;
    private final Logger logger;
    private final MetallurgyCore instance;

    public NativePluginPreInitEvent(final FMLPreInitializationEvent event,
            final MetallurgyCore instance, final EventBus bus, final String version)
    {
        super(bus);
        sourceFile = event.getSourceFile();
        configDir = new File(event.getModConfigurationDirectory(), "Metallurgy3");
        this.version = version;
        logger = event.getModLog();
        this.instance = instance;
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
}
