package rebelkeithy.mods.metallurgy.api;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public interface ICrusherRecipeManager
{
    /**
     * Add a crusher recipe
     * 
     * @param block
     *            The input block
     * @param result
     *            The output
     */
    public void addCrushing(Block block, ItemStack result);

    /**
     * Add a crusher recipe
     * 
     * @param itemID
     *            The input itemID
     * @param damage
     *            The input damage
     * @param result
     *            The output
     */
    public void addCrushing(int itemID, int damage, ItemStack result);

    /**
     * Add a crusher recipe
     * 
     * @param itemID
     *            The input itemID
     * @param result
     *            The output
     */
    public void addCrushing(int itemID, ItemStack result);

    /**
     * Add a crusher recipe
     * 
     * @param item
     *            The input item
     * @param result
     *            The output
     */
    public void addCrushing(ItemStack item, ItemStack result);
}
