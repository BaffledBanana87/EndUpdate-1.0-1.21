package net.baffledbanana87.repurposedend.entity;

import net.baffledbanana87.repurposedend.RepurposedEnd;
import net.baffledbanana87.repurposedend.entity.custom.CryingSkeletonEntity;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
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

    public static void registerEntitySpawns() {
        BiomeModifications.addSpawn(
                BiomeSelectors.foundInTheEnd(),
                SpawnGroup.MONSTER,
                CRYING_SKELETON,
                10,
                1,
                1
        );
    }
}
