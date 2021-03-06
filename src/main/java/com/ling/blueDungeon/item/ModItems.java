package com.ling.blueDungeon.item;

import com.ling.blueDungeon.ExampleMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Joe on 31/10/2016.
 */
public class ModItems {

    public static ModItem testItem;
    public static ModItem testWorldGenDungoen;

    public static void preinit(){
        testItem = new TestItem();
        testWorldGenDungoen = new TestWorldGenDungoen();
        registerItems();
    }

    public static void registerItems(){
        GameRegistry.register(testItem, new ResourceLocation(ExampleMod.MODID, testItem.getUnlocalizedNameSub()));
        GameRegistry.register(testWorldGenDungoen, new ResourceLocation(ExampleMod.MODID, testWorldGenDungoen.getUnlocalizedNameSub()));
    }

    public static void regesterRenders(){
        regesterRender(testItem);

    }

    public static void regesterRender(ModItem item){
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(
                item,
                0,
                new ModelResourceLocation(ExampleMod.MODID + ":" + item.getUnlocalizedNameSub(), "inventory")
        );
    }
}
