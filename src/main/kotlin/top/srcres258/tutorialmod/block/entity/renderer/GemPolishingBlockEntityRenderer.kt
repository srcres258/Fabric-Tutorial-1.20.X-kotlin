package top.srcres258.tutorialmod.block.entity.renderer

import net.minecraft.client.MinecraftClient
import net.minecraft.client.render.LightmapTextureManager
import net.minecraft.client.render.OverlayTexture
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.block.entity.BlockEntityRenderer
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory
import net.minecraft.client.render.model.json.ModelTransformationMode
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.RotationAxis
import net.minecraft.world.LightType
import net.minecraft.world.World
import top.srcres258.tutorialmod.block.entity.GemPolishingStationBlockEntity

class GemPolishingBlockEntityRenderer(
    context: BlockEntityRendererFactory.Context
) : BlockEntityRenderer<GemPolishingStationBlockEntity> {
    override fun render(
        entity: GemPolishingStationBlockEntity,
        tickDelta: Float,
        matrices: MatrixStack,
        vertexConsumers: VertexConsumerProvider,
        light: Int,
        overlay: Int
    ) {
        val itemRenderer = MinecraftClient.getInstance().itemRenderer
        val stack = entity.renderStack
        val world = entity.world ?: return

        matrices.run {
            push()

            translate(0.5F, 0.75F, 0.5F)
            scale(0.35F, 0.35F, 0.35F)
            multiply(RotationAxis.POSITIVE_X.rotationDegrees(270F))

            itemRenderer.renderItem(
                stack,
                ModelTransformationMode.GUI,
                getLightLevel(world, entity.pos),
                OverlayTexture.DEFAULT_UV,
                this,
                vertexConsumers,
                world,
                1
            )

            pop()
        }
    }
}

private fun getLightLevel(world: World, pos: BlockPos): Int {
    val bLight = world.getLightLevel(LightType.BLOCK, pos)
    val sLight = world.getLightLevel(LightType.SKY, pos)
    return LightmapTextureManager.pack(bLight, sLight)
}