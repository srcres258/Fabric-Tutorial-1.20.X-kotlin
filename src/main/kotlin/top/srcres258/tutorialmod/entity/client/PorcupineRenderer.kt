package top.srcres258.tutorialmod.entity.client

import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.render.entity.MobEntityRenderer
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.util.Identifier
import top.srcres258.tutorialmod.TutorialMod
import top.srcres258.tutorialmod.entity.custom.PorcupineEntity

private val TEXTURE = Identifier(TutorialMod.MOD_ID, "textures/entity/porcupine.png")

class PorcupineRenderer(
    context: EntityRendererFactory.Context
) : MobEntityRenderer<PorcupineEntity, PorcupineModel<PorcupineEntity>>(
    context,
    PorcupineModel(context.getPart(ModModelLayers.PORCUPINE)),
    0.6F
) {
    override fun getTexture(entity: PorcupineEntity?) = TEXTURE

    override fun render(
        mobEntity: PorcupineEntity,
        f: Float,
        g: Float,
        matrixStack: MatrixStack,
        vertexConsumerProvider: VertexConsumerProvider,
        i: Int
    ) {
        if (mobEntity.isBaby) {
            matrixStack.scale(0.5F, 0.5F, 0.5F)
        } else {
            matrixStack.scale(1F, 1F, 1F)
        }

        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i)
    }
}