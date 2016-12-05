package com.ling.blueDungeon.worldGen;

import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created on 23/11/2016 part of com.ling.blueDungeon.worldGen.
 */
public class ModWorldGen
{
    public static void init(){
        // ores

        // structures
        GameRegistry.registerWorldGenerator(new WorldGenDungeon(), 1);
        GameRegistry.registerWorldGenerator(new WorldGenVillageDungeon(), 1);
    }
}
