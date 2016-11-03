package com.ling.test.structure;

import net.minecraft.world.World;
import net.minecraft.world.gen.structure.MapGenMineshaft;
import net.minecraft.world.gen.structure.StructureStart;

import java.util.Random;

/**
 * Created on 03/11/2016 part of com.ling.test.structure.
 */
public class DungeonStart extends StructureStart
{
    public DungeonStart()
    {
    }

    public DungeonStart(World world, Random rand, int p_i47149_3_, int p_i47149_4_) {
        super(p_i47149_3_, p_i47149_4_); // chunk info
        DungeonPieces.Room foo = new DungeonPieces.Room(p_i47149_3_, p_i47149_4_, rand);
        foo.buildComponent(foo, this.components, rand);
        this.updateBoundingBox();

    }
}
