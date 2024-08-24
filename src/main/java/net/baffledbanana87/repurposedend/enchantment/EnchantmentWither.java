package net.baffledbanana87.repurposedend.enchantment;

import com.mojang.serialization.MapCodec;
import net.baffledbanana87.repurposedend.RepurposedEnd;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public class EnchantmentWither {

    public static final RegistryKey<Enchantment> WITHER_ENCHANTMENT_KEY = RegistryKey.of(RegistryKeys.ENCHANTMENT,
            RepurposedEnd.id("wither_enchantment"));


    public static final MapCodec<WitherEnchantmentEffect> WITHER_EFFECT = register("wither", WitherEnchantmentEffect.CODEC);


    private static <T extends EnchantmentEntityEffect>MapCodec<T> register(String name, MapCodec<T> codec){
        return Registry.register(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, RepurposedEnd.id(name), codec);
    }



    public static void load() {}



}
