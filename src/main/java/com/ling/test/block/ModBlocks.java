package com.ling.test.block;


import com.ling.test.ExampleMod;
import com.ling.test.item.ModItem;
import com.ling.test.item.TestItem;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Joe on 02/11/2016.
 */
public class ModBlocks{
    public static Block crate_basic;
    public static Block crate_uncommon;
    public static Block crate_rare;

    public static void preinit(){
        crate_basic = new CrateBasic(
                "crate_basic",
                1.5F,
                new Item[] {Items.SLIME_BALL, Items.POISONOUS_POTATO},
                new Item[] {Items.POTATO, Items.PAPER, Items.LEATHER},
                new Item[] {Items.GOLD_NUGGET, Items.COAL}
        );

        crate_uncommon = new CrateBasic(
                "crate_uncommon",
                2F,
                new Item[] {Items.MELON_SEEDS, Items.GLASS_BOTTLE},
                new Item[] {Items.SKULL, Items.ARROW, Items.COAL},
                new Item[] {Items.IRON_INGOT}
        );

        crate_rare = new CrateBasic(
                "crate_rare",
                3F,
                new Item[] {Items.QUARTZ, Items.LEATHER, Items.BOOK, Items.BONE},
                new Item[] {Items.PAINTING, Items.PRISMARINE_CRYSTALS},
                new Item[] {Items.DIAMOND, Items.REDSTONE}
        );
        registerBlocks();
    }


    public static void registerBlocks() {
        registerBlock(crate_basic, "crate_basic");
        registerBlock(crate_uncommon, "crate_uncommon");
        registerBlock(crate_rare, "crate_rare");
    }



    public static void registerRenders() {
        registerRender(crate_basic);
        registerRender(crate_uncommon);
        registerRender(crate_rare);
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



