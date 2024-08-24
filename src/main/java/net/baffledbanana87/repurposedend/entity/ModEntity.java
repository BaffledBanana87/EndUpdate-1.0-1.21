package net.baffledbanana87.repurposedend.entity;

import net.baffledbanana87.repurposedend.RepurposedEnd;
import net.baffledbanana87.repurposedend.entity.custom.CryingSkeletonEntity;
import net.baffledbanana87.repurposedend.entity.custom.CryingWitherEntity;
import net.baffledbanana87.repurposedend.entity.custom.EnderSkeletonEntity;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;



public class ModEntity {



    public static final EntityType<CryingSkeletonEntity> CRYING_SKELETON = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(RepurposedEnd.MOD_ID,
            "cryingskeleton"),
            EntityType.Builder.create(CryingSkeletonEntity::new,SpawnGroup.MONSTER)
                    .makeFireImmune()
                    .dimensions(0.6f,2.4f)
                    .build()
    );

    public static final EntityType<EnderSkeletonEntity> ENDER_SKELETON = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(RepurposedEnd.MOD_ID,"enderskeleton"),
            EntityType.Builder.create(EnderSkeletonEntity::new,SpawnGroup.MONSTER)
                    .makeFireImmune()
                    .dimensions(0.6f,2.4f)
                    .build()
    );
    public static final EntityType<CryingWitherEntity> CRYING_WITHER = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(RepurposedEnd.MOD_ID,"cryingwither"),
            EntityType.Builder.create(CryingWitherEntity::new,SpawnGroup.MONSTER)
                    .dimensions(0.9F, 3.5F)
                    .makeFireImmune()
                    .allowSpawningInside(Blocks.WITHER_ROSE)
                    .maxTrackingRange(10)
                    .build()
    );

    public static void registerEntitySpawns() {
        BiomeModifications.addSpawn(
                BiomeSelectors.foundInTheEnd(),
                SpawnGroup.MONSTER,
                CRYING_SKELETON,
                5,
                1,
                1
        );
    }

    public static void registerEnderSkeletonSpawns() {
        BiomeModifications.addSpawn(
                BiomeSelectors.foundInTheEnd(),
                SpawnGroup.MONSTER,
                ENDER_SKELETON,
                5,
                1,
                1
        );
    }


}
