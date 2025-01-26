package top.srcres258.tutorialmod.compat

import me.shedaniel.math.Rectangle
import me.shedaniel.rei.api.client.plugins.REIClientPlugin
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry
import me.shedaniel.rei.api.client.registry.screen.ScreenRegistry
import me.shedaniel.rei.api.common.util.EntryStacks
import top.srcres258.tutorialmod.block.ModBlocks
import top.srcres258.tutorialmod.recipe.GemPolishingRecipe
import top.srcres258.tutorialmod.screen.GemPolishingScreen

object TutorialModREIClientPlugin : REIClientPlugin {
    override fun registerCategories(registry: CategoryRegistry) {
        registry.add(GemPolishingCategory())

        registry.addWorkstations(GemPolishingCategory.GEM_POLISHING, EntryStacks.of(ModBlocks.GEM_POLISHING_STATION))
    }

    override fun registerDisplays(registry: DisplayRegistry) {
        registry.registerRecipeFiller(GemPolishingRecipe::class.java, GemPolishingRecipe.Type, ::GemPolishingDisplay)
    }

    override fun registerScreens(registry: ScreenRegistry) {
        registry.registerClickArea(
            { screen ->
                val x = (screen.width - screen.bgWidth) / 2
                val y = (screen.height - screen.bgHeight) / 2
                Rectangle(x + 83, y + 30, 12, 26)
            },
            GemPolishingScreen::class.java,
            GemPolishingCategory.GEM_POLISHING
        )
    }
}