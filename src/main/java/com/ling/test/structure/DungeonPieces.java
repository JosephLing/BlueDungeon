package com.ling.test.structure;

import com.ling.test.ExampleMod;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.MapGenMineshaft;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;

import java.util.Random;

/**
 * Created on 02/11/2016 part of com.ling.test.structure.
 */
public class DungeonPieces
{
   public static void regestierDungeonPieces(){
       MapGenStructureIO.registerStructureComponent(com.ling.test.structure.DungeonPieces.Room.class, MapGenDungeon.DungoenID+"R1");
       MapGenStructureIO.registerStructureComponent(com.ling.test.structure.DungeonPieces.Corridor.class, MapGenDungeon.DungoenID+"C1");
       MapGenStructureIO.registerStructureComponent(com.ling.test.structure.DungeonPieces.TreasureRoom.class, MapGenDungeon.DungoenID+"T1");
   }

    abstract static class Peice extends StructureComponent{
        public Peice(){
            super();
            this.boundingBox = new StructureBoundingBox();
        }


        public Peice(Random rand, int p_i47137_3_, int p_i47137_4_) {

        }

        @Override
        protected void writeStructureToNBT(NBTTagCompound nbtTagCompound)
        {

        }

        @Override
        protected void readStructureFromNBT(NBTTagCompound nbtTagCompound)
        {

        }
    }

    public static class Room extends Peice{
        public Room(int x, int z, Random rand){
            super(rand, x, z);
            this.boundingBox = new StructureBoundingBox(
                    // min
                    x, 50, z,
                    // max
                    x + 7 + rand.nextInt(6), 54 + rand.nextInt(6), z + 7 + rand.nextInt(6));
        }


        public boolean addComponentParts(World world, Random random, StructureBoundingBox structureBoundingBox)
        {
            if(this.isLiquidInStructureBoundingBox(world, structureBoundingBox)) {
                return false;
            }else{
                this.fillWithBlocks(world, structureBoundingBox, 0, 0, 0, 2, 1, 10, Blocks.PLANKS.getDefaultState(), Blocks.PLANKS.getDefaultState(), false);
            }
            return true;
        }

    }

    public static class Corridor extends Peice{
        public Corridor(int x, int z, Random rand, EnumDirection facing){
            super(rand, x, z);

            switch (facing){
                case NORTH:
                    this.boundingBox = new StructureBoundingBox(
                            // min
                            x, 20, z,
                            // max
                            x + 7 + rand.nextInt(6), -54 - rand.nextInt(6), z + 7 + rand.nextInt(6));
                    break;

                case EAST:
                    this.boundingBox = new StructureBoundingBox(
                            // min
                            x, 20, z,
                            // max
                            x + 7 + rand.nextInt(6), 54 + rand.nextInt(6), z + 7 + rand.nextInt(6));
                    break;

                case SOUTH:
                    this.boundingBox = new StructureBoundingBox(
                            // min
                            x, 20, z,
                            // max
                            x + 7 + rand.nextInt(6), 54 + rand.nextInt(6), z + 7 + rand.nextInt(6));
                    break;

                case WEST:
                    this.boundingBox = new StructureBoundingBox(
                            // min
                            x, 20, z,
                            // max
                            x - 7 - rand.nextInt(6), 54 + rand.nextInt(6), z + 7 + rand.nextInt(6));
                    break;
            }
        }
        public boolean addComponentParts(World world, Random random, StructureBoundingBox structureBoundingBox)
        {
            if(this.isLiquidInStructureBoundingBox(world, structureBoundingBox)) {
                return false;
            }else{
                this.fillWithBlocks(world, structureBoundingBox, 0, 0, 0, 2, 1, 10, Blocks.PLANKS.getDefaultState(), Blocks.PLANKS.getDefaultState(), false);
            }
            return true;
        }
    }

    public static class TreasureRoom extends Peice{
        public TreasureRoom(int x, int z, Random rand){

            super(rand, x, z);
            this.boundingBox = new StructureBoundingBox(
                    // min
                    x, 10, z,
                    // max
                    x + 7 + rand.nextInt(6), 20 + rand.nextInt(6), z + 7 + rand.nextInt(6));
        }

        public boolean addComponentParts(World world, Random random, StructureBoundingBox structureBoundingBox)
        {
            if(this.isLiquidInStructureBoundingBox(world, structureBoundingBox)) {
                return false;
            }else{
                this.fillWithBlocks(world, structureBoundingBox, 0, 0, 0, 2, 1, 10, Blocks.PLANKS.getDefaultState(), Blocks.PLANKS.getDefaultState(), false);
            }
            return true;
        }


    }

    public enum EnumDirection
    {
        NORTH,
        SOUTH,
        EAST,
        WEST
    }
}




