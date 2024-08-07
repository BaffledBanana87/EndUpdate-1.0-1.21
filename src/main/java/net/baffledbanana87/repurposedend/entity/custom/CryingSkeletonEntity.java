package net.baffledbanana87.repurposedend.entity.custom;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.*;
import org.jetbrains.annotations.Nullable;



public class CryingSkeletonEntity extends WitherSkeletonEntity {

    public CryingSkeletonEntity(EntityType<? extends WitherSkeletonEntity> entityType, World world) {
        super(entityType, world);
        this.setPathfindingPenalty(PathNodeType.LAVA, 8.0F);
    }

    @Override
    protected void initGoals() {

       this.goalSelector.add(3, new FleeEntityGoal<>(this, WolfEntity.class, 6.0F, 1.0, 1.2));
       this.goalSelector.add(5, new WanderAroundFarGoal(this, 1.0));
       this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
       this.goalSelector.add(6, new LookAroundGoal(this));
       this.targetSelector.add(1, new RevengeGoal(this));
       this.targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
       this.targetSelector.add(3, new ActiveTargetGoal<>(this, IronGolemEntity.class, true));
       this.targetSelector.add(3, new ActiveTargetGoal<>(this, TurtleEntity.class, 10, true, false, TurtleEntity.BABY_TURTLE_ON_LAND_FILTER));
       this.targetSelector.add(3, new ActiveTargetGoal<>(this, AbstractPiglinEntity.class, true));
    }

    @Override
    protected void dropEquipment(ServerWorld world, DamageSource source, boolean causedByPlayer) {
        super.dropEquipment(world, source, causedByPlayer);
        int cryingObsidianCount = random.nextInt(2);
        this.dropStack(new ItemStack(Items.CRYING_OBSIDIAN, cryingObsidianCount));


        int bonesCount = random.nextInt(3) + 1;
        this.dropStack(new ItemStack(Items.BONE, bonesCount));

        if (source.getAttacker() instanceof CreeperEntity creeperEntity && creeperEntity.shouldDropHead()) {
            creeperEntity.onHeadDropped();
            this.dropItem(Items.WITHER_SKELETON_SKULL);
        }
    }

    @Override
    protected PersistentProjectileEntity createArrowProjectile(ItemStack arrow, float damageModifier, @Nullable ItemStack shotFrom) {
        PersistentProjectileEntity persistentProjectileEntity = super.createArrowProjectile(arrow, damageModifier, shotFrom);
        persistentProjectileEntity.setOnFireFor(100.0F);
        return persistentProjectileEntity;
    }


    @Override
    protected void initEquipment(Random random, LocalDifficulty localDifficulty) {
        this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_SWORD));
    }


    public static DefaultAttributeContainer.Builder createCryingSkeletonAttributes(){
        return CryingSkeletonEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 40f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25f)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 6.0f);

    }


    @Nullable
    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData) {
        this.updateAttackType();
        this.initEquipment(world.getRandom(), difficulty);
        return super.initialize(world, difficulty, spawnReason, entityData);
    }

    @Override
    public boolean tryAttack(Entity target) {
        if (!super.tryAttack(target)) {
            return false;
        } else {
            if (target instanceof LivingEntity) {
                ((LivingEntity)target).addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 200), this);
            }

            return true;
        }
    }



    @Override
    public void equipStack(EquipmentSlot slot, ItemStack stack) {
        super.equipStack(slot, stack);
        if (!this.getWorld().isClient) {
            this.updateAttackType();
        }
    }

    @Override
    public boolean canSpawn(WorldView world) {
        return super.canSpawn(world);
    }

}

