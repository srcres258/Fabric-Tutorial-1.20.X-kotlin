package top.srcres258.tutorialmod

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap
import net.minecraft.client.render.RenderLayer
import top.srcres258.tutorialmod.block.ModBlocks

object TutorialModClient : ClientModInitializer {
    override fun onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.run {
            RenderLayer.getCutout().let { cutout ->
                arrayOf(
                    ModBlocks.RUBY_DOOR,
                    ModBlocks.RUBY_TRAPDOOR,

                    ModBlocks.TOMATO_CROP,
                    ModBlocks.CORN_CROP
                ).forEach { putBlock(it, cutout) }
            }
        }
    }
}