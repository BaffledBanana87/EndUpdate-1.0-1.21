package net.baffledbanana87.repurposedend.item.custom;

import net.baffledbanana87.repurposedend.RepurposedEnd;
import net.minecraft.item.SmithingTemplateItem;
import net.minecraft.resource.featuretoggle.FeatureFlag;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.List;

public class SmithingTemplateScale extends SmithingTemplateItem {

    public SmithingTemplateScale(Text appliesToText, Text ingredientsText, Text titleText, Text baseSlotDescriptionText, Text additionsSlotDescriptionText, List<Identifier> emptyBaseSlotTextures, List<Identifier> emptyAdditionsSlotTextures, FeatureFlag... requiredFeatures) {
        super(appliesToText, ingredientsText, titleText, baseSlotDescriptionText, additionsSlotDescriptionText, emptyBaseSlotTextures, emptyAdditionsSlotTextures, requiredFeatures);
    }

    private static final Formatting TITLE_FORMATTING = Formatting.GRAY;
    private static final Formatting DESCRIPTION_FORMATTING = Formatting.BLUE;

    private static final Text NETHERITE_UPGRADE_TEXT = Text.translatable(Util.createTranslationKey("upgrade", Identifier.of(RepurposedEnd.MOD_ID,"scale_upgrade")))
            .formatted(TITLE_FORMATTING);

    private static final Text NETHERITE_UPGRADE_APPLIES_TO_TEXT = Text.translatable(
                    Util.createTranslationKey("item", Identifier.of(RepurposedEnd.MOD_ID,"smithing_scale.applies_to"))
            )
            .formatted(DESCRIPTION_FORMATTING);

    private static final Text NETHERITE_UPGRADE_INGREDIENTS_TEXT = Text.translatable(
                    Util.createTranslationKey("item", Identifier.of(RepurposedEnd.MOD_ID, "smithing_scale.ingredients" ))
            )
            .formatted(DESCRIPTION_FORMATTING);
    private static final Text NETHERITE_UPGRADE_BASE_SLOT_DESCRIPTION_TEXT = Text.translatable(
            Util.createTranslationKey("item", Identifier.of(RepurposedEnd.MOD_ID,"smithing_scale.base_slot_description"))
    );
    private static final Text NETHERITE_UPGRADE_ADDITIONS_SLOT_DESCRIPTION_TEXT = Text.translatable(
            Util.createTranslationKey("item", Identifier.of(RepurposedEnd.MOD_ID,"smithing_scale.additions_slot_description"))
    );

    private static final Identifier EMPTY_ARMOR_SLOT_HELMET_TEXTURE = Identifier.ofVanilla("item/empty_armor_slot_helmet");
    private static final Identifier EMPTY_ARMOR_SLOT_CHESTPLATE_TEXTURE = Identifier.ofVanilla("item/empty_armor_slot_chestplate");
    private static final Identifier EMPTY_ARMOR_SLOT_LEGGINGS_TEXTURE = Identifier.ofVanilla("item/empty_armor_slot_leggings");
    private static final Identifier EMPTY_ARMOR_SLOT_BOOTS_TEXTURE = Identifier.ofVanilla("item/empty_armor_slot_boots");
    private static final Identifier EMPTY_SLOT_HOE_TEXTURE = Identifier.ofVanilla("item/empty_slot_hoe");
    private static final Identifier EMPTY_SLOT_AXE_TEXTURE = Identifier.ofVanilla("item/empty_slot_axe");
    private static final Identifier EMPTY_SLOT_SWORD_TEXTURE = Identifier.ofVanilla("item/empty_slot_sword");
    private static final Identifier EMPTY_SLOT_SHOVEL_TEXTURE = Identifier.ofVanilla("item/empty_slot_shovel");
    private static final Identifier EMPTY_SLOT_PICKAXE_TEXTURE = Identifier.ofVanilla("item/empty_slot_pickaxe");
    private static final Identifier EMPTY_SLOT_INGOT_TEXTURE = Identifier.ofVanilla("item/empty_slot_ingot");

    private static List<Identifier> getNetheriteUpgradeEmptyAdditionsSlotTextures() {
        return List.of(EMPTY_SLOT_INGOT_TEXTURE);
    }

    private static List<Identifier> getNetheriteUpgradeEmptyBaseSlotTextures() {
        return List.of(
                EMPTY_ARMOR_SLOT_HELMET_TEXTURE,
                EMPTY_SLOT_SWORD_TEXTURE,
                EMPTY_ARMOR_SLOT_CHESTPLATE_TEXTURE,
                EMPTY_SLOT_PICKAXE_TEXTURE,
                EMPTY_ARMOR_SLOT_LEGGINGS_TEXTURE,
                EMPTY_SLOT_AXE_TEXTURE,
                EMPTY_ARMOR_SLOT_BOOTS_TEXTURE,
                EMPTY_SLOT_HOE_TEXTURE,
                EMPTY_SLOT_SHOVEL_TEXTURE
        );
    }

    public static SmithingTemplateScale createScaleUpgrade() {
        return new SmithingTemplateScale(
                NETHERITE_UPGRADE_APPLIES_TO_TEXT,
                NETHERITE_UPGRADE_INGREDIENTS_TEXT,
                NETHERITE_UPGRADE_TEXT,
                NETHERITE_UPGRADE_BASE_SLOT_DESCRIPTION_TEXT,
                NETHERITE_UPGRADE_ADDITIONS_SLOT_DESCRIPTION_TEXT,
                getNetheriteUpgradeEmptyBaseSlotTextures(),
                getNetheriteUpgradeEmptyAdditionsSlotTextures()
        );
    }

}
