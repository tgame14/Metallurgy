package rebelkeithy.mods.metallurgy.integration.appeng;

import java.util.logging.Logger;

import net.minecraft.item.ItemStack;
import net.minecraftforge.event.ForgeSubscribe;
import rebelkeithy.mods.metallurgy.api.ICrusherRecipeManager;
import rebelkeithy.mods.metallurgy.api.plugin.event.AddCrusherRecipesEvent;
import rebelkeithy.mods.metallurgy.core.plugin.event.NativePluginPreInitEvent;

public class CrusherRecipeHandler
{
    private Logger logger;

    CrusherRecipeHandler()
    {
        // limits visibility to package
    }

    @ForgeSubscribe
    public void addCrusherRecipes(final AddCrusherRecipesEvent event)
    {
        try
        {
            final AppEngAPI apiAppEng = new AppEngAPI();
            logger.fine("Adding Applied Energistics' quartz to the crusher.");
            final ICrusherRecipeManager cookbook = event.getCrusherRecipeManager();
            final ItemStack result = apiAppEng.getQuartzDust();
            result.stackSize = 2;
            cookbook.addCrushing(apiAppEng.getQuartz(), result);
        }
        catch (ClassNotFoundException | NoSuchFieldException | SecurityException
                | IllegalArgumentException | IllegalAccessException e)
        {
            logger.warning("Cannot add Applied Energistics support. API has changed or is corrupted.");
        }
    }

    @ForgeSubscribe
    public void preInit(final NativePluginPreInitEvent event)
    {
        logger = event.getMetallurgyLog();
    }
}
