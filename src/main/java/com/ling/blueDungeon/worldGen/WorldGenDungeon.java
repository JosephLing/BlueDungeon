package com.ling.blueDungeon.worldGen;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created on 23/11/2016 part of com.ling.blueDungeon.worldGen.
 */
public class WorldGenDungeon implements IWorldGenerator
{
    private static DungeonWorldGen BASIC_DUNEGEON = new DungeonWorldGen();



    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator iChunkGenerator, IChunkProvider iChunkProvider)
    {
        // world.getBiomeForCoordsBody(new BlockPos(chunkX, chunkZ, 1))
        if (DungeonWorldGen.chunks.size() == 0){
            DungeonWorldGen.chunks.add(new BlockPos(chunkX*15, 10, chunkZ*15));
        }
        if (random.nextDouble() < 0.01 && world.provider.getDimension() == 0) {
            chunkX *= 16;
            chunkZ *= 16;
            if (WorldGenDungeon.goodLocation(DungeonWorldGen.chunks, chunkX, chunkZ, random)){
                BASIC_DUNEGEON.generate(world, random, new BlockPos(chunkX, random.nextInt(10)+50, chunkZ));
                DungeonWorldGen.chunks.add(new BlockPos(chunkX, 50, chunkZ));
            }
        }
    }

    /**
     *
     * @param x
     * @param z
     * @return true if all the current positions are not within 500+rnd(250) blocks of the dungoen
     */
    private static boolean goodLocation(ArrayList<BlockPos> chunks, int x, int z, Random rand){
        boolean good = true;
        int count = 0;
        while (good && count < chunks.size()){
            // we only want the distance on the x z axis
            if (chunks.get(count).getDistance(x, 10, z) < rand.nextInt(250)+250){
                good = false;
            }
            count ++;
        }
        return good;
    }
}
