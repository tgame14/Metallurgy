package rebelkeithy.mods.metallurgy.api.plugin.event;

import net.minecraftforge.event.Event;
import rebelkeithy.mods.metallurgy.api.ICrusherRecipeManager;

public class AddCrusherRecipesEvent extends Event
{
    private final ICrusherRecipeManager crusherRecipeManager;

    public AddCrusherRecipesEvent(final ICrusherRecipeManager crusherRecipeManager)
    {
        this.crusherRecipeManager = crusherRecipeManager;
    }

    public ICrusherRecipeManager getCrusherRecipeManager()
    {
        return crusherRecipeManager;
    }
}
