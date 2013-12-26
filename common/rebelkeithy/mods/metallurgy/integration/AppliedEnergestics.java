package rebelkeithy.mods.metallurgy.integration;

import cpw.mods.fml.common.Loader;
import rebelkeithy.mods.metallurgy.machines.crusher.CrusherRecipes;
import appeng.api.Materials;
import net.minecraft.item.ItemStack;

public class AppliedEnergestics {

	public static void init() {
		if (Loader.isModLoaded("AppliedEnergistics"))
		{
			CrusherRecipes.addCrushing(Materials.matQuartz.copy().itemID, Materials.matQuartz.copy().getItemDamage(), new ItemStack(Materials.matQuartzDust.copy().getItem(), 2, Materials.matQuartzDust.copy().getItemDamage()));
		}
		
	}

}
