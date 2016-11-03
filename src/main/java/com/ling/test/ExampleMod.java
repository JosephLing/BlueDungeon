package com.ling.test;

import com.ling.test.block.ModBlocks;
import com.ling.test.item.ModItems;
import com.ling.test.proxy.CommonProxy;
import com.ling.test.structure.ModGenDungeon;
import com.ling.test.tabs.CreativeModTab;
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
    public static final String MODID = "test";
    public static final String VERSION = "1.0";
    public static final String NAME = "Test Mod";


    @SidedProxy(clientSide = "com.ling.test.proxy.ClientProxy", serverSide = "com.ling.test.proxy.CommonProxy")
    public static CommonProxy proxy;


    @Mod.Instance
    public static ExampleMod instance;

    public static CreativeModTab creativeTab1;

    @EventHandler
    public void preinit(FMLPreInitializationEvent event){
        creativeTab1 = new CreativeModTab(CreativeTabs.getNextID(), ExampleMod.MODID+"tab");
        ModBlocks.preinit();
        ModItems.preinit();
        ModGenDungeon.init();
        proxy.pretinit(event);
    }


    @EventHandler
    public void init(FMLInitializationEvent event){
        proxy.init(event);
    }


    @EventHandler
    public void postinit(FMLPostInitializationEvent event){
        proxy.postinit(event);
    }
}
