package com.ling.blueDungeon.block;

import com.ling.blueDungeon.ExampleMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

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