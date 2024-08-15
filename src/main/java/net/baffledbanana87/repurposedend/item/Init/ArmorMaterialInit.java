package net.baffledbanana87.repurposedend.item.Init;

import net.baffledbanana87.repurposedend.RepurposedEnd;
import net.baffledbanana87.repurposedend.item.ModItems;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class ArmorMaterialInit {


    public static final RegistryEntry<ArmorMaterial> DRAGON_SCALE = register("dragon_scale", Map.of(
                    ArmorItem.Type.HELMET, 3,
                    ArmorItem.Type.CHESTPLATE, 8,
                    ArmorItem.Type.LEGGINGS, 6,
                    ArmorItem.Type.BOOTS, 3
            ),
            15,
            SoundEvents.ITEM_ARMOR_EQUIP_GOLD,
            () -> Ingredient.ofItems(ModItems.DRAGON_SCALE),
            0.5F,
            0.1F,
            false);




    public static RegistryEntry<ArmorMaterial> register(String id, Map<ArmorItem.Type, Integer> defensePoints,
                                                        int enchantability, RegistryEntry<SoundEvent> equipSound,
                                                        Supplier<Ingredient> repairIngredient, float toughness,
                                                        float knockbackResistance, boolean dyeable){


        List<ArmorMaterial.Layer> layers = List.of(
                new ArmorMaterial.Layer(RepurposedEnd.id(id), "", dyeable)
        );

        var material = new ArmorMaterial(defensePoints, enchantability, equipSound, repairIngredient, layers,
                toughness, knockbackResistance);
        material = Registry.register(Registries.ARMOR_MATERIAL, RepurposedEnd.id(id), material);

        return RegistryEntry.of(material);


    }




    public static void load() {}


}
