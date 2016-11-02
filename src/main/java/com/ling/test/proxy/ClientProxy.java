package com.ling.test.proxy;

import com.ling.test.block.ModBlocks;
import com.ling.test.item.ModItems;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * Created by Joe on 31/10/2016.
 */
public class ClientProxy extends CommonProxy {


    @Override
    public void pretinit(FMLPreInitializationEvent event) {

    }

    @Override
    public void init(FMLInitializationEvent event) {
        ModItems.regesterRenders();
        ModBlocks.registerRenders();
    }

    @Override
    public void postinit(FMLPostInitializationEvent event) {

    }
}
