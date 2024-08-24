package net.baffledbanana87.repurposedend.entity.client;

import net.baffledbanana87.repurposedend.RepurposedEnd;
import net.baffledbanana87.repurposedend.entity.custom.CryingWitherEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.WitherEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;


@Environment(EnvType.CLIENT)
public class CryingWitherRenderer extends MobEntityRenderer<CryingWitherEntity, WitherEntityModel<CryingWitherEntity>> {
    private static final Identifier INVULNERABLE_TEXTURE = Identifier.ofVanilla("textures/entity/wither/wither_invulnerable.png");
    private static final Identifier TEXTURE = Identifier.of(RepurposedEnd.MOD_ID,"textures/entity/cryingwither.png");

    public CryingWitherRenderer(EntityRendererFactory.Context context) {
        super(context, new WitherEntityModel<>(context.getPart(EntityModelLayers.WITHER)), 1.0F);
        this.addFeature(new CryingWitherArmorFeatureRenderer(this, context.getModelLoader()));
    }

    protected int getBlockLight(CryingWitherEntity witherEntity, BlockPos blockPos) {
        return 15;
    }


    @Override
    public Identifier getTexture(CryingWitherEntity witherEntity) {
        int i = witherEntity.getInvulnerableTimer();
        return i > 0 && (i > 80 || i / 5 % 2 != 1) ? INVULNERABLE_TEXTURE : TEXTURE;
    }

    protected void scale(CryingWitherEntity witherEntity, MatrixStack matrixStack, float f) {
        float g = 2.0F;
        int i = witherEntity.getInvulnerableTimer();
        if (i > 0) {
            g -= ((float)i - f) / 220.0F * 0.5F;
        }

        matrixStack.scale(g, g, g);
    }
}
