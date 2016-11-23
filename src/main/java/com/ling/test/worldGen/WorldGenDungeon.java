package com.ling.test.worldGen;

import com.ling.test.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

/**
 * Created on 23/11/2016 part of com.ling.test.worldGen.
 */
public class WorldGenDungeon implements IWorldGenerator
{
    private World genWorld;
    private Random rand;

    private static Biome[] notSpawnBiome = {
            Biomes.BEACH, Biomes.EXTREME_HILLS,
            Biomes.EXTREME_HILLS_EDGE,
            Biomes.EXTREME_HILLS_WITH_TREES,
            Biomes.MUTATED_EXTREME_HILLS,
            Biomes.MUTATED_EXTREME_HILLS_WITH_TREES
    };


    public void setBlock(int x, int y, int z, Block block){
        genWorld.setBlockState(new BlockPos(x, y, z), block.getDefaultState(), 2);
    }

    private void genPlatform(int x, int y, int z, int range, int height, int doors){
        int doorCount = 0;
        for (int i = 0; i < range; i++)
        {
            for (int j = 0; j < range; j++)
            {
                setBlock(x+i, 10, z+j, Blocks.COBBLESTONE);
                if (j==0 || i==range-1 || i==0 || j==range-1 ){

                    if (((i==0 && j==range/2) || (j==0 && i==range/2) || (i==range-1 && j==range/2) || (j==range-1 && i==range/2)) && (doors>0 && doorCount <= doors)){
                        for (int k = 1; k < height+1; k++)
                        {
                            setBlock(x+i, y+k, z+j, Blocks.GOLD_BLOCK);
                        }
                        doorCount ++;
                    }else{
                        for (int k = 1; k < height+1; k++)
                        {
                            setBlock(x+i, y+k, z+j, Blocks.COBBLESTONE);
                        }
                    }
                }
            }
        }
    }


    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator iChunkGenerator, IChunkProvider iChunkProvider)
    {
        rand = random;
        genWorld = world;
        chunkX *= 15;
        chunkZ *= 15;
        // world.getBiomeForCoordsBody(new BlockPos(chunkX, chunkZ, 1))
        if (random.nextDouble() < 0.1 && world.provider.getDimension() == 0) {
            genPlatform(chunkX, 10, chunkZ, 15, 4, 3);
        }
    }
}
