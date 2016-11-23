package com.ling.test.worldGen;

import com.ling.test.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.DungeonHooks;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created on 23/11/2016 part of com.ling.test.worldGen.
 */
public class WorldGenDungeon implements IWorldGenerator
{
    private World genWorld;
    private Random rand;

    private static ArrayList<BlockPos> chunks = new ArrayList<BlockPos>();

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
                            setBlock(x+i, y+k, z+j, Blocks.AIR);
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

    private void genBasicRoom(int x, int y, int z, int range, int height, int doors){
        int doorCount = 0;
        for (int i = 0; i < range; i++)
        {
            for (int j = 0; j < range; j++)
            {
                setBlock(x+i, 10, z+j, Blocks.COBBLESTONE);
                if (j==0 || i==range-1 || i==0 || j==range-1 ){

                    if (((i==0 && j==range/2) || (j==0 && i==range/2) || (i==range-1 && j==range/2) || (j==range-1 && i==range/2)) && (doors>0 && doorCount <= doors)){
                        for (int k = 1; k < height; k++)
                        {
                            setBlock(x+i, y+k, z+j, Blocks.AIR);
                        }
                        setBlock(x+i, y+height, z+j, Blocks.COBBLESTONE);
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

    private void genRoomLayout1(int x, int y, int z, int range, int height, int doors){
        /*
        [      ]
        [   *  ]
        [      ]
         */
        int doorCount = 0;
        for (int i = 0; i < range; i++)
        {
            for (int j = 0; j < range; j++)
            {
                if (j==0 || i==range-1 || i==0 || j==range-1 ){

                    if (((i==0 && j==range/2) || (j==0 && i==range/2) || (i==range-1 && j==range/2) || (j==range-1 && i==range/2)) && (doors>0 && doorCount <= doors)){
                        for (int k = 1; k < height+1; k++)
                        {
                            setBlock(x+i, y+k, z+j, Blocks.AIR); // doors
                        }
                        doorCount ++;
                    }else{
                        for (int k = 1; k < height+1; k++)
                        {
                            // should have holes in the walls for spiders
                            setBlock(x+i, y+k, z+j, Blocks.COBBLESTONE); // wall
                        }
                    }
                }else if (i==range/2 && j==range/2){
                    createMobSpawner(x+i, y+1, z+j);
                    createCrate(x+i, y+2, z+j); // middle of the room
                    createCrate(x+i-1, y+1, z+j); // middle of the room
                    createCrate(x+i+1, y+1, z+j); // middle of the room
                    createCrate(x+i, y+1, z+j-1); // middle of the room
                    createCrate(x+i, y+1, z+j+1); // middle of the room
                }
            }
        }
    }

    private void createMobSpawner(int x, int y, int z){
        BlockPos spawnerPos = new BlockPos(x, y, z);
        genWorld.setBlockState(spawnerPos, Blocks.MOB_SPAWNER.getDefaultState(), 2);
        TileEntity sp = genWorld.getTileEntity(spawnerPos);
        if (sp instanceof TileEntityMobSpawner){
            ((TileEntityMobSpawner)sp).getSpawnerBaseLogic().setEntityName(DungeonHooks.getRandomDungeonMob(rand));
        }
    }

    private void createCrate(int x, int y, int z){
        int chance = rand.nextInt(10);
        if (chance <= 5){
            setBlock(x, y, z, ModBlocks.crate_basic);
        }else if (chance <= 9){
            setBlock(x, y, z, ModBlocks.crate_uncommon);
        }else{
            setBlock(x, y, z, ModBlocks.crate_rare);
        }
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator iChunkGenerator, IChunkProvider iChunkProvider)
    {
        rand = random;
        genWorld = world;
        // world.getBiomeForCoordsBody(new BlockPos(chunkX, chunkZ, 1))
        if (chunks.size() == 0){
            chunks.add(new BlockPos(chunkX*15, 10, chunkZ*15));
        }
        if (random.nextDouble() < 0.005 && world.provider.getDimension() == 0) {
            chunkX *= 15;
            chunkZ *= 15;
            if (goodLocation(chunkX, chunkZ)){

                for (int i = 0; i < 3; i++)
                {
                    for (int j = 0; j < 3; j++)
                    {
                        genRoomLayout1(chunkX+(9*j), 10, chunkZ+(9*i), 9, 4, 3);
                    }

                }

                genRoomLayout1(chunkX, 10, chunkZ, 15, 4, 3);

                for (int i = 0; i < chunkX+(9); i++)
                {
                    for (int j = 0; j < chunkZ+(9); j++)
                    {
                        setBlock(chunkX+i, 10, chunkZ+j, Blocks.COBBLESTONE);
                        setBlock(chunkX+i, 14, chunkZ+j, Blocks.COBBLESTONE);
                    }
                }


                chunks.add(new BlockPos(chunkX, 10, chunkZ));

            }
        }
    }

    /**
     *
     * @param x
     * @param z
     * @return true if all the current positions are not within 500+rnd(250) blocks of the dungoen
     */
    private boolean goodLocation(int x, int z){
        boolean good = true;
        int count = 0;
        while (good && count < chunks.size()){
            // we only want the distance on the x z axis
            if (chunks.get(count).getDistance(x, 10, z) < rand.nextInt(250)+500){
                good = false;
            }
            count ++;
        }
        return good;
    }
}
