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
//            worldIn.getCombinedLight(pos, 1);
            pos = pos.up();
            playerIn.addChatComponentMessage(new TextComponentString(Integer.toString(worldIn.getLight(pos))));
        }
        return EnumActionResult.SUCCESS;
    }
}
