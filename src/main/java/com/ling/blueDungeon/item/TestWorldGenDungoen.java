package com.ling.blueDungeon.item;


import com.ling.blueDungeon.worldGen.DungeonWorldGen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

/**
 * Created on 30/11/2016 part of com.ling.blueDungeon.item.
 */
public class TestWorldGenDungoen extends ModItem
{
    public TestWorldGenDungoen(){
        super("dev_test_gen", 1, null);
    }


    @Override
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote)
        {
            DungeonWorldGen dungeon = new DungeonWorldGen();
            pos.add(2, 2, 2);
            dungeon.generate(worldIn, itemRand, pos);
            playerIn.addChatComponentMessage(new TextComponentString("creating structure..."));
        }
        return EnumActionResult.SUCCESS;
    }
}
