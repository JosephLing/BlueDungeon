package com.ling.blueDungeon.tileenitity;

import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created on 02/12/2016 part of com.ling.blueDungeon.tileenitity.
 */
public class ModTileEntities
{
    public static void preinit(){
        GameRegistry.registerTileEntity(ExplodingSpawner.class, "exploding_spawner");
    }
}
