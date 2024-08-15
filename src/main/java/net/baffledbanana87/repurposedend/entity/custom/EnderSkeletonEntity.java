package net.baffledbanana87.repurposedend.entity.custom;

import net.minecraft.block.BlockState;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.AbstractPiglinEntity;
import net.minecraft.entity.mob.WitherSkeletonEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.thrown.PotionEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.potion.Potions;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.*;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;



public class EnderSkeletonEntity extends WitherSkeletonEntity{

    public EnderSkeletonEntity(EntityType<? extends WitherSkeletonEntity> entityType, World world) {
        super(entityType, world);
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

        int bonesCount = random.nextInt(3) + 1;
        this.dropStack(new ItemStack(Items.BONE, bonesCount));

        int pearlCount = random.nextInt(2);
        this.dropStack(new ItemStack(Items.ENDER_PEARL, pearlCount));

    }

    @Override
    protected PersistentProjectileEntity createArrowProjectile(ItemStack arrow, float damageModifier, @Nullable ItemStack shotFrom) {
        PersistentProjectileEntity persistentProjectileEntity = super.createArrowProjectile(arrow, damageModifier, shotFrom);
        persistentProjectileEntity.setOnFireFor(100.0F);
        return persistentProjectileEntity;
    }


    @Override
    protected void initEquipment(Random random, LocalDifficulty localDifficulty) {
        this.equipStack(EquipmentSlot.OFFHAND, new ItemStack(Items.BOW));
    }


    public static DefaultAttributeContainer.Builder createEnderSkeletonAttributes() {
        return CryingSkeletonEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 15f)
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
                ((LivingEntity) target).addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 200), this);
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


    @Override
    public void tickMovement() {
        if (this.getWorld().isClient) {
            for (int i = 0; i < 2; i++) {
                this.getWorld()
                        .addParticle(
                                ParticleTypes.PORTAL,
                                this.getParticleX(0.5),
                                this.getRandomBodyY() - 0.25,
                                this.getParticleZ(0.5),
                                (this.random.nextDouble() - 0.5) * 2.0,
                                -this.random.nextDouble(),
                                (this.random.nextDouble() - 0.5) * 2.0
                        );
            }
        }

        super.tickMovement();
    }


    protected boolean teleportRandomly() {
        if (!this.getWorld().isClient() && this.isAlive()) {
            double d = this.getX() + (this.random.nextDouble() - 0.5) * 12.0;
            double e = this.getY() + (double) (this.random.nextInt(64) - 32);
            double f = this.getZ() + (this.random.nextDouble() - 0.5) * 12.0;
            return this.teleportTo(d, e, f);
        } else {
            return false;
        }
    }


    private boolean teleportTo(double x, double y, double z) {
        BlockPos.Mutable mutable = new BlockPos.Mutable(x, y, z);

        while (mutable.getY() > this.getWorld().getBottomY() && !this.getWorld().getBlockState(mutable).blocksMovement()) {
            mutable.move(Direction.DOWN);
        }

        BlockState blockState = this.getWorld().getBlockState(mutable);
        boolean bl = blockState.blocksMovement();
        boolean bl2 = blockState.getFluidState().isIn(FluidTags.WATER);
        if (bl && !bl2) {
            Vec3d vec3d = this.getPos();
            boolean bl3 = this.teleport(x, y, z, true);
            if (bl3) {
                this.getWorld().emitGameEvent(GameEvent.TELEPORT, vec3d, GameEvent.Emitter.of(this));
                if (!this.isSilent()) {
                    this.getWorld().playSound(null, this.prevX, this.prevY, this.prevZ, SoundEvents.ENTITY_ENDERMAN_TELEPORT, this.getSoundCategory(), 1.0F, 1.0F);
                    this.playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, 1.0F, 1.0F);
                }
            }

            return bl3;
        } else {
            return false;
        }
    }


    private boolean damageFromPotion(DamageSource source, PotionEntity potion, float amount) {
        ItemStack itemStack = potion.getStack();
        PotionContentsComponent potionContentsComponent = itemStack.getOrDefault(DataComponentTypes.POTION_CONTENTS, PotionContentsComponent.DEFAULT);
        return potionContentsComponent.matches(Potions.WATER) && super.damage(source, amount);
    }


    @Override
    public boolean damage(DamageSource source, float amount) {
        if (this.isInvulnerableTo(source)) {
            return false;
        }
        else {
            boolean bl = source.getSource() instanceof PotionEntity;
            if (!bl && !source.isIn(DamageTypeTags.IS_PROJECTILE)) {
                boolean bl2 = super.damage(source, amount);
                if (!this.getWorld().isClient() && !(source.getAttacker() instanceof LivingEntity) && this.random.nextInt(10) != 0) {
                    this.teleportRandomly();
                }

                return bl2;
            } else {
                boolean bl2 = bl && this.damageFromPotion(source, (PotionEntity) source.getSource(), amount);

                for (int i = 0; i < 64; i++) {
                    if (this.teleportRandomly()) {
                        return true;
                    }
                }
                return bl2;
            }
        }
    }


    @Override
    public void takeKnockback(double strength, double x, double z) {
        this.teleportRandomly();
        super.takeKnockback(strength, x, z);
    }
}




