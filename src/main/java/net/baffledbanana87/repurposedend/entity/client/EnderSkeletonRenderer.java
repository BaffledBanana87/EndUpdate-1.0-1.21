package net.baffledbanana87.repurposedend.entity.client;

import net.baffledbanana87.repurposedend.RepurposedEnd;
import net.baffledbanana87.repurposedend.entity.custom.EnderSkeletonEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.SkeletonEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.util.Identifier;


@Environment(EnvType.CLIENT)
public class EnderSkeletonRenderer extends SkeletonEntityRenderer<EnderSkeletonEntity> {
    private static final Identifier TEXTURE = Identifier.of(RepurposedEnd.MOD_ID,"textures/entity/enderskeleton.png");

    public EnderSkeletonRenderer(EntityRendererFactory.Context context) {
        super(context, EntityModelLayers.SKELETON, EntityModelLayers.SKELETON_INNER_ARMOR, EntityModelLayers.SKELETON_OUTER_ARMOR);
    }

    public Identifier getTexture(EnderSkeletonEntity enderSkeletonEntity) {
        return TEXTURE;
    }

}
