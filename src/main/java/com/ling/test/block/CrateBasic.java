package com.ling.test.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;

/**
 * Created by Joe on 02/11/2016.
 */
public class CrateBasic extends Block {

    public static String name = "crate_basic";

    public CrateBasic() {
        super(Material.WOOD);
        setHardness(10F);
        setUnlocalizedName(CrateBasic.name);
    }
}
