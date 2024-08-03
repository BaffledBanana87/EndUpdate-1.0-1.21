package net.baffledbanana87.repurposedend.entity.custom;

import net.baffledbanana87.repurposedend.entity.client.CryingSkeletonModel;
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
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;


public class CryingSkeletonEntity extends HostileEntity {
    private final MeleeAttackGoal meleeAttackGoal = new MeleeAttackGoal(this, 1f, false) {
        @Override
        public void stop() {
            super.stop();
            CryingSkeletonEntity.this.setAttacking(false);
        }

        @Override
        public void start() {
            super.start();
            CryingSkeletonEntity.this.setAttacking(true);
        }
    };


    public CryingSkeletonEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
        this.updateAttackType();
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
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_WITHER_SKELETON_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_WITHER_SKELETON_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_WITHER_SKELETON_DEATH;
    }


    @Override
    protected void initEquipment(Random random, LocalDifficulty localDifficulty) {
        this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_SWORD));
    }



    public static DefaultAttributeContainer.Builder createCryingSkeletonAttributes(){
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 15f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25f)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 4.0f);

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

    public void updateAttackType() {
        if (this.getWorld() != null && !this.getWorld().isClient) {
            this.goalSelector.remove(this.meleeAttackGoal);
            this.goalSelector.add(4, this.meleeAttackGoal);
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
    protected void updateEnchantments(ServerWorldAccess world, Random random, LocalDifficulty localDifficulty) {
    }

}
