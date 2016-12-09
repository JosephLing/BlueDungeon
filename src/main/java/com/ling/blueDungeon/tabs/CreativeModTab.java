package com.ling.blueDungeon.tabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Created by Joe on 31/10/2016.
 */
public class CreativeModTab extends CreativeTabs{


    public CreativeModTab(int index, String label) {
        super(index, label);
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(Items.EMERALD);
    }
}
