package com.ling.test.worldGen;

import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created on 23/11/2016 part of com.ling.test.worldGen.
 */
public class ModWorldGen
{
    public static void init(){
        // ores

        // structures
        GameRegistry.registerWorldGenerator(new WorldGenDungeon(), 1);
    }
}
