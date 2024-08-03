package net.baffledbanana87.repurposedend;


import net.baffledbanana87.repurposedend.entity.client.CryingSkeletonModel;
import net.baffledbanana87.repurposedend.entity.client.CryingSkeletonRenderer;
import net.baffledbanana87.repurposedend.entity.client.ModModelLayers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.entity.EntityType;


public class RepurposedEndClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {



        EntityRendererRegistry.register(EntityType.WITHER_SKELETON, CryingSkeletonRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.CRYING_SKELETON, CryingSkeletonModel::getTexturedModelData);


    }
}
