package rebelkeithy.mods.metallurgy.machines.mint;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import rebelkeithy.mods.metallurgy.machines.MetallurgyMachines;
import rebelkeithy.mods.metallurgy.metals.MetallurgyMetals;
import cpw.mods.fml.common.registry.VillagerRegistry.IVillageTradeHandler;

public class MetallurgyTradeHandler implements IVillageTradeHandler {

	@Override
	public void manipulateTradesForVillager(EntityVillager villager, MerchantRecipeList recipeList, Random random) {
		if (villager.getProfession() == 0) // Farmer
		{
			recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.coin, 9), new ItemStack(Item.appleRed, 8)));
			recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.coin, 9), new ItemStack(Item.bread, 4)));
			recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.coin, 9), new ItemStack(Item.chickenCooked, 8)));
			recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.coin, 9), new ItemStack(Item.cookie, 10)));
			recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.coin, 9), new ItemStack(Item.melon, 8)));
			recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.coin, 9), new ItemStack(Item.arrow, 5)));
			recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.coin, 27), new ItemStack(Item.flintAndSteel, 1)));
			recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.coin, 27), new ItemStack(Item.shears, 1)));
			recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(Item.chickenRaw, 17), new ItemStack(MetallurgyMachines.coin, 9)));
			recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(Item.wheat, 17), new ItemStack(MetallurgyMachines.coin, 9)));
			recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(Item.fishRaw, 12), new ItemStack(MetallurgyMachines.coin, 9)));
			recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(Block.cloth, 21), new ItemStack(MetallurgyMachines.coin, 9)));
		} else if (villager.getProfession() == 1) // Librarian
		{
			recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.coin, 9), new ItemStack(Block.glass, 5)));
			recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.coin, 27), new ItemStack(Block.bookShelf, 1)));
			recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.stack, 10), new ItemStack(Item.compass, 1)));
			recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.stack, 10), new ItemStack(Item.pocketSundial, 1)));
			recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(Item.paper, 29), new ItemStack(MetallurgyMachines.coin, 9)));
			recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(Item.book, 14), new ItemStack(MetallurgyMachines.coin, 9)));
			recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(Item.writableBook), new ItemStack(MetallurgyMachines.coin, 9)));
		} else if (villager.getProfession() == 2) // priest
		{
			recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.coin, 9), new ItemStack(Item.expBottle, 4)));
			recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.coin, 9), new ItemStack(Item.redstone, 4)));
			recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.coin, 9), new ItemStack(Block.glowStone, 3)));
			recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.stack, 10), new ItemStack(Item.eyeOfEnder, 1)));
		}
		if (villager.getProfession() == 3) // Blacksmith
		{
			recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.stack, 9), new ItemStack(Item.helmetDiamond, 1)));
			recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.stack, 19), new ItemStack(Item.plateDiamond, 1)));
			recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.stack, 13), new ItemStack(Item.legsDiamond, 1)));
			recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.stack, 9), new ItemStack(Item.bootsDiamond, 1)));
			recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.stack, 13), new ItemStack(Item.swordDiamond, 1)));
			recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.stack, 11), new ItemStack(Item.pickaxeDiamond, 1)));
			recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.stack, 11), new ItemStack(Item.axeDiamond, 1)));
			recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.stack, 7), new ItemStack(Item.shovelDiamond, 1)));
			recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.stack, 7), new ItemStack(Item.hoeDiamond, 1)));
			// Chain
			recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.stack, 6), new ItemStack(Item.helmetChain, 1)));
			recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.stack, 14), new ItemStack(Item.plateChain, 1)));
			recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.stack, 10), new ItemStack(Item.legsChain, 1)));
			recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.stack, 6), new ItemStack(Item.bootsChain, 1)));
			// Iron
			recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.stack, 5), new ItemStack(Item.helmetIron, 1)));
			recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.stack, 13), new ItemStack(Item.plateIron, 1)));
			recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.stack, 9), new ItemStack(Item.legsIron, 1)));
			recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.stack, 5), new ItemStack(Item.bootsIron, 1)));
			recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.stack, 10), new ItemStack(Item.swordIron, 1)));
			recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.stack, 8), new ItemStack(Item.pickaxeIron, 1)));
			recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.stack, 7), new ItemStack(Item.axeIron, 1)));
			recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.stack, 5), new ItemStack(Item.shovelIron, 1)));
			recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.stack, 5), new ItemStack(Item.hoeIron, 1)));
			// Bars
			recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(Item.ingotIron, 1), new ItemStack(MetallurgyMachines.coin, 3, 1)));
			if (MetallurgyMetals.baseSet.getOreInfo("Tin").isEnabled()) {
				recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMetals.baseSet.getOreInfo("Tin").getIngotItem(), 5), new ItemStack(MetallurgyMachines.coin, 6)));
			}
			if (MetallurgyMetals.baseSet.getOreInfo("Manganese").isEnabled()) {
				recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMetals.baseSet.getOreInfo("Manganese").getIngotItem(), 5), new ItemStack(MetallurgyMachines.stack, 3, 1)));
			}

			if (MetallurgyMetals.baseSet.getOreInfo("Copper").isEnabled()) {
				recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMetals.baseSet.getOreInfo("Copper").getIngotItem(), 5), new ItemStack(MetallurgyMachines.coin, 6)));
				recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.stack, 3), new ItemStack(MetallurgyMetals.baseSet.getOreInfo("Copper").getHelmet(), 1)));
				recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.stack, 10), new ItemStack(MetallurgyMetals.baseSet.getOreInfo("Copper").getChest(), 1)));
				recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.stack, 6), new ItemStack(MetallurgyMetals.baseSet.getOreInfo("Copper").getLegs(), 1)));
				recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.stack, 3), new ItemStack(MetallurgyMetals.baseSet.getOreInfo("Copper").getBoots(), 1)));

				recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.stack, 7), new ItemStack(MetallurgyMetals.baseSet.getOreInfo("Copper").getSword(), 1)));
				recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.stack, 5), new ItemStack(MetallurgyMetals.baseSet.getOreInfo("Copper").getPickaxe(), 1)));
				recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.stack, 4), new ItemStack(MetallurgyMetals.baseSet.getOreInfo("Copper").getAxe(), 1)));
				recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.stack, 2), new ItemStack(MetallurgyMetals.baseSet.getOreInfo("Copper").getShovel(), 1)));
				recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.stack, 2), new ItemStack(MetallurgyMetals.baseSet.getOreInfo("Copper").getHoe(), 1)));
			}
			if (MetallurgyMetals.baseSet.getOreInfo("Bronze").isEnabled()) {
				recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMetals.baseSet.getOreInfo("Bronze").getIngotItem(), 5), new ItemStack(MetallurgyMachines.coin, 12)));
				// Bronze
				recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.stack, 4), new ItemStack(MetallurgyMetals.baseSet.getOreInfo("Bronze").getHelmet(), 1)));
				recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.stack, 12), new ItemStack(MetallurgyMetals.baseSet.getOreInfo("Bronze").getChest(), 1)));
				recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.stack, 8), new ItemStack(MetallurgyMetals.baseSet.getOreInfo("Bronze").getLegs(), 1)));
				recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.stack, 4), new ItemStack(MetallurgyMetals.baseSet.getOreInfo("Bronze").getBoots(), 1)));

				recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.stack, 9), new ItemStack(MetallurgyMetals.baseSet.getOreInfo("Bronze").getSword(), 1)));
				recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.stack, 7), new ItemStack(MetallurgyMetals.baseSet.getOreInfo("Bronze").getPickaxe(), 1)));
				recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.stack, 6), new ItemStack(MetallurgyMetals.baseSet.getOreInfo("Bronze").getAxe(), 1)));
				recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.stack, 4), new ItemStack(MetallurgyMetals.baseSet.getOreInfo("Bronze").getShovel(), 1)));
				recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.stack, 4), new ItemStack(MetallurgyMetals.baseSet.getOreInfo("Bronze").getHoe(), 1)));
			}
			if (MetallurgyMetals.baseSet.getOreInfo("Steel").isEnabled()) {
				recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMetals.baseSet.getOreInfo("Steel").getIngotItem(), 5), new ItemStack(MetallurgyMachines.stack, 6, 1)));
				// Steel
				recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.stack, 7), new ItemStack(MetallurgyMetals.baseSet.getOreInfo("Steel").getHelmet(), 1, 4)));
				recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.stack, 15), new ItemStack(MetallurgyMetals.baseSet.getOreInfo("Steel").getChest(), 1, 4)));
				recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.stack, 11), new ItemStack(MetallurgyMetals.baseSet.getOreInfo("Steel").getLegs(), 1, 4)));
				recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.stack, 7), new ItemStack(MetallurgyMetals.baseSet.getOreInfo("Steel").getBoots(), 1, 4)));

				recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.stack, 12), new ItemStack(MetallurgyMetals.baseSet.getOreInfo("Steel").getSword(), 1)));
				recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.stack, 10), new ItemStack(MetallurgyMetals.baseSet.getOreInfo("Steel").getPickaxe(), 1)));
				recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.stack, 9), new ItemStack(MetallurgyMetals.baseSet.getOreInfo("Steel").getAxe(), 1)));
				recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.stack, 7), new ItemStack(MetallurgyMetals.baseSet.getOreInfo("Steel").getShovel(), 1)));
				recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.stack, 7), new ItemStack(MetallurgyMetals.baseSet.getOreInfo("Steel").getHoe(), 1)));
			}
		}
		if (villager.getProfession() == 4) // Butcher
		{
			recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.coin, 9), new ItemStack(Item.beefCooked, 7)));
			recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.coin, 9), new ItemStack(Item.porkCooked, 7)));
			recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.coin, 27), new ItemStack(Item.helmetLeather, 1)));
			recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.coin, 27), new ItemStack(Item.plateLeather, 1)));
			recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.coin, 27), new ItemStack(Item.legsLeather, 1)));
			recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.coin, 27), new ItemStack(Item.bootsLeather, 1)));
			recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(MetallurgyMachines.coin, 64), new ItemStack(Item.saddle, 1)));
			recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(Item.beefRaw, 17), new ItemStack(MetallurgyMachines.coin, 9)));
			recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(Item.porkRaw, 17), new ItemStack(MetallurgyMachines.coin, 9)));
			recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(Item.coal, 23), new ItemStack(MetallurgyMachines.coin, 9)));
		}
	}

}
