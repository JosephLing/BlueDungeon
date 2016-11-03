package com.ling.test.structure;

import net.minecraft.init.Biomes;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.StructureStart;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created on 02/11/2016 part of com.ling.test.structure.
 */
public class MapGenDungeon extends MapGenStructure
{
    public static String DungoenID = "test1";

    private int distance;
    private int size;
    private String name;
    private static List<Biome> VILLAGE_SPAWN_BIOMES = Arrays.asList(new Biome[]{
            Biomes.PLAINS, Biomes.DESERT, Biomes.SAVANNA, Biomes.TAIGA});

    public MapGenDungeon(String dungeonName, int dist, int size)
    {
        super();
        distance = dist;
        name = dungeonName;
        this.size = size;
        System.out.println("Called");
        System.out.println("Called");
        System.out.println("Called");
        System.out.println("Called");
        System.out.println("Called");
    }

    @Override
    public String getStructureName()
    {
        return name;
    }



    @Override
    protected StructureStart getStructureStart(int p_getStructureStart_1_, int p_getStructureStart_2_) {
        System.out.println("SPAWNING");
        System.out.println("SPAWNING");
        System.out.println("SPAWNING");
        System.out.println("SPAWNING");
        System.out.println("SPAWNING");
        System.out.println("SPAWNING");
        System.out.println("SPAWNING");
        return new MapGenDungeon.Start(this.worldObj, this.rand, p_getStructureStart_1_, p_getStructureStart_2_, this.size);
    }


    protected boolean canSpawnStructureAtCoords(int p_canSpawnStructureAtCoords_1_, int p_canSpawnStructureAtCoords_2_) {
        System.out.println("Called 2");
        int lvt_3_1_ = p_canSpawnStructureAtCoords_1_;
        int lvt_4_1_ = p_canSpawnStructureAtCoords_2_;
        if(p_canSpawnStructureAtCoords_1_ < 0) {
            p_canSpawnStructureAtCoords_1_ -= this.distance - 1;
        }

        if(p_canSpawnStructureAtCoords_2_ < 0) {
            p_canSpawnStructureAtCoords_2_ -= this.distance - 1;
        }

        int lvt_5_1_ = p_canSpawnStructureAtCoords_1_ / this.distance;
        int lvt_6_1_ = p_canSpawnStructureAtCoords_2_ / this.distance;
        Random lvt_7_1_ = this.worldObj.setRandomSeed(lvt_5_1_, lvt_6_1_, 10387312);
        lvt_5_1_ *= this.distance;
        lvt_6_1_ *= this.distance;
        lvt_5_1_ += lvt_7_1_.nextInt(this.distance - 4);
        lvt_6_1_ += lvt_7_1_.nextInt(this.distance - 4);
        if(lvt_3_1_ == lvt_5_1_ && lvt_4_1_ == lvt_6_1_) {
            boolean lvt_8_1_ = this.worldObj.getBiomeProvider().areBiomesViable(lvt_3_1_ * 16 + 8, lvt_4_1_ * 16 + 8, 0, VILLAGE_SPAWN_BIOMES);
            if(lvt_8_1_) {
                System.out.println("can spawn");
                System.out.println("can spawn");
                System.out.println("can spawn");
                System.out.println("can spawn");
                System.out.println("can spawn");
                System.out.println("can spawn");
                return true;
            }
        }

        return false;
    }


    public static class Start extends StructureStart {
        private boolean hasMoreThanTwoComponents;

        public Start(World p_i2092_1_, Random p_i2092_2_, int p_i2092_3_, int p_i2092_4_, int size) {
            super(p_i2092_3_, p_i2092_4_);
            System.out.println("Creating dungone");
            System.out.println("Creating dungone");
            System.out.println("Creating dungone");
            System.out.println("Creating dungone");

        }
    }
}
