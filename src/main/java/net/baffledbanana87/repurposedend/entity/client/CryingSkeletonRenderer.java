package net.baffledbanana87.repurposedend.entity.client;

import net.baffledbanana87.repurposedend.RepurposedEnd;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.WitherSkeletonEntityRenderer;
import net.minecraft.entity.mob.WitherSkeletonEntity;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class CryingSkeletonRenderer extends WitherSkeletonEntityRenderer{
    private static final Identifier TEXTURE = Identifier.of(RepurposedEnd.MOD_ID,"textures/entity/cryingskeleton.png");

    public CryingSkeletonRenderer(EntityRendererFactory.Context context) {
        super(context);
    }


    public Identifier getTexture(WitherSkeletonEntity witherSkeletonEntity) {
        return TEXTURE;
    }

}