package top.srcres258.tutorialmod.util

import net.minecraft.block.Block
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import net.minecraft.util.Identifier
import top.srcres258.tutorialmod.TutorialMod

object ModTags {
    object Blocks {
        val METAL_DETECTOR_DETECTABLE_BLOCKS: TagKey<Block> =
            createTag("metal_detector_detectable_blocks")

        private fun createTag(name: String) =
            TagKey.of(RegistryKeys.BLOCK, Identifier(TutorialMod.MOD_ID, name))
    }
    object Items {
        private fun createTag(name: String) =
            TagKey.of(RegistryKeys.ITEM, Identifier(TutorialMod.MOD_ID, name))
    }
}