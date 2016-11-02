package com.ling.test.block;


import com.ling.test.ExampleMod;
import com.ling.test.item.ModItem;
import com.ling.test.item.TestItem;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Joe on 02/11/2016.
 */
public class ModBlocks{
    public static Block crate_basic;

    public static void preinit(){
        crate_basic = new CrateBasic();
        registerBlocks();
    }


    public static void registerBlocks() {
        registerBlock(crate_basic, CrateBasic.name);
    }



    public static void registerRenders() {
        registerRender(crate_basic);
    }

    public static void registerBlock(Block block, String name) {
        GameRegistry.register(block, new ResourceLocation(ExampleMod.MODID, name));
        GameRegistry.register(new ItemBlock(block), new ResourceLocation(ExampleMod.MODID, name));
    }


    public static void registerRender(Block block) {
        Item item = Item.getItemFromBlock(block);
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(ExampleMod.MODID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
    }

}



