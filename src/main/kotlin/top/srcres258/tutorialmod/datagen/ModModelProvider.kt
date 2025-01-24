package top.srcres258.tutorialmod.datagen

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider
import net.minecraft.data.client.BlockStateModelGenerator
import net.minecraft.data.client.ItemModelGenerator
import net.minecraft.data.client.Models
import net.minecraft.item.ArmorItem
import top.srcres258.tutorialmod.block.ModBlocks
import top.srcres258.tutorialmod.block.custom.TomatoCropBlock
import top.srcres258.tutorialmod.item.ModItems

class ModModelProvider(output: FabricDataOutput) : FabricModelProvider(output) {
    override fun generateBlockStateModels(generator: BlockStateModelGenerator) {
        ModBlocks.run { arrayOf(
            RAW_RUBY_BLOCK,
            RUBY_ORE,
            DEEPSLATE_RUBY_ORE,
            NETHER_RUBY_ORE,
            END_STONE_RUBY_ORE,
            SOUND_BLOCK
        ) }.forEach { generator.registerSimpleCubeAll(it) }

        generator.registerCubeAllModelTexturePool(ModBlocks.RUBY_BLOCK).let { rubyPool ->
            rubyPool.stairs(ModBlocks.RUBY_STAIRS)
            rubyPool.slab(ModBlocks.RUBY_SLAB)
            rubyPool.button(ModBlocks.RUBY_BUTTON)
            rubyPool.pressurePlate(ModBlocks.RUBY_PRESSURE_PLATE)
            rubyPool.fence(ModBlocks.RUBY_FENCE)
            rubyPool.fenceGate(ModBlocks.RUBY_FENCE_GATE)
            rubyPool.wall(ModBlocks.RUBY_WALL)
        }

        generator.registerDoor(ModBlocks.RUBY_DOOR)
        generator.registerTrapdoor(ModBlocks.RUBY_TRAPDOOR)

        generator.registerCrop(ModBlocks.TOMATO_CROP, TomatoCropBlock.AGE,
            *(0 .. TomatoCropBlock.MAX_AGE).toList().toIntArray())
    }

    override fun generateItemModels(generator: ItemModelGenerator) {
        ModItems.run { arrayOf(
            RUBY,
            RAW_RUBY,

            COAL_BRIQUETTE,
            TOMATO,
            METAL_DETECTOR
        ) }.forEach { generator.register(it, Models.GENERATED) }

        ModItems.run { arrayOf(
            RUBY_PICKAXE,
            RUBY_AXE,
            RUBY_SHOVEL,
            RUBY_SWORD,
            RUBY_HOE
        ) }.forEach { generator.register(it, Models.HANDHELD) }

        ModItems.run { arrayOf(
            RUBY_HELMET,
            RUBY_CHESTPLATE,
            RUBY_LEGGINGS,
            RUBY_BOOTS
        ) }.forEach { generator.registerArmor(it as ArmorItem) }
    }
}