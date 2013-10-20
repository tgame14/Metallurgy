package rebelkeithy.mods.metallurgy.metals;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.EventPriority;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import rebelkeithy.mods.metallurgy.core.MetalInfoDatabase;
import rebelkeithy.mods.metallurgy.core.MetallurgyCore;
import rebelkeithy.mods.metallurgy.core.MetallurgyTabs;
import rebelkeithy.mods.metallurgy.core.metalsets.ISwordHitListener;
import rebelkeithy.mods.metallurgy.core.metalsets.ItemMetallurgy;
import rebelkeithy.mods.metallurgy.core.metalsets.MetalSet;
import rebelkeithy.mods.metallurgy.core.plugin.event.NativePluginStartupEvent;
import rebelkeithy.mods.metallurgy.integration.ComputerCraftIntegration;
import rebelkeithy.mods.metallurgy.integration.IndustrialCraftIntegration;
import rebelkeithy.mods.metallurgy.metals.utilityItems.ItemFertilizer;
import rebelkeithy.mods.metallurgy.metals.utilityItems.ItemIgniter;
import rebelkeithy.mods.metallurgy.metals.utilityItems.tnt.BlockLargeTNT;
import rebelkeithy.mods.metallurgy.metals.utilityItems.tnt.BlockMinersTNT;
import rebelkeithy.mods.metallurgy.metals.utilityItems.tnt.EntityLargeTNTPrimed;
import rebelkeithy.mods.metallurgy.metals.utilityItems.tnt.EntityMinersTNTPrimed;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import cpw.mods.fml.common.IFuelHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class MetallurgyMetals
{

    public boolean isRelease = false;

	private int oreFinderID = 5102;
	private boolean oreFinderEnabled = true;

    public static MetalSet baseSet;
    public static MetalSet preciousSet;
    public static MetalSet netherSet;
    public static MetalSet fantasySet;
    public static MetalSet enderSet;
    public static MetalSet utilitySet;

    public static CreativeTabs baseTab;
    public static CreativeTabs preciousTab;
    public static CreativeTabs netherTab;
    public static CreativeTabs fantasyTab;
    public static CreativeTabs enderTab;
    public static CreativeTabs utilityTab;

    public static Configuration baseConfig;
    public static Configuration utilityConfig;
    public static Configuration fantasyConfig;

    // Vanilla Items
    public static Item dustIron;
    public static Item dustGold;

    // Utility Items
    public static Item magnesiumIgniter;
    public static Item match;
    public static Item fertilizer;
    public static Item tar;
    public static Item debug;

    public static Block largeTNT;
    public static Block minersTNT;

    @SidedProxy(clientSide = "rebelkeithy.mods.metallurgy.metals.ClientProxy", serverSide = "rebelkeithy.mods.metallurgy.metals.CommonProxy")
    public static CommonProxy proxy;

    private void addRailRecipes()
    {
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.rail, 4), "X X", "XSX", "X X", 'X', "ingotCopper", 'S', Item.stick));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.rail, 10), "X X", "XSX", "X X", 'X', "ingotBronze", 'S', Item.stick));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.rail, 14), "X X", "XSX", "X X", 'X', "ingotHepatizon", 'S', Item.stick));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.rail, 26), "X X", "XSX", "X X", 'X', "ingotDamascus Steel", 'S', Item.stick));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.rail, 32), "X X", "XSX", "X X", 'X', "ingotAngmallen", 'S', Item.stick));
    }

    public void addSwordEffects()
    {
        ISwordHitListener swordEffects = new NetherSwordHitListener();
        MinecraftForge.EVENT_BUS.register(swordEffects); // Registers the on
                                                         // death event needed
                                                         // by Midasium's
                                                         // looting effect
        if (netherSet.getOreInfo("Ignatius").getSword() != null)
        {
            netherSet.getOreInfo("Ignatius").getSword().addHitListener(swordEffects);
            netherSet.getOreInfo("Ignatius").getSword().setSubText("cIgnite I");
        }
        if (netherSet.getOreInfo("Shadow Iron").getSword() != null)
        {
            netherSet.getOreInfo("Shadow Iron").getSword().addHitListener(swordEffects);
            netherSet.getOreInfo("Shadow Iron").getSword().setSubText("cWeakness I");
        }
        if (netherSet.getOreInfo("Shadow Steel").getSword() != null)
        {
            netherSet.getOreInfo("Shadow Steel").getSword().addHitListener(swordEffects);
            netherSet.getOreInfo("Shadow Steel").getSword().setSubText("7Weakness II");
        }
        if (netherSet.getOreInfo("Midasium").getSword() != null)
        {
            // Midsium'ss effect comes from the onDeath event, not the onHit
            // method
            netherSet.getOreInfo("Midasium").getSword().setSubText("7Looting I");
        }
        if (netherSet.getOreInfo("Vyroxeres").getSword() != null)
        {
            netherSet.getOreInfo("Vyroxeres").getSword().addHitListener(swordEffects);
            netherSet.getOreInfo("Vyroxeres").getSword().setSubText("cPoison I");
        }
        if (netherSet.getOreInfo("Ceruclase").getSword() != null)
        {
            netherSet.getOreInfo("Ceruclase").getSword().addHitListener(swordEffects);
            netherSet.getOreInfo("Ceruclase").getSword().setSubText("cSlowness");
        }
        if (netherSet.getOreInfo("Inolashite").getSword() != null)
        {
            netherSet.getOreInfo("Inolashite").getSword().addHitListener(swordEffects);
            netherSet.getOreInfo("Inolashite").getSword().setSubText("7Poison, Slowness");
        }
        if (netherSet.getOreInfo("Kalendrite").getSword() != null)
        {
            netherSet.getOreInfo("Kalendrite").getSword().addHitListener(swordEffects);
            netherSet.getOreInfo("Kalendrite").getSword().setSubText("7Regen");
        }
        if (netherSet.getOreInfo("Amordrine").getSword() != null)
        {
            netherSet.getOreInfo("Amordrine").getSword().addHitListener(swordEffects);
            netherSet.getOreInfo("Amordrine").getSword().setSubText("7Healing");
        }
        if (netherSet.getOreInfo("Vulcanite").getSword() != null)
        {
            netherSet.getOreInfo("Vulcanite").getSword().addHitListener(swordEffects);
            netherSet.getOreInfo("Vulcanite").getSword().setSubText("cIgnite II");
        }
        if (netherSet.getOreInfo("Sanguinite").getSword() != null)
        {
            netherSet.getOreInfo("Sanguinite").getSword().addHitListener(swordEffects);
            netherSet.getOreInfo("Sanguinite").getSword().setSubText("cWither I");
        }

        swordEffects = new FantasySwordHitListener();
        MinecraftForge.EVENT_BUS.register(swordEffects); // Registers the on
                                                         // death event needed
                                                         // by Astral Silver's
                                                         // and Carmot's looting
                                                         // effect

        if (netherSet.getOreInfo("Deep Iron").getSword() != null)
        {
            fantasySet.getOreInfo("Deep Iron").getSword().addHitListener(swordEffects);
            fantasySet.getOreInfo("Deep Iron").getSword().setSubText("cBlindness I");
        }
        if (netherSet.getOreInfo("Black Steel").getSword() != null)
        {
            fantasySet.getOreInfo("Black Steel").getSword().addHitListener(swordEffects);
            fantasySet.getOreInfo("Black Steel").getSword().setSubText("cBlindness II");
        }
        if (netherSet.getOreInfo("Oureclase").getSword() != null)
        {
            fantasySet.getOreInfo("Oureclase").getSword().addHitListener(swordEffects);
            fantasySet.getOreInfo("Oureclase").getSword().setSubText("7Resistance I");
        }
        if (netherSet.getOreInfo("Astral Silver").getSword() != null)
        {
            // fantasySet.getOreInfo("Astral Silver").sword.addHitListener(swordEffects);
            fantasySet.getOreInfo("Astral Silver").getSword().setSubText("7Looting I");
        }
        if (netherSet.getOreInfo("Carmot").getSword() != null)
        {
            // fantasySet.getOreInfo("Carmot").sword.addHitListener(swordEffects);
            fantasySet.getOreInfo("Carmot").getSword().setSubText("7Looting II");
        }
        if (netherSet.getOreInfo("Mithril").getSword() != null)
        {
            fantasySet.getOreInfo("Mithril").getSword().addHitListener(swordEffects);
            fantasySet.getOreInfo("Mithril").getSword().setSubText("7Haste I");
        }
        if (netherSet.getOreInfo("Quicksilver").getSword() != null)
        {
            fantasySet.getOreInfo("Quicksilver").getSword().addHitListener(swordEffects);
            fantasySet.getOreInfo("Quicksilver").getSword().setSubText("7Speed I");
        }
        if (netherSet.getOreInfo("Haderoth").getSword() != null)
        {
            fantasySet.getOreInfo("Haderoth").getSword().addHitListener(swordEffects);
            fantasySet.getOreInfo("Haderoth").getSword().setSubText("cHaste I, Ignite II");
        }
        if (netherSet.getOreInfo("Orichalcum").getSword() != null)
        {
            fantasySet.getOreInfo("Orichalcum").getSword().addHitListener(swordEffects);
            fantasySet.getOreInfo("Orichalcum").getSword().setSubText("cResistance II");
        }
        if (netherSet.getOreInfo("Celenegil").getSword() != null)
        {
            fantasySet.getOreInfo("Celenegil").getSword().addHitListener(swordEffects);
            fantasySet.getOreInfo("Celenegil").getSword().setSubText("7Resistance III");
        }
        if (netherSet.getOreInfo("Adamantine").getSword() != null)
        {
            fantasySet.getOreInfo("Adamantine").getSword().addHitListener(swordEffects);
            fantasySet.getOreInfo("Adamantine").getSword().setSubText("7Fire Resist I, Ignite II");
        }
        if (netherSet.getOreInfo("Atlarus").getSword() != null)
        {
            fantasySet.getOreInfo("Atlarus").getSword().addHitListener(swordEffects);
            fantasySet.getOreInfo("Atlarus").getSword().setSubText("7Strength II");
        }
        if (netherSet.getOreInfo("Tartarite").getSword() != null)
        {
            fantasySet.getOreInfo("Tartarite").getSword().addHitListener(swordEffects);
            fantasySet.getOreInfo("Tartarite").getSword().setSubText("cWither, Igntite II");
        }
    }

    public void createMidasiumRecipes(Logger log)
    {
        final String[] ores = OreDictionary.getOreNames();
        for (final String name : ores)
        {
            if (name.contains("dust") && !name.toLowerCase().contains("tiny") && !name.toLowerCase().contains("clay") && !name.toLowerCase().contains("quartz"))
            {
                log.info("Adding recipe for " + name + " midasium = gold");
                GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(dustGold), "dustMidasium", name));
            }
        }
    }

    public void createUtilityItems(MetallurgyCore instance, File configDir, MetalInfoDatabase dbMetal)
    {
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Item.blazeRod), "I", "I", 'I', "ingotVulcanite"));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(dustIron, 2), "dustShadow Iron", "dustIgnatius"));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(dustIron, 2), "dustDeep Iron", "dustPrometheum"));

        int id = utilityConfig.get("Item IDs", "HE TNT", 920).getInt();
        if (id != 0)
        {
            largeTNT = new BlockLargeTNT(id).setUnlocalizedName("M3HETNT").setCreativeTab(utilityTab);
            GameRegistry.registerBlock(largeTNT, "M3HETNT");
            EntityRegistry.registerModEntity(EntityLargeTNTPrimed.class, "LargeTNTEntity", 113, instance, 64, 10, true);
            LanguageRegistry.addName(largeTNT, "HE TNT");
            if (utilityConfig.get("Recipes", "Enable HE TNT", true).getBoolean(true))
            {
                GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(largeTNT, 4), "MPM", "PTP", "MPM", 'M', "dustSaltpeter", 'P', "dustSulfur", 'T', Block.tnt));
                GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(largeTNT, 4), "MPM", "PTP", "MPM", 'P', "dustSaltpeter", 'M', "dustSulfur", 'T', Block.tnt));    
            }
        }

        id = utilityConfig.get("Item IDs", "LE TNT", 921).getInt();
        if (id != 0)
        {
            minersTNT = new BlockMinersTNT(id).setUnlocalizedName("M3LETNT").setCreativeTab(utilityTab);
            GameRegistry.registerBlock(minersTNT, "M3LETNT");
            EntityRegistry.registerModEntity(EntityMinersTNTPrimed.class, "MinersTNTEntity", 113, instance, 64, 10, true);
            LanguageRegistry.addName(minersTNT, "LE TNT");
            if (utilityConfig.get("Recipes", "Enable LE TNT", true).getBoolean(true))
            {
                GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(minersTNT, 4), "MPM", "PTP", "MPM", 'M', "dustMagnesium", 'P', "dustPhosphorus", 'T', Block.tnt));
                GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(minersTNT, 4), "PMP", "MTM", "PMP", 'M', "dustMagnesium", 'P', "dustPhosphorus", 'T', Block.tnt));
            }
        }

        id = utilityConfig.get("Item IDs", "Magnesium Igniter", 29007).getInt();
        magnesiumIgniter = new ItemIgniter(id).setMaxDamage(128).setMaxStackSize(1).setTextureName("Metallurgy:Utility/Igniter").setUnlocalizedName("Metallurgy:Utility/Igniter").setCreativeTab(utilityTab);
        LanguageRegistry.addName(magnesiumIgniter, "Magnesium Igniter");
        if (utilityConfig.get("Recipes", "Enable Magnesium Igniter", true).getBoolean(true))
        {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(magnesiumIgniter), "X ", " F", 'X', "dustMagnesium", 'F', Item.flint));
        }

        id = utilityConfig.get("Item IDs", "Match", 29008).getInt();
        match = new ItemIgniter(id).setMaxDamage(1).setMaxStackSize(64).setTextureName("Metallurgy:Utility/Match").setUnlocalizedName("Metallurgy:Utility/Match").setCreativeTab(utilityTab);
        LanguageRegistry.addName(match, "Match");
        if (utilityConfig.get("Recipes", "Enable Match", true).getBoolean(true))
        {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(match, 4), "X", "|", 'X', "dustPhosphorus", '|', Item.stick));
        }

        id = utilityConfig.get("Item IDs", "Fertilizer", 29009).getInt();
        fertilizer = new ItemFertilizer(id).setTextureName("Metallurgy:Utility/Fertilizer").setUnlocalizedName("Metallurgy:Utility/Fertilizer").setCreativeTab(utilityTab);
        LanguageRegistry.addName(fertilizer, "Fertilizer");
        if (utilityConfig.get("Recipes", "Enable Fertilizer", true).getBoolean(true))
        {
            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(fertilizer, 8), "dustPhosphorus", "dustMagnesium", "dustPotash"));
            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(fertilizer, 8), "dustPhosphorus", "dustMagnesium", "dustSaltpeter"));
            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(fertilizer, 8), "dustPhosphorus", "dustSaltpeter", "dustPotash"));
            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(fertilizer, 8), "dustSaltpeter", "dustMagnesium", "dustPotash"));
        }
        OreDictionary.registerOre("itemFertilizer", fertilizer);

        id = utilityConfig.get("Item IDs", "Tar", 29010).getInt();
        tar = new ItemMetallurgy(id).setTextureName("Metallurgy:Utility/Tar").setUnlocalizedName("Metallurgy:Utility/Tar").setCreativeTab(utilityTab);
        LanguageRegistry.addName(tar, "Tar");
        OreDictionary.registerOre("itemTar", tar);
        GameRegistry.addSmelting(dbMetal.getItem("Bitumen").itemID, new ItemStack(tar), 0.1F);

        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Item.gunpowder, 4), new ItemStack(Item.coal, 1, 1), "dustSulfur", "dustSaltpeter"));
        GameRegistry.addRecipe(new ShapelessOreRecipe(Item.magmaCream, "itemTar", Item.blazePowder));
        GameRegistry.addRecipe(new ShapedOreRecipe(Block.pistonStickyBase, "T", "P", 'T', "itemTar", 'P', Block.pistonBase));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Item.leash, 2), "SS ", "ST ", "  S", 'T', "itemTar", 'S', Item.silk));
        
        if (isSetEnabled("Utility", configDir))
        {
            ((MetallurgyTabs) utilityTab).setIconItem(fertilizer.itemID);
        }
    }

    @ForgeSubscribe(priority = EventPriority.HIGHEST)
    public void Init(NativePluginStartupEvent.Init event)
    {
        // TODO add config for vanilla dusts
        FurnaceRecipes.smelting().addSmelting(dustIron.itemID, 0, new ItemStack(Item.ingotIron), 0.7F);
        FurnaceRecipes.smelting().addSmelting(dustGold.itemID, 0, new ItemStack(Item.ingotGold), 0.7F);

        LanguageRegistry.addName(dustIron, "Iron Dust");
        LanguageRegistry.addName(dustGold, "Gold Dust");
        OreDictionary.registerOre("dustIron", dustIron);
        OreDictionary.registerOre("dustGold", dustGold);

        if (oreFinderEnabled)
        {
            debug = new ItemOreFinder(oreFinderID).setUnlocalizedName("stick").setCreativeTab(CreativeTabs.tabTools);
        }

        if (fantasySet.getOreInfo("Atral Silver").getOreItem() != null)
        {
            fantasySet.getOreInfo("Astral Silver").getOreItem().addDisplayListener(new DisplayListenerOreParticles("FantasyOre", 0.6, 0.8, 0.95));
        }
        if (fantasySet.getOreInfo("Carmot").getOreItem() != null)
        {
            fantasySet.getOreInfo("Carmot").getOreItem().addDisplayListener(new DisplayListenerOreParticles("FantasyOre", 0.8, 0.8, 0.4));
        }
        if (fantasySet.getOreInfo("Mithril").getOreItem() != null)
        {
            fantasySet.getOreInfo("Mithril").getOreItem().addDisplayListener(new DisplayListenerOreParticles("FantasyOre", 0.6, 0.9, 0.95));
        }
        if (fantasySet.getOreInfo("Orichalcum").getOreItem() != null)
        {
            fantasySet.getOreInfo("Orichalcum").getOreItem().addDisplayListener(new DisplayListenerOreParticles("FantasyOre", 0.3, 0.5, 0.15));
        }
        if (fantasySet.getOreInfo("Adamantine").getOreItem() != null)
        {
            fantasySet.getOreInfo("Adamantine").getOreItem().addDisplayListener(new DisplayListenerOreParticles("FantasyOre", 0.5, 0.2, 0.2));
        }
        if (fantasySet.getOreInfo("Atlarus").getOreItem() != null)
        {
            fantasySet.getOreInfo("Atlarus").getOreItem().addDisplayListener(new DisplayListenerOreParticles("FantasyOre", 0.8, 0.8, 0.2));
        }

        if (netherSet.getOreInfo("Midasium").getOreItem() != null)
        {
            netherSet.getOreInfo("Midasium").getOreItem().addDisplayListener(new DisplayListenerOreParticles("NetherOre", 1.0, 0.8, 0.25));
        }
        if (netherSet.getOreInfo("Vyroxeres").getOreItem() != null)
        {
            netherSet.getOreInfo("Vyroxeres").getOreItem().addDisplayListener(new DisplayListenerVyroxeresOreParticles());
        }
        if (netherSet.getOreInfo("Ceruclase").getOreItem() != null)
        {
            netherSet.getOreInfo("Ceruclase").getOreItem().addDisplayListener(new DisplayListenerOreParticles("NetherOre", 0.35, 0.6, 0.9));
        }
        if (netherSet.getOreInfo("Kalendrite").getOreItem() != null)
        {
            netherSet.getOreInfo("Kalendrite").getOreItem().addDisplayListener(new DisplayListenerOreParticles("NetherOre", 0.8, 0.4, 0.8));
        }
        if (netherSet.getOreInfo("Vulcanite").getOreItem() != null)
        {
            netherSet.getOreInfo("Vulcanite").getOreItem().addDisplayListener(new DisplayListenerVulcaniteOreParticles());
        }
        if (netherSet.getOreInfo("Sanguinite").getOreItem() != null)
        {
            netherSet.getOreInfo("Sanguinite").getOreItem().addDisplayListener(new DisplayListenerOreParticles("NetherOre", 0.85, 0.0, 0.0));
        }

        if (netherSet.getOreInfo("Sanguinite").getOreItem() != null)
        {
            netherSet.getOreInfo("Vyroxeres").getOreItem().addCollisionListener(new VyroxeresCollisionListener());
        }

        addRailRecipes();
        addSwordEffects();

        proxy.registerParticles();
    }

    public Configuration initConfig(File dirPath, String name)
    {
        final File cfgFile = new File(dirPath, String.format("Metallurgy%s.cfg", name));
        return new Configuration(cfgFile);
    }

    private boolean isSetEnabled(String setName, File configDir)
    {
        final File cfgFile = new File(configDir, "Metallurgy" + setName + ".cfg");
        final Configuration config = new Configuration(cfgFile);

        final boolean enabled = config.get("!Enable", "Enable " + setName + " Set", true).getBoolean(true);

        if (config.hasChanged())
            config.save();
        return enabled;
    }

    @ForgeSubscribe(priority = EventPriority.HIGHEST)
    public void postInit(NativePluginStartupEvent.Post event)
    {
        final File configDir = event.getMetallurgyConfigDir();
        if (isSetEnabled("Base", configDir) && baseSet.getOreInfo("Steel").getHelmet() != null)
        {
            ((MetallurgyTabs) baseTab).setIconItem(baseSet.getOreInfo("Steel").getHelmet().itemID);
        }
        if (isSetEnabled("Precious", configDir) && preciousSet.getOreInfo("Platinum").getHelmet() != null)
        {
            ((MetallurgyTabs) preciousTab).setIconItem(preciousSet.getOreInfo("Platinum").getHelmet().itemID);
        }
        if (isSetEnabled("Nether", configDir) && netherSet.getOreInfo("Sanguinite").getHelmet() != null)
        {
            ((MetallurgyTabs) netherTab).setIconItem(netherSet.getOreInfo("Sanguinite").getHelmet().itemID);
        }
        if (isSetEnabled("Fantasy", configDir) && fantasySet.getOreInfo("Tartarite").getHelmet() != null)
        {
            ((MetallurgyTabs) fantasyTab).setIconItem(fantasySet.getOreInfo("Tartarite").getHelmet().itemID);
        }
        if (isSetEnabled("Ender", configDir) && enderSet.getOreInfo("Desichalkos").getHelmet() != null)
        {
            ((MetallurgyTabs) enderTab).setIconItem(enderSet.getOreInfo("Desichalkos").getHelmet().itemID);
        }

        final Logger log = event.getMetallurgyLog();
        createMidasiumRecipes(log);
        IndustrialCraftIntegration.init(log);
        ComputerCraftIntegration.init(log);
    }

    @ForgeSubscribe(priority = EventPriority.HIGHEST)
    public void preInit(NativePluginStartupEvent.Pre event)
    {
        final File configDir = event.getMetallurgyConfigDir();
        baseConfig = initConfig(configDir, "Base");
        utilityConfig = initConfig(configDir, "Utility");
        fantasyConfig = initConfig(configDir, "Fantasy");

        oreFinderID = baseConfig.get("Debug", "OreFinderID", oreFinderID).getInt(oreFinderID);
        oreFinderEnabled = baseConfig.get("Debug", "OreFinderEnabled", oreFinderEnabled).getBoolean(oreFinderEnabled);
        
        if(baseConfig.hasChanged())
        {
        	baseConfig.save();
        }

        GameRegistry.registerFuelHandler(new IFuelHandler()
        {
            @Override
            public int getBurnTime(ItemStack fuel)
            {
                if(fuel.getItem() == netherSet.getOreInfo("Ignatius").getDustItem()) {
                        return 3200;
                }

                if(fuel.getItem() == netherSet.getOreInfo("Vulcanite").getDustItem()) {
                    return 32000;
                }
                
                return 0;
            }
        });

        if (isSetEnabled("Base", configDir))
        {
            baseTab = new MetallurgyTabs("Metallurgy: Base");
        }
        else
        {
            baseTab = CreativeTabs.tabBlock;
        }
        if (isSetEnabled("Precious", configDir))
        {
            preciousTab = new MetallurgyTabs("Metallurgy: Precious");
        }
        if (isSetEnabled("Nether", configDir))
        {
            netherTab = new MetallurgyTabs("Metallurgy: Nether");
        }
        if (isSetEnabled("Fantasy", configDir))
        {
            fantasyTab = new MetallurgyTabs("Metallurgy: Fantasy");
        }
        if (isSetEnabled("Ender", configDir))
        {
            enderTab = new MetallurgyTabs("Metallurgy: Ender");
        }
        if (isSetEnabled("Utility", configDir))
        {
            utilityTab = new MetallurgyTabs("Metallurgy: Utility");
        }

        LanguageRegistry.instance().addStringLocalization("itemGroup.Metallurgy: Base", "Metallurgy: Base");
        LanguageRegistry.instance().addStringLocalization("itemGroup.Metallurgy: Precious", "Metallurgy: Precious");
        LanguageRegistry.instance().addStringLocalization("itemGroup.Metallurgy: Nether", "Metallurgy: Nether");
        LanguageRegistry.instance().addStringLocalization("itemGroup.Metallurgy: Fantasy", "Metallurgy: Fantasy");
        LanguageRegistry.instance().addStringLocalization("itemGroup.Metallurgy: Utility", "Metallurgy: Utility");
        LanguageRegistry.instance().addStringLocalization("itemGroup.Metallurgy: Ender", "Metallurgy: Ender");

        String filepath = "assets/metallurgy/data";
        final URL metalSetsResource = Resources.getResource(filepath + "/spreadsheet.csv");
        final URL itemDataResource = Resources.getResource(filepath + "/Items.csv");
        final MetalInfoDatabase dbMetal = event.getMetalDatabase();
        try
        {
            dbMetal.loadMetalSet(Resources.newReaderSupplier(metalSetsResource, Charsets.UTF_8));
            dbMetal.loadItemData(utilityConfig, Resources.newReaderSupplier(itemDataResource, Charsets.UTF_8), utilityTab);
        }
        catch (IOException e)
        {
            event.getMetallurgyLog().log(Level.SEVERE, "Internal data resource corrupt of missing. Check integrity of mod archive.", e);
        }

        utilityConfig.save();

        baseSet = new MetalSet("Base", dbMetal.getDataForSet("Base"), baseTab, dbMetal, configDir);
        preciousSet = new MetalSet("Precious", dbMetal.getDataForSet("Precious"), preciousTab, dbMetal, configDir);
        netherSet = new MetalSet("Nether", dbMetal.getDataForSet("Nether"), netherTab, dbMetal, configDir);
        fantasySet = new MetalSet("Fantasy", dbMetal.getDataForSet("Fantasy"), fantasyTab, dbMetal, configDir);
        enderSet = new MetalSet("Ender", dbMetal.getDataForSet("Ender"), enderTab, dbMetal, configDir);
        utilitySet = new MetalSet("Utility", dbMetal.getDataForSet("Utility"), utilityTab, dbMetal, configDir);

        dustIron = new ItemMetallurgy(5100).setTextureName("Metallurgy:Vanilla/IronDust").setUnlocalizedName("Metallurgy:Vanilla/IronDust")
                .setCreativeTab(CreativeTabs.tabMaterials);
        dustGold = new ItemMetallurgy(5101).setTextureName("Metallurgy:Vanilla/GoldDust").setUnlocalizedName("Metallurgy:Vanilla/GoldDust")
                .setCreativeTab(CreativeTabs.tabMaterials);

        if (isSetEnabled("Utility", configDir))
        {
            utilityConfig.load();
            createUtilityItems(event.getMetallurgyInstance(), configDir, dbMetal);
            utilityConfig.save();
        }
    }
}
