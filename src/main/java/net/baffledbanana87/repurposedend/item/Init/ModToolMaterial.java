package net.baffledbanana87.repurposedend.item.Init;

import net.baffledbanana87.repurposedend.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;

import java.util.function.Supplier;


public enum ModToolMaterial implements ToolMaterial {
    DRAGON_SCALE_MATERIAL(
            3430,
            3.5f,
            0.0f,
            BlockTags.INCORRECT_FOR_NETHERITE_TOOL,
            15,
            () -> Ingredient.ofItems(ModItems.DRAGON_SCALE)
    );


    private final int durability;
    private final float miningSpeedMultiplier, attackDamage;
    private final TagKey<Block> inverseTag;
    private final int enchantability;
    private final Supplier<Ingredient> repairIngredient;


    ModToolMaterial( int durability, float miningSpeedMultiplier, float attackDamage, TagKey<Block> inverseTag,int enchantability, Supplier<Ingredient> repairIngredient) {
        this.durability = durability;
        this.miningSpeedMultiplier = miningSpeedMultiplier;
        this.attackDamage = attackDamage;
        this.inverseTag = inverseTag;
        this.enchantability = enchantability;
        this.repairIngredient = repairIngredient;
    }


    @Override
    public int getDurability() {
        return this.durability;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return this.miningSpeedMultiplier;
    }

    @Override
    public float getAttackDamage() {
        return this.attackDamage;

    }

    @Override
    public TagKey<Block> getInverseTag() {
        return this.inverseTag;
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }


}
