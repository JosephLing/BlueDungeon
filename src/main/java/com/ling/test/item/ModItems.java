package com.ling.test.item;

import com.ling.test.ExampleMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Joe on 31/10/2016.
 */
public class ModItems {

    public static ModItem testItem;

    public static void preinit(){
        testItem = new TestItem();
        registerItems();
    }

    public static void registerItems(){
        GameRegistry.register(testItem, new ResourceLocation(ExampleMod.MODID, testItem.getUnlocalizedNameSub()));
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
