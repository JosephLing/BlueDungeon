package com.ling.test.block;

import com.ling.test.ExampleMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

import javax.annotation.Nullable;
import java.util.Random;

/**
 * Created by Joe on 02/11/2016.
 */
public class CrateBasic extends ModBlock {

    public static String name = "crate_basic";

    @Nullable
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return ;
    }

    public CrateBasic() {
        super(CrateBasic.name, Material.WOOD);
        setHardness(10F);
    }


}
