package com.ling.blueDungeon.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

/**
 * Created by Joe on 31/10/2016.
 */
public class TestItem extends ModItem{

    public TestItem(){
        super("test_item", 1, null);
    }


    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand enumHand, EnumFacing hand, float facing, float hitX, float hitY) {
        if (!world.isRemote){
//            worldIn.getCombinedLight(pos, 1);
            pos = pos.up();
            player.addChatComponentMessage(new TextComponentString(Integer.toString(world.getLight(pos))), true);
        }
        return EnumActionResult.SUCCESS;
    }
}
