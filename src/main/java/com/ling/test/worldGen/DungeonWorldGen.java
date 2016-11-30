package com.ling.test.worldGen;

import com.ling.test.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.DungeonHooks;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created on 25/11/2016 part of com.ling.test.worldGen.
 * 6749494716693856388
 */
public class DungeonWorldGen extends WorldGenerator
{
    public static ArrayList<BlockPos> chunks = new ArrayList<BlockPos>();

    private World genWorld;
    private Random rand;

    @Override
    public boolean generate(World world, Random random, BlockPos blockPos)
    {
        int chunkX = blockPos.getX();
        int chunkZ = blockPos.getZ();
        int y = blockPos.getY();
        int height = 4;
        int chance;

        genWorld = world;
        rand = random;


        genAreaRoof(chunkX, y, chunkZ, 9, height);
        clearSpace(chunkX, y, chunkZ, 9, height);
        for (int i = 0; i < 1; i++)
        {
            for (int j = 0; j < 1; j++)
            {
                chance = random.nextInt(10);
                if (chance <= 5){
                    genRoomLayout2(chunkX+(9*j), y, chunkZ+(9*i), 9, height, 4);
                }else if (chance <= 7){
                    genRoomLayout2(chunkX+(9*j), y, chunkZ+(9*i), 9, height, 4);
                }else{
                    genRoomLayout2(chunkX+(9*j), y, chunkZ+(9*i), 9, height, 4);
                }
            }
        }
        return true;
    }


    private void setBlock(int x, int y, int z, Block block){
        genWorld.setBlockState(new BlockPos(x, y, z), block.getDefaultState(), 2);
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

    private void createWall(int x, int y, int z){
        int chance = rand.nextInt(10);
        if (chance <= 3){
            setBlock(x, y, z, Blocks.MOSSY_COBBLESTONE);
        }else if (chance <= 8){
            setBlock(x, y, z, Blocks.COBBLESTONE);
        }else {
            setBlock(x, y, z, Blocks.AIR); // for spiders ahaha
        }
    }

    private void clearSpace(int x, int y, int z, int range, int height){
        for (int i = 0; i < range; i++)
        {
            for (int j = 0; j < range; j++)
            {
                for (int k = 1; k < height; k++)
                {
                    setBlock(x+i, y+k, z+j, Blocks.AIR); // glass for testing
                }
            }
        }
    }

    private void genAreaRoof(int x, int y, int z, int range, int height){
        for (int i = 0; i < range; i++)
        {
            for (int j = 0; j < range; j++)
            {
                setBlock(x+i, y, z+j, Blocks.COBBLESTONE);
                setBlock(x+i, y+height, z+j, Blocks.COBBLESTONE);
            }
        }
    }

    private void genRoomLayout2(int x, int y, int z, int range, int height, int doors){
        int doorCount = 0;
        for (int i = 0; i < range; i++)
        {
            for (int j = 0; j < range; j++)
            {
                if (j==0 || i==range-1 || i==0 || j==range-1){
                    for (int k = 1; k < height+1; k++)
                    {
                        // should have holes in the walls for spiders
                        createWall(x+i, y+k, z+j); // wall
                    }
                }
                if (((i==0 && j==range/2) || (j==0 && i==range/2) || (i==range-1 && j==range/2) || (j==range-1 && i==range/2)) && (doors>0 && doorCount <= doors)){
                    doorCount ++;
                    for (int k = 1; k < height+1; k++)
                    {
                        setBlock(x+i, y+k, z+j, Blocks.AIR);
                    }
                }
                if (j % range-2 == 0 && i % range-2 == 0){ // offset of 1 from the boarder
                    setBlock(x+i, y, z+j, Blocks.GOLD_BLOCK);
                }
            }
        }
    }

    private void genRoomLayout1(int x, int y, int z, int range, int height, int doors){
        int doorCount = 0;
        for (int i = 0; i < range; i++)
        {
            for (int j = 0; j < range; j++)
            {
                if (j==0 || i==range-1 || i==0 || j==range-1){
                    for (int k = 1; k < height+1; k++)
                    {
                        // should have holes in the walls for spiders
                        createWall(x+i, y+k, z+j); // wall
                    }
                }
                if (((i==0 && j==range/2) || (j==0 && i==range/2) || (i==range-1 && j==range/2) || (j==range-1 && i==range/2)) && (doors>0 && doorCount <= doors)){
                    doorCount ++;
                    for (int k = 1; k < height+1; k++)
                    {
                        setBlock(x+i, y+k, z+j, Blocks.AIR);
                    }
                }
                if (i==range/2 && j==range/2){
                    createMobSpawner(x+i, y+1, z+j);
                    createCrate(x+i, y+2, z+j);
                    createCrate(x+i-1, y+1, z+j);
                    createCrate(x+i+1, y+1, z+j);
                    createCrate(x+i, y+1, z+j-1);
                    createCrate(x+i, y+1, z+j+1);
                }
            }
        }
    }

    private void genEmptyRoom(int x, int y, int z, int range, int height, int doors){
        int doorCount = 0;
        for (int i = 0; i < range; i++)
        {
            for (int j = 0; j < range; j++)
            {
                if (j==0 || i==range-1 || i==0 || j==range-1){
                    for (int k = 1; k < height+1; k++)
                    {
                        // should have holes in the walls for spiders
                        createWall(x+i, y+k, z+j); // wall
                    }
                }
                if (((i==0 && j==range/2) || (j==0 && i==range/2) || (i==range-1 && j==range/2) || (j==range-1 && i==range/2)) && (doors>0 && doorCount <= doors)){
                    doorCount ++;
                    for (int k = 1; k < height+1; k++)
                    {
                        setBlock(x+i, y+k, z+j, Blocks.DIAMOND_BLOCK);
                    }
                }
            }
        }
    }

//    private void genEmptyRoom(int x, int y, int z, int range, int height, int doors){
//        int doorCount = 0;
//        for (int i = 0; i < range; i++)
//        {
//            for (int j = 0; j < range; j++)
//            {
//                setBlock(x+i, 10, z+j, Blocks.COBBLESTONE);
//                if (j==0 || i==range-1 || i==0 || j==range-1 ){
//
//                    if (((i==0 && j==range/2) || (j==0 && i==range/2) || (i==range-1 && j==range/2) || (j==range-1 && i==range/2)) && (doors>0 && doorCount <= doors)){
//                        for (int k = 1; k < height; k++)
//                        {
//                            setBlock(x+i, y+k, z+j, Blocks.AIR);
//                        }
//                        doorCount ++;
//                    }else{
//                        for (int k = 1; k < height+1; k++)
//                        {
//                            createWall(x+i, y+k, z+j);
//                        }
//                    }
//                }else{
//                    for (int k = 1; k < height; k++)
//                    {
//                        setBlock(x+i, y+k, z+j, Blocks.AIR);
//                    }
//                }
//            }
//        }
//    }
//
}
