package net.baffledbanana87.repurposedend.entity;

import net.baffledbanana87.repurposedend.RepurposedEnd;
import net.baffledbanana87.repurposedend.entity.custom.CryingSkeletonEntity;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;



public class ModEntity {

    public static final EntityType<CryingSkeletonEntity> CRYING_SKELETON = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(RepurposedEnd.MOD_ID,
            "cryingskeleton"),
            EntityType.Builder.create(CryingSkeletonEntity::new,SpawnGroup.MONSTER).dimensions(0.6f,2.4f).build());


}
