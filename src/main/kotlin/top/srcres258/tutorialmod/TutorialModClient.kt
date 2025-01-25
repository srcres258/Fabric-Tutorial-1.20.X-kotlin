package top.srcres258.tutorialmod

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry
import net.minecraft.client.render.RenderLayer
import top.srcres258.tutorialmod.block.ModBlocks
import top.srcres258.tutorialmod.entity.ModEntities
import top.srcres258.tutorialmod.entity.client.ModModelLayers
import top.srcres258.tutorialmod.entity.client.PorcupineModel
import top.srcres258.tutorialmod.entity.client.PorcupineRenderer

object TutorialModClient : ClientModInitializer {
    override fun onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.run {
            RenderLayer.getCutout().let { cutout ->
                arrayOf(
                    ModBlocks.RUBY_DOOR,
                    ModBlocks.RUBY_TRAPDOOR,

                    ModBlocks.TOMATO_CROP,
                    ModBlocks.CORN_CROP,

                    ModBlocks.DAHLIA,
                    ModBlocks.POTTED_DAHLIA
                ).forEach { putBlock(it, cutout) }
            }
        }

        EntityRendererRegistry.register(ModEntities.PORCUPINE, ::PorcupineRenderer)
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.PORCUPINE) {
            PorcupineModel.getTexturedModelData()
        }
    }
}