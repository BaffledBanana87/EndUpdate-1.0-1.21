package net.baffledbanana87.repurposedend.drop;


import net.baffledbanana87.repurposedend.item.ModItems;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;

public class EnderDragonDrop{

    private static final Identifier DRAGON_ID =
            Identifier.of("minecraft","entities/ender_dragon");

    public static void modifyLootTables(){
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {

            if(DRAGON_ID.equals(key.getValue())){
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(2))
                        .conditionally(RandomChanceLootCondition.builder(3f))
                        .with(ItemEntry.builder(ModItems.MINI_SCALE))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,1.0f)).build());
                tableBuilder.pool(poolBuilder.build());
            }


        });
    }

}
