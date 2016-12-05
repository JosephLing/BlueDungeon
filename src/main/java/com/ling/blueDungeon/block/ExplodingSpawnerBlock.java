package com.ling.blueDungeon.block;

import com.ling.blueDungeon.tileenitity.ExplodingSpawner;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

/**
 * Created on 02/12/2016 part of com.ling.blueDungeon.block.
 */
public class ExplodingSpawnerBlock extends ModBlock
{
    public ExplodingSpawnerBlock(){
        super("exploding_spawner", Material.ANVIL);
    }

    @Override
    public TileEntity createTileEntity(World world, IBlockState state)
    {
        return new ExplodingSpawner();
    }

    @Override
    public boolean hasTileEntity(IBlockState p_hasTileEntity_1_)
    {
        return true;
    }

    @Override
    public int getExpDrop(IBlockState p_getExpDrop_1_, IBlockAccess p_getExpDrop_2_, BlockPos p_getExpDrop_3_, int p_getExpDrop_4_)
    {
        return super.getExpDrop(p_getExpDrop_1_, p_getExpDrop_2_, p_getExpDrop_3_, p_getExpDrop_4_);
    }

    @Nullable
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return null;
    }

    @Override
    public boolean canSilkHarvest(World p_canSilkHarvest_1_, BlockPos p_canSilkHarvest_2_, IBlockState p_canSilkHarvest_3_, EntityPlayer p_canSilkHarvest_4_)
    {
        return false;
    }
}

