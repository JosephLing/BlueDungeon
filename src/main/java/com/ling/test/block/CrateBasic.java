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

    private Item[] common;
    private Item[] uncommon;
    private Item[] rare;

    public CrateBasic(String name, float hardness, Item[] common, Item[] uncommon, Item[] rare) {
        super(name, Material.WOOD);
        setHardness(hardness);
        this.common = common;
        this.uncommon = uncommon;
        this.rare = rare;
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
     *
     * @param state
     * @param rand
     * @param fortune 0 to 3
     * @return
     */
    @Nullable
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        double chance = rand.nextDouble();
        if ((chance <= 0.35  && fortune == 0) || (fortune != 0 && chance <= 0.2)){
            return Items.STICK;
        }else if (chance <= 0.7 - (0.1*fortune)){
            return common[rand.nextInt(common.length)];
        }else if (chance <= 0.9 - (0.05*fortune)){
            return uncommon[rand.nextInt(uncommon.length)];
        }else{
            return rare[rand.nextInt(rare.length)];
        }
    }
}
