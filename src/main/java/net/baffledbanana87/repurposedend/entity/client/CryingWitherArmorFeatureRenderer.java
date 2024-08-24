package net.baffledbanana87.repurposedend.entity.client;

import net.baffledbanana87.repurposedend.entity.custom.CryingWitherEntity;
import net.minecraft.client.render.entity.feature.EnergySwirlOverlayFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.render.entity.model.WitherEntityModel;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

public class CryingWitherArmorFeatureRenderer extends EnergySwirlOverlayFeatureRenderer<CryingWitherEntity, WitherEntityModel<CryingWitherEntity>> {

    private static final Identifier SKIN = Identifier.ofVanilla("textures/entity/wither/wither_armor.png");
    private final WitherEntityModel<CryingWitherEntity> model;

    public CryingWitherArmorFeatureRenderer(FeatureRendererContext<CryingWitherEntity, WitherEntityModel<CryingWitherEntity>> context, EntityModelLoader loader) {
        super(context);
        this.model = new WitherEntityModel<>(loader.getModelPart(EntityModelLayers.WITHER_ARMOR));
    }

    @Override
    protected float getEnergySwirlX(float partialAge) {
        return MathHelper.cos(partialAge * 0.02F) * 3.0F;
    }

    @Override
    protected Identifier getEnergySwirlTexture() {
        return SKIN;
    }

    @Override
    protected EntityModel<CryingWitherEntity> getEnergySwirlModel() {
        return this.model;
    }
}
