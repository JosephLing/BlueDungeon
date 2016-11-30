package com.ling.test.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.List;

/**
 * Created by Joe on 31/10/2016.
 */
public class TestItem extends ModItem{

    public TestItem(){
        super("test_item", 1, null);
    }


    @Override
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (worldIn.isRemote){
            Block currentBlock = worldIn.getBlockState(pos).getBlock();
            if (currentBlock == Blocks.CRAFTING_TABLE){

            } else if (currentBlock == Blocks.FURNACE){

            }
            //playerIn.addChatComponentMessage(new TextComponentString(worldIn.getBlockState(pos).getBlock().getLocalizedName()));
            playerIn.addChatComponentMessage(new TextComponentString(CraftingManager.getInstance().getRecipeList().get(0).getClass().getName()));
        }
        return EnumActionResult.SUCCESS;
    }
}
