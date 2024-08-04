package net.baffledbanana87.repurposedend;

import net.baffledbanana87.repurposedend.entity.ModEntity;
import net.baffledbanana87.repurposedend.entity.custom.CryingSkeletonEntity;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.SpawnLocationTypes;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.world.Heightmap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RepurposedEnd implements ModInitializer {
	public static final String MOD_ID = "repurposedend";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		ModEntity.registerEntitySpawns();
		SpawnRestriction.register(ModEntity.CRYING_SKELETON, SpawnLocationTypes.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, HostileEntity::canSpawnInDark);
		FabricDefaultAttributeRegistry.register(ModEntity.CRYING_SKELETON, CryingSkeletonEntity.createCryingSkeletonAttributes());

	}
}