package top.srcres258.tutorialmod

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap
import net.minecraft.client.render.RenderLayer
import top.srcres258.tutorialmod.block.ModBlocks

object TutorialModClient : ClientModInitializer {
    override fun onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.run {
            RenderLayer.getCutout().let { cutout ->
                putBlock(ModBlocks.RUBY_DOOR, cutout)
                putBlock(ModBlocks.RUBY_TRAPDOOR, cutout)
            }
        }
    }
}