package top.srcres258.tutorialmod.recipe

import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier
import top.srcres258.tutorialmod.TutorialMod

object ModRecipes {
    fun registerRecipes() {
        Registry.register(Registries.RECIPE_SERIALIZER, Identifier(TutorialMod.MOD_ID, GemPolishingRecipe.Serializer.ID),
            GemPolishingRecipe.Serializer)
        Registry.register(Registries.RECIPE_TYPE, Identifier(TutorialMod.MOD_ID, GemPolishingRecipe.Type.ID),
            GemPolishingRecipe.Type)
    }
}