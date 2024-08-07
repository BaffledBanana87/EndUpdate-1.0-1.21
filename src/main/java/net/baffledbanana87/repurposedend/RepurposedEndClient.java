package net.baffledbanana87.repurposedend;


import net.baffledbanana87.repurposedend.entity.ModEntity;
import net.baffledbanana87.repurposedend.entity.client.CryingSkeletonRenderer;
import net.baffledbanana87.repurposedend.entity.client.EnderSkeletonRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;


public class RepurposedEndClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {


        EntityRendererRegistry.register(ModEntity.CRYING_SKELETON, CryingSkeletonRenderer::new);
        EntityRendererRegistry.register(ModEntity.ENDER_SKELETON, EnderSkeletonRenderer::new);

    }
}
