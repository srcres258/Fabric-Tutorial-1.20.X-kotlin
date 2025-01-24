package top.srcres258.tutorialmod.datagen

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider
import net.minecraft.data.client.BlockStateModelGenerator
import net.minecraft.data.client.ItemModelGenerator
import net.minecraft.data.client.Models
import top.srcres258.tutorialmod.block.ModBlocks
import top.srcres258.tutorialmod.item.ModItems

class ModModelProvider(output: FabricDataOutput) : FabricModelProvider(output) {
    override fun generateBlockStateModels(generator: BlockStateModelGenerator) {
        ModBlocks.run { arrayOf(
            RUBY_BLOCK,
            RAW_RUBY_BLOCK,
            RUBY_ORE,
            DEEPSLATE_RUBY_ORE,
            NETHER_RUBY_ORE,
            END_STONE_RUBY_ORE,
            SOUND_BLOCK
        ) }.forEach { generator.registerSimpleCubeAll(it) }
    }

    override fun generateItemModels(generator: ItemModelGenerator) {
        ModItems.run { arrayOf(
            RUBY,
            RAW_RUBY,
            COAL_BRIQUETTE,
            TOMATO,
            METAL_DETECTOR
        ) }.forEach { generator.register(it, Models.GENERATED) }
    }
}