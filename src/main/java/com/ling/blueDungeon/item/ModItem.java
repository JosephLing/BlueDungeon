package com.ling.blueDungeon.item;

import com.ling.blueDungeon.ExampleMod;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.*;

/**
 * Created by Joe on 31/10/2016.
 */
public class ModItem extends Item {

    private String name;
    private List<String> description = new ArrayList<String>();

    public ModItem(String name, int stackSize, List<String> description) {
        super();
        setUnlocalizedName(name);
        setCreativeTab(ExampleMod.creativeTab1);
        setMaxStackSize(stackSize);
        if (description != null){
            this.description = description;
        }else{
            this.description.add("");
        }

    }

    public String getUnlocalizedNameSub(){
        return this.getUnlocalizedName().substring(5);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        tooltip.addAll(description);
        super.addInformation(stack, playerIn, tooltip, advanced);
    }
}
