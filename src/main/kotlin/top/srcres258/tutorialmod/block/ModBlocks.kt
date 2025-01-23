package top.srcres258.tutorialmod.block

import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.block.ExperienceDroppingBlock
import net.minecraft.item.BlockItem
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.util.Identifier
import net.minecraft.util.math.intprovider.UniformIntProvider
import top.srcres258.tutorialmod.TutorialMod
import top.srcres258.tutorialmod.block.custom.SoundBlock

object ModBlocks {
    val RUBY_BLOCK: Block = registerBlock("ruby_block",
        Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).sounds(BlockSoundGroup.AMETHYST_BLOCK)))
    val RAW_RUBY_BLOCK: Block = registerBlock("raw_ruby_block",
        Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).sounds(BlockSoundGroup.AMETHYST_BLOCK)))

    val RUBY_ORE: Block = registerBlock("ruby_ore",
        ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.STONE).strength(2F),
            UniformIntProvider.create(2, 5)))
    val DEEPSLATE_RUBY_ORE: Block = registerBlock("deepslate_ruby_ore",
        ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.DEEPSLATE).strength(4F),
            UniformIntProvider.create(2, 5)))
    val NETHER_RUBY_ORE: Block = registerBlock("nether_ruby_ore",
        ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.NETHERRACK).strength(1.5F),
            UniformIntProvider.create(2, 5)))
    val END_STONE_RUBY_ORE: Block = registerBlock("end_stone_ruby_ore",
        ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.END_STONE).strength(4F),
            UniformIntProvider.create(4, 7)))

    val SOUND_BLOCK: Block = registerBlock("sound_block",
        SoundBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)))

    private fun registerBlock(name: String, block: Block) =
        Registry.register(
            Registries.BLOCK,
            Identifier(TutorialMod.MOD_ID, name),
            block.also { registerBlockItem(name, it) }
        )

    private fun registerBlockItem(name: String, block: Block) =
        Registry.register(
            Registries.ITEM,
            Identifier(TutorialMod.MOD_ID, name),
            BlockItem(block, FabricItemSettings())
        )

    fun registerModBlocks() {
        TutorialMod.LOGGER.info("Registering ModBlocks for ${TutorialMod.MOD_ID}")
    }
}