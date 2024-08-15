package net.baffledbanana87.repurposedend.block;

import net.baffledbanana87.repurposedend.RepurposedEnd;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlock {


    public static final Block DRAGON_SCALE_BLOCK = registerBlock("dragon_scale_block", new Block(AbstractBlock.Settings.copy(Blocks.NETHERITE_BLOCK)));



    private static Block registerBlock(String name, Block block){
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(RepurposedEnd.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block){
        return Registry.register(Registries.ITEM, Identifier.of(RepurposedEnd.MOD_ID, name),
                new BlockItem(block,  new Item.Settings()));
    }

    public static void registerModBlocks(){
        RepurposedEnd.LOGGER.info("Registering ModBlock for " + RepurposedEnd.MOD_ID);
    }




}
