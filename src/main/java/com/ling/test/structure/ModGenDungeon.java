package com.ling.test.structure;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.gen.feature.WorldGenEndIsland;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.MapGenStructureIO;

import java.util.Random;

/**
 * Created on 03/11/2016 part of com.ling.test.structure.
 */
public class ModGenDungeon
{
    public static void init(){
        System.out.println("creating dungone stuff");
        System.out.println("creating dungone stuff");
        System.out.println("creating dungone stuff");
        System.out.println("creating dungone stuff");
        System.out.println("creating dungone stuff");
        MapGenStructureIO.registerStructure(com.ling.test.structure.MapGenDungeon.Start.class,
                "ModDungone");
        DungeonPieces.regestierDungeonPieces();

    }
}