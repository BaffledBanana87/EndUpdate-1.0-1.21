package net.baffledbanana87.repurposedend.mixin;

import net.baffledbanana87.repurposedend.entity.witherskull.SkullPlacedEvent;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.WitherSkullBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WitherSkullBlock.class)
public class SkullBlockMixin {

    @Inject(method = "onPlaced", at = @At("HEAD"))
    private void onPlacedInject(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack, CallbackInfo ci) {
        if (state.isOf(Blocks.WITHER_SKELETON_SKULL)) {
            SkullPlacedEvent.onPlaced(world, pos);
        }
    }
}