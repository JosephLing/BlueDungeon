package com.ling.blueDungeon.worldGen;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

import static net.minecraft.world.gen.structure.MapGenVillage.VILLAGE_SPAWN_BIOMES;

/**
 * Created on 23/11/2016 part of com.ling.blueDungeon.worldGen.
 */
public class WorldGenVillageDungeon implements IWorldGenerator
{
    private int distance = 32;
    private int minTownSeparation = 8;
    private static DungeonWorldGen BASIC_DUNEGEON = new DungeonWorldGen(4);



    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator iChunkGenerator, IChunkProvider iChunkProvider)
    {
        chunkX *= 16;
        chunkZ *= 16;
        int lvt_3_1_ = chunkX;
        int lvt_4_1_ = chunkZ;
        int p_canSpawnStructureAtCoords_1_ = chunkX;
        int p_canSpawnStructureAtCoords_2_ = chunkZ;
        if(p_canSpawnStructureAtCoords_1_ < 0) {
            p_canSpawnStructureAtCoords_1_ -= this.distance - 1;
        }

        if(p_canSpawnStructureAtCoords_2_ < 0) {
            p_canSpawnStructureAtCoords_2_ -= this.distance - 1;
        }

        int lvt_5_1_ = p_canSpawnStructureAtCoords_1_ / this.distance;
        int lvt_6_1_ = p_canSpawnStructureAtCoords_2_ / this.distance;
        lvt_5_1_ *= this.distance;
        lvt_6_1_ *= this.distance;
        lvt_5_1_ += random.nextInt(this.distance - 8);
        lvt_6_1_ += random.nextInt(this.distance - 8);
        if(lvt_3_1_ == lvt_5_1_ && lvt_4_1_ == lvt_6_1_) {
            boolean lvt_8_1_ = world.getBiomeProvider().areBiomesViable(lvt_3_1_ * 16 + 8, lvt_4_1_ * 16 + 8, 0, VILLAGE_SPAWN_BIOMES);
            if(lvt_8_1_) {
                BASIC_DUNEGEON.generate(world, random, new BlockPos(chunkX, random.nextInt(10)+50, chunkZ));
            }
        }

    }


}
