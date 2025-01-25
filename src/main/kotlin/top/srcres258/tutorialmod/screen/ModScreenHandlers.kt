package top.srcres258.tutorialmod.screen

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.screen.ScreenHandlerType
import net.minecraft.util.Identifier
import top.srcres258.tutorialmod.TutorialMod

object ModScreenHandlers {
    val GEM_POLISHING_SCREEN_HANDLER: ScreenHandlerType<GemPolishingScreenHandler> =
        Registry.register(Registries.SCREEN_HANDLER, Identifier(TutorialMod.MOD_ID, "gem_polishing"),
            ExtendedScreenHandlerType(::GemPolishingScreenHandler))

    fun registerScreenHandlers() {
        TutorialMod.LOGGER.info("Registering Screen Handlers for ${TutorialMod.MOD_ID}")
    }
}