package net.baffledbanana87.repurposedend.item;

import net.baffledbanana87.repurposedend.RepurposedEnd;
import net.baffledbanana87.repurposedend.block.ModBlock;
import net.baffledbanana87.repurposedend.item.Init.ArmorMaterialInit;
import net.baffledbanana87.repurposedend.item.Init.ModToolMaterial;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;



public class ModItems {



    public static final Item DRAGON_SCALE = registerItem("dragon_scale", new Item(new Item.Settings()));
    public static final Item MINI_SCALE = registerItem("mini_scale", new Item(new Item.Settings()));
    public static final Item DRAGON_UPGRADER = registerItem("dragon_upgrade_smithing_template", new Item(new Item.Settings()));

    public static final Item DRAGON_SCALE_BOOTS = registerItem("dragon_scale_boots", new ArmorItem(ArmorMaterialInit.DRAGON_SCALE,
            ArmorItem.Type.BOOTS, new Item.Settings().maxCount(1)));
    public static final Item DRAGON_SCALE_CHESTPLATE = registerItem("dragon_scale_chestplate", new ArmorItem(ArmorMaterialInit.DRAGON_SCALE,
            ArmorItem.Type.CHESTPLATE, new Item.Settings().maxCount(1)));
    public static final Item DRAGON_SCALE_LEGGINGS = registerItem("dragon_scale_leggings", new ArmorItem(ArmorMaterialInit.DRAGON_SCALE,
            ArmorItem.Type.LEGGINGS, new Item.Settings().maxCount(1)));
    public static final Item DRAGON_SCALE_HELMETS = registerItem("dragon_scale_helmet", new ArmorItem(ArmorMaterialInit.DRAGON_SCALE,
            ArmorItem.Type.HELMET, new Item.Settings().maxCount(1)));




    public static final Item DRAGON_SCALE_SWORD = registerItem("dragon_sword", new SwordItem(ModToolMaterial.DRAGON_SCALE_MATERIAL, new Item.Settings().attributeModifiers(
            SwordItem.createAttributeModifiers(ModToolMaterial.DRAGON_SCALE_MATERIAL,  8, -2.4f))));
    public static final Item DRAGON_SCALE_PICKAXE = registerItem("dragon_pickaxe", new PickaxeItem(ModToolMaterial.DRAGON_SCALE_MATERIAL, new Item.Settings().attributeModifiers(
            PickaxeItem.createAttributeModifiers(ModToolMaterial.DRAGON_SCALE_MATERIAL,  6, -2.8f))));
    public static final Item DRAGON_SCALE_HOE = registerItem("dragon_hoe", new HoeItem(ModToolMaterial.DRAGON_SCALE_MATERIAL, new Item.Settings().attributeModifiers(
            HoeItem.createAttributeModifiers(ModToolMaterial.DRAGON_SCALE_MATERIAL,  0, 0f))));
    public static final Item DRAGON_SCALE_SHOVEL = registerItem("dragon_shovel", new ShovelItem(ModToolMaterial.DRAGON_SCALE_MATERIAL, new Item.Settings().attributeModifiers(
            ShovelItem.createAttributeModifiers(ModToolMaterial.DRAGON_SCALE_MATERIAL,  6.5f, -3f))));
    public static final Item DRAGON_SCALE_AXE = registerItem("dragon_axe", new AxeItem(ModToolMaterial.DRAGON_SCALE_MATERIAL, new Item.Settings().attributeModifiers(
            AxeItem.createAttributeModifiers(ModToolMaterial.DRAGON_SCALE_MATERIAL,  10, -3f))));


    private static void addItemsToMaterialsItemGroup(FabricItemGroupEntries entries){
        entries.add(MINI_SCALE);
        entries.add(DRAGON_SCALE);
        entries.add(DRAGON_UPGRADER);
    }

    private  static void addItemsToBlockGroup(FabricItemGroupEntries entries){
        entries.add(ModBlock.DRAGON_SCALE_BLOCK);
    }

    private static void addItemsToArmorGroup(FabricItemGroupEntries entries){
        entries.add(DRAGON_SCALE_BOOTS);
        entries.add(DRAGON_SCALE_CHESTPLATE);
        entries.add(DRAGON_SCALE_LEGGINGS);
        entries.add(DRAGON_SCALE_HELMETS);
        entries.add(DRAGON_SCALE_SWORD);
        entries.add(DRAGON_SCALE_AXE);
    }

    private static void addItemToTools(FabricItemGroupEntries entries){
        entries.add(DRAGON_SCALE_AXE);
        entries.add(DRAGON_SCALE_PICKAXE);
        entries.add(DRAGON_SCALE_SHOVEL);
        entries.add(DRAGON_SCALE_HOE);

    }


    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, Identifier.of(RepurposedEnd.MOD_ID, name), item);
    }


    public static void registerModItems(){
        RepurposedEnd.LOGGER.info("Registering Mod Items for " + RepurposedEnd.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToMaterialsItemGroup);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(ModItems::addItemsToBlockGroup);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(ModItems::addItemsToArmorGroup);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(ModItems::addItemToTools);
    }

}
