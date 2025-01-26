package top.srcres258.tutorialmod.datagen

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider
import net.minecraft.data.client.BlockStateModelGenerator
import net.minecraft.data.client.ItemModelGenerator
import net.minecraft.data.client.Model
import net.minecraft.data.client.Models
import net.minecraft.item.ArmorItem
import net.minecraft.util.Identifier
import top.srcres258.tutorialmod.block.ModBlocks
import top.srcres258.tutorialmod.block.custom.CornCropBlock
import top.srcres258.tutorialmod.block.custom.TomatoCropBlock
import top.srcres258.tutorialmod.item.ModItems
import java.util.*

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
        generator.registerCrop(ModBlocks.CORN_CROP, CornCropBlock.AGE,
            *(0 .. CornCropBlock.FIRST_STAGE_MAX_AGE + CornCropBlock.SECOND_STAGE_MAX_AGE).toList().toIntArray())

        generator.registerFlowerPotPlant(ModBlocks.DAHLIA, ModBlocks.POTTED_DAHLIA,
            BlockStateModelGenerator.TintType.NOT_TINTED)

        generator.registerSimpleState(ModBlocks.GEM_POLISHING_STATION)

        generator.registerLog(ModBlocks.CHESTNUT_LOG)
            .log(ModBlocks.CHESTNUT_LOG)
            .wood(ModBlocks.CHESTNUT_WOOD)
        generator.registerLog(ModBlocks.STRIPPED_CHESTNUT_LOG)
            .log(ModBlocks.STRIPPED_CHESTNUT_LOG)
            .wood(ModBlocks.STRIPPED_CHESTNUT_WOOD)
        generator.registerSimpleCubeAll(ModBlocks.CHESTNUT_LEAVES)
        generator.registerCubeAllModelTexturePool(ModBlocks.CHESTNUT_PLANKS)
            .family(ModBlocks.CHESTNUT_FAMILY)
    }

    override fun generateItemModels(generator: ItemModelGenerator) {
        ModItems.run { arrayOf(
            RUBY,
            RAW_RUBY,

            TOMATO,
            CORN,

            COAL_BRIQUETTE,
            METAL_DETECTOR,
            BAR_BRAWL_MUSIC_DISC,

            HANGING_CHESTNUT_SIGN,

            CHESTNUT_BOAT,
            CHESTNUT_CHEST_BOAT,

            DICE
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

        generator.register(ModItems.PORCUPINE_SPAWN_EGG,
            Model(Optional.of(Identifier("item/template_spawn_egg")), Optional.empty()))
    }
}