package com.ling.blueDungeon.worldGen;

import com.ling.blueDungeon.block.ModBlocks;
import com.ling.blueDungeon.tileenitity.ExplodingSpawner;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.DungeonHooks;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created on 25/11/2016 part of com.ling.blueDungeon.worldGen.
 * 6749494716693856388
 */
public class DungeonWorldGen extends WorldGenerator
{
    public static ArrayList<BlockPos> chunks = new ArrayList<BlockPos>();

    private World genWorld;
    private Random rand;
    private int size;

    public DungeonWorldGen(int size){
        this.size = size;
    }

    public DungeonWorldGen(){
        this.size = 3;
    }

    @Override
    public boolean generate(World world, Random random, BlockPos blockPos)
    {
        int chunkX = blockPos.getX();
        int chunkZ = blockPos.getZ();
        int y = blockPos.getY();
        int height = 4;
        int chance;
        System.out.println("Generated at " + chunkX + " " + y + " " + chunkZ);

        genWorld = world;
        rand = random;


        genAreaRoof(chunkX, y, chunkZ, 9*size, height);
        clearSpace(chunkX, y, chunkZ, 9*size, height);
        genEmptyRoom(chunkX+(9), y, chunkZ+9, 12, height, 4);
        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                chance = random.nextInt(10);
                if (chance <= 6){
                    genRoomLayout1(chunkX+(9*j), y, chunkZ+(9*i), 9, height, 4);
                }else if (chance <= 8){
                    genEmptyRoom(chunkX+(9*j), y, chunkZ+(9*i), 9, height, 4);
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
        genWorld.setBlockState(spawnerPos, ModBlocks.exploding_spawner.getDefaultState(), 2);
        TileEntity sp = genWorld.getTileEntity(spawnerPos);
        if (sp instanceof ExplodingSpawner){
            ((ExplodingSpawner)sp).getSpawnerBaseLogic().setEntityName(DungeonHooks.getRandomDungeonMob(rand));
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
                if (i==range/2 && j==range/2){ // offset of 1 from the boarder
                    for (int k = 1; k < height+1; k++)
                    {
                        setBlock(x+i+range/4, y+k, z+j, Blocks.STONEBRICK);
                        setBlock(x+i-range/4, y+k, z+j, Blocks.STONEBRICK);
                        setBlock(x+i, y+k, z+j-range/4, Blocks.STONEBRICK);
                        setBlock(x+i, y+k, z+j+range/4, Blocks.STONEBRICK);
                    }
                    int chance = rand.nextInt(10);
                    // so there is a chance there will done but there is a chance there will be 4
                    if (chance > 2){
                        createMobSpawner(x+i+range/4, y, z+j);
                    }else if (chance > 4){
                        createMobSpawner(x+i-range/4, y, z+j);
                    }else if (chance > 6){
                        createMobSpawner(x+i, y, z+j+range/4);
                    }else if (chance > 8){
                        createMobSpawner(x+i, y, z+j+range/4);
                    }
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
                        setBlock(x+i, y+k, z+j, Blocks.AIR);
                    }
                }
            }
        }
    }
}
