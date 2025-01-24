package top.srcres258.tutorialmod.datagen

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider
import net.minecraft.block.Block
import net.minecraft.enchantment.Enchantments
import net.minecraft.item.Item
import net.minecraft.loot.entry.ItemEntry
import net.minecraft.loot.function.ApplyBonusLootFunction
import net.minecraft.loot.function.SetCountLootFunction
import net.minecraft.loot.provider.number.UniformLootNumberProvider
import top.srcres258.tutorialmod.block.ModBlocks
import top.srcres258.tutorialmod.item.ModItems

class ModLootTableProvider(dataOutput: FabricDataOutput) : FabricBlockLootTableProvider(dataOutput) {
    override fun generate() {
        addDrop(ModBlocks.RUBY_BLOCK)
        addDrop(ModBlocks.RAW_RUBY_BLOCK)
        addDrop(ModBlocks.SOUND_BLOCK)

        addDrop(ModBlocks.RUBY_ORE, copperLikeOreDrops(ModBlocks.RUBY_ORE, ModItems.RAW_RUBY))
        addDrop(ModBlocks.DEEPSLATE_RUBY_ORE, copperLikeOreDrops(ModBlocks.DEEPSLATE_RUBY_ORE, ModItems.RAW_RUBY))
        addDrop(ModBlocks.NETHER_RUBY_ORE, copperLikeOreDrops(ModBlocks.NETHER_RUBY_ORE, ModItems.RAW_RUBY))
        addDrop(ModBlocks.END_STONE_RUBY_ORE, copperLikeOreDrops(ModBlocks.END_STONE_RUBY_ORE, ModItems.RAW_RUBY))
    }

    private fun copperLikeOreDrops(drop: Block, item: Item) =
        dropsWithSilkTouch(drop, applyExplosionDecay(drop,
            ItemEntry.builder(item)
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2F, 5F)))
                .apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE))))
}