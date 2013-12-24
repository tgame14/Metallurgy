package rebelkeithy.mods.metallurgy.vanilla;

import java.io.File;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import rebelkeithy.mods.keithyutils.reflection.Reflector;
import rebelkeithy.mods.metallurgy.core.metalsets.MetalSet;
import rebelkeithy.mods.metallurgy.core.plugin.event.NativePluginStartupEvent;

import com.google.common.collect.Maps;

import cpw.mods.fml.common.SidedProxy;

public class MetallurgyVanilla
{
    @SidedProxy(clientSide = "rebelkeithy.mods.metallurgy.vanilla.ClientProxy", serverSide = "rebelkeithy.mods.metallurgy.vanilla.CommonProxy")
    public static CommonProxy proxy;

    public static MetalSet vanillaSet;

    @ForgeSubscribe
    public void Init(NativePluginStartupEvent.Init event)
    {
        vanillaSet.init();
        VanillaAddons.load();
    }

    @ForgeSubscribe
    public void preInit(NativePluginStartupEvent.Pre event)
    {

        final Map<String, Map<String, String>> vanillaList = Maps.newHashMap();
        for (Entry<String, Map<String, String>> entry: event.getMetalDatabase().getDataForSet("Vanilla").entrySet())
            if (!entry.getKey().equals("Wood/Leather") && !entry.getKey().equals("Stone/Chainmail"))
                vanillaList.put(entry.getKey(), entry.getValue());
        final File configDir = event.getMetallurgyConfigDir();
        vanillaSet = new MetalSet("Vanilla", vanillaList, CreativeTabs.tabBlock, event.getMetalDatabase(), configDir);

        VanillaAddons.init(configDir);

        vanillaSet.initConfig(configDir);

        final File cfgFile = new File(configDir, "MetallurgyVanilla.cfg");
        final Configuration config = new Configuration(cfgFile);

        if (config.get("!enable", "Enable Texture Overrides", true).getBoolean(true))
        {
            Reflector.setItemTexture(Item.swordWood, "Metallurgy:Vanilla/SwordWood");
            Reflector.setItemTexture(Item.shovelWood, "Metallurgy:Vanilla/ShovelWood");
            Reflector.setItemTexture(Item.hoeWood, "Metallurgy:Vanilla/HoeWood");
            Reflector.setItemTexture(Item.axeWood, "Metallurgy:Vanilla/AxeWood");
            Reflector.setItemTexture(Item.pickaxeWood, "Metallurgy:Vanilla/PickaxeWood");
            Reflector.setItemTexture(Item.swordStone, "Metallurgy:Vanilla/SwordStone");
            Reflector.setItemTexture(Item.shovelStone, "Metallurgy:Vanilla/ShovelStone");
            Reflector.setItemTexture(Item.hoeStone, "Metallurgy:Vanilla/HoeStone");
            Reflector.setItemTexture(Item.axeStone, "Metallurgy:Vanilla/AxeStone");
            Reflector.setItemTexture(Item.pickaxeStone, "Metallurgy:Vanilla/PickaxeStone");
            Reflector.setItemTexture(Item.swordIron, "Metallurgy:Vanilla/SwordIron");
            Reflector.setItemTexture(Item.shovelIron, "Metallurgy:Vanilla/ShovelIron");
            Reflector.setItemTexture(Item.hoeIron, "Metallurgy:Vanilla/HoeIron");
            Reflector.setItemTexture(Item.axeIron, "Metallurgy:Vanilla/AxeIron");
            Reflector.setItemTexture(Item.pickaxeIron, "Metallurgy:Vanilla/PickaxeIron");
            Reflector.setItemTexture(Item.swordGold, "Metallurgy:Vanilla/SwordGold");
            Reflector.setItemTexture(Item.shovelGold, "Metallurgy:Vanilla/ShovelGold");
            Reflector.setItemTexture(Item.hoeGold, "Metallurgy:Vanilla/HoeGold");
            Reflector.setItemTexture(Item.axeGold, "Metallurgy:Vanilla/AxeGold");
            Reflector.setItemTexture(Item.pickaxeGold, "Metallurgy:Vanilla/PickaxeGold");
            Reflector.setItemTexture(Item.swordDiamond, "Metallurgy:Vanilla/SwordDiamond");
            Reflector.setItemTexture(Item.shovelDiamond, "Metallurgy:Vanilla/ShovelDiamond");
            Reflector.setItemTexture(Item.hoeDiamond, "Metallurgy:Vanilla/HoeDiamond");
            Reflector.setItemTexture(Item.axeDiamond, "Metallurgy:Vanilla/AxeDiamond");
            Reflector.setItemTexture(Item.pickaxeDiamond, "Metallurgy:Vanilla/PickaxeDiamond");

            Reflector.setItemTexture(Item.helmetIron, "Metallurgy:Vanilla/HelmetIron");
            Reflector.setItemTexture(Item.plateIron, "Metallurgy:Vanilla/ChestIron");
            Reflector.setItemTexture(Item.legsIron, "Metallurgy:Vanilla/LegsIron");
            Reflector.setItemTexture(Item.bootsIron, "Metallurgy:Vanilla/BootsIron");
            Reflector.setItemTexture(Item.helmetGold, "Metallurgy:Vanilla/HelmetGold");
            Reflector.setItemTexture(Item.plateGold, "Metallurgy:Vanilla/ChestGold");
            Reflector.setItemTexture(Item.legsGold, "Metallurgy:Vanilla/LegsGold");
            Reflector.setItemTexture(Item.bootsGold, "Metallurgy:Vanilla/BootsGold");
            Reflector.setItemTexture(Item.helmetDiamond, "Metallurgy:Vanilla/HelmetDiamond");
            Reflector.setItemTexture(Item.plateDiamond, "Metallurgy:Vanilla/ChestDiamond");
            Reflector.setItemTexture(Item.legsDiamond, "Metallurgy:Vanilla/LegsDiamond");
            Reflector.setItemTexture(Item.bootsDiamond, "Metallurgy:Vanilla/BootsDiamond");
        }

        config.save();

        // MinecraftForge.ORE_GEN_BUS.register(new VanillaTextureReplacer());
        MinecraftForge.ORE_GEN_BUS.register(new VanillaOreInhibitor());

    }
}
