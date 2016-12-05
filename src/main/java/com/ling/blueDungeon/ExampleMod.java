package com.ling.blueDungeon;

import com.ling.blueDungeon.block.ModBlocks;
import com.ling.blueDungeon.item.ModItems;
import com.ling.blueDungeon.proxy.CommonProxy;
import com.ling.blueDungeon.tabs.CreativeModTab;
import com.ling.blueDungeon.tileenitity.ModTileEntities;
import com.ling.blueDungeon.worldGen.ModWorldGen;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ExampleMod.MODID, version = ExampleMod.VERSION, name=ExampleMod.NAME)
public class ExampleMod
{
    public static final String MODID = "bluedungeon";
    public static final String VERSION = "1.0";
    public static final String NAME = "Blue Dungeon";


    @SidedProxy(clientSide = "com.ling.blueDungeon.proxy.ClientProxy", serverSide = "com.ling.blueDungeon.proxy.CommonProxy")
    public static CommonProxy proxy;


    @Mod.Instance
    public static ExampleMod instance;

    public static CreativeModTab creativeTab1;

    @EventHandler
    public void preinit(FMLPreInitializationEvent event){
        creativeTab1 = new CreativeModTab(CreativeTabs.getNextID(), ExampleMod.MODID+"tab");
        ModBlocks.preinit();
        ModTileEntities.preinit();
        ModItems.preinit();
        proxy.pretinit(event);
    }


    @EventHandler
    public void init(FMLInitializationEvent event){
        proxy.init(event);
        ModWorldGen.init();
    }


    @EventHandler
    public void postinit(FMLPostInitializationEvent event){
        proxy.postinit(event);
    }
}
