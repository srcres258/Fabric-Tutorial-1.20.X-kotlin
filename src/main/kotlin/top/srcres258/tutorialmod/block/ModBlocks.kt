package top.srcres258.tutorialmod.block

import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.item.BlockItem
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.util.Identifier
import top.srcres258.tutorialmod.TutorialMod

object ModBlocks {
    val RUBY_BLOCK: Block = registerBlock("ruby_block",
        Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).sounds(BlockSoundGroup.AMETHYST_BLOCK)))
    val RAW_RUBY_BLOCK: Block = registerBlock("raw_ruby_block",
        Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).sounds(BlockSoundGroup.AMETHYST_BLOCK)))

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