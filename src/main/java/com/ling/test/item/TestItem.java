package com.ling.test.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

/**
 * Created by Joe on 31/10/2016.
 */
public class TestItem extends ModItem{

    public TestItem(){
        super("test_item", 1, null);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
        if (worldIn.isRemote){

        }
        return super.onItemRightClick(itemStackIn, worldIn, playerIn, hand);
    }
}
