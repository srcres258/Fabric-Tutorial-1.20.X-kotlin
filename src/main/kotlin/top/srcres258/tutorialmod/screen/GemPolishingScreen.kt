package top.srcres258.tutorialmod.screen

import com.mojang.blaze3d.systems.RenderSystem
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.screen.ingame.HandledScreen
import net.minecraft.client.render.GameRenderer
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import top.srcres258.tutorialmod.TutorialMod

private val TEXTURE = Identifier(TutorialMod.MOD_ID, "textures/gui/gem_polishing_station_gui.png")

class GemPolishingScreen(
    handler: GemPolishingScreenHandler,
    inventory: PlayerInventory,
    title: Text
) : HandledScreen<GemPolishingScreenHandler>(handler, inventory, title) {
    override fun init() {
        super.init()
        titleY = 1000
        playerInventoryTitleY = 1000
    }

    override fun drawBackground(context: DrawContext, delta: Float, mouseX: Int, mouseY: Int) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram)
        RenderSystem.setShaderColor(1F, 1F, 1F, 1F)
        RenderSystem.setShaderTexture(0, TEXTURE)
        val x = (width - backgroundWidth) / 2
        val y = (height - backgroundHeight) / 2

        context.drawTexture(TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight)

        renderProgressArrow(context, x, y)
    }

    private fun renderProgressArrow(context: DrawContext, x: Int, y: Int) {
        if (handler.isCrafting) {
            context.drawTexture(TEXTURE, x + 85, y + 30, 176, 0, 8, handler.scaledProgress)
        }
    }

    override fun render(context: DrawContext, mouseX: Int, mouseY: Int, delta: Float) {
        renderBackground(context, mouseX, mouseY, delta)
        super.render(context, mouseX, mouseY, delta)
        drawMouseoverTooltip(context, mouseX, mouseY)
    }
}