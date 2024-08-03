package net.baffledbanana87.repurposedend.entity.client;

import net.baffledbanana87.repurposedend.RepurposedEnd;
import net.baffledbanana87.repurposedend.entity.custom.CryingSkeletonEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.SkeletonEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class CryingSkeletonRenderer extends SkeletonEntityRenderer<CryingSkeletonEntity> {
    private static final Identifier TEXTURE = Identifier.of(RepurposedEnd.MOD_ID,"textures/entity/cryingskeleton.png");

    public CryingSkeletonRenderer(EntityRendererFactory.Context context) {
        super(context, EntityModelLayers.WITHER_SKELETON, EntityModelLayers.WITHER_SKELETON_INNER_ARMOR, EntityModelLayers.WITHER_SKELETON_OUTER_ARMOR);
    }

    public Identifier getTexture(CryingSkeletonEntity cryingSkeletonRenderer) {
        return TEXTURE;
    }

    protected void scale(CryingSkeletonEntity cryingSkeletonRenderer, MatrixStack matrixStack, float f) {
        matrixStack.scale(1.2F, 1.2F, 1.2F);
    }

}