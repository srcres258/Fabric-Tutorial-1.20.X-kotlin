package top.srcres258.tutorialmod.util

import net.fabricmc.fabric.api.loot.v2.LootTableEvents
import net.minecraft.loot.LootPool
import net.minecraft.loot.condition.RandomChanceLootCondition
import net.minecraft.loot.entry.ItemEntry
import net.minecraft.loot.function.SetCountLootFunction
import net.minecraft.loot.provider.number.ConstantLootNumberProvider
import net.minecraft.loot.provider.number.UniformLootNumberProvider
import net.minecraft.util.Identifier
import top.srcres258.tutorialmod.item.ModItems

object ModLootTableModifiers {
    private val JUNGLE_TEMPLE_ID = Identifier("minecraft", "chests/jungle_temple")
    private val CREEPER_ID = Identifier("minecraft", "entities/creeper")

    private val SUSPICIOUS_SAND_ID = Identifier("minecraft", "archaeology/desert_pyramid")

    fun modifyLootTables() {
        LootTableEvents.MODIFY.register { resourceManager, lootManager, id, tableBuilder, source ->
            when (id) {
                JUNGLE_TEMPLE_ID -> tableBuilder.pool(
                    LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1F))
                        .conditionally(RandomChanceLootCondition.builder(1F)) // Drops 100% of the time
                        .with(ItemEntry.builder(ModItems.METAL_DETECTOR))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1F, 1F)))
                        .build()
                )
                CREEPER_ID -> tableBuilder.pool(
                    LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1F))
                        .conditionally(RandomChanceLootCondition.builder(1F)) // Drops 100% of the time
                        .with(ItemEntry.builder(ModItems.COAL_BRIQUETTE))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1F, 1F)))
                        .build()
                )
            }
        }

        // This is not going to work since Minecraft 1.20.2 because original.pools has been changed to private.
        LootTableEvents.REPLACE.register { resourceManager, lootManager, id, original, source ->
//            when (id) {
//                SUSPICIOUS_SAND_ID -> LootTable.builder()
//                    .pool(
//                        LootPool.builder()
//                            .with(original.pools[0].entries.toMutableList()
//                                .apply {
//                                    add(ItemEntry.builder(ModItems.METAL_DETECTOR).build())
//                                    add(ItemEntry.builder(ModItems.COAL_BRIQUETTE).build())
//                                })
//                    )
//                    .build()
//                else -> null
//            }

            null
        }
    }
}