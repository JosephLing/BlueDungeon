package com.ling.test.block;

import com.ling.test.ExampleMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

/**
 * Created by Joe on 02/11/2016.
 */
public class CrateBasic extends ModBlock {

    public static String name = "crate_basic";


    public CrateBasic() {
        super(CrateBasic.name, Material.WOOD);
        setHardness(2F);
    }

    /**
     * always 3
     * max 6
     * @return
     */
    @Override
    public int quantityDropped(Random p_quantityDropped_1_)
    {
        return 3+p_quantityDropped_1_.nextInt(3);
    }

    /**
     * we don't want this to be abused too much so you can't farm it
     * @return false
     */
    @Override
    public boolean canSilkHarvest(World p_canSilkHarvest_1_, BlockPos p_canSilkHarvest_2_, IBlockState p_canSilkHarvest_3_, EntityPlayer p_canSilkHarvest_4_)
    {
        return false;
    }


    /**
     * Flint 10%
     * Feather, Coal, String 5%
     * Poisonous potato, Spider Eye 2%
     * sticks 50%
     * @param state
     * @param rand
     * @param fortune
     * @return
     */
    @Nullable
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        double randNum = rand.nextDouble();
        if (randNum < 0.1){
            return Items.FLINT;
        }else if (randNum < 0.2){
            return Items.FEATHER;
        }else if (randNum < 0.25){
            return Items.COAL;
        }else if (randNum < 0.3){
            return Items.STRING;
        }else if (randNum < 0.32){
            return Items.SPIDER_EYE;
        }else if (randNum < 0.34){
            return Items.POISONOUS_POTATO;
        }else{
            return Items.STICK;
        }
    }
}
