package com.ling.test.block;

import com.ling.test.ExampleMod;
import com.ling.test.tileenitity.ExplodingSpawner;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by Joe on 02/11/2016.
 */
public class ModBlock extends Block {

    public ModBlock(String name, Material materialIn) {
        super(materialIn);
        this.setUnlocalizedName(name);
        this.setCreativeTab(ExampleMod.creativeTab1);
    }
}