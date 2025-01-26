package top.srcres258.tutorialmod.compat

import me.shedaniel.rei.api.common.category.CategoryIdentifier
import me.shedaniel.rei.api.common.display.basic.BasicDisplay
import me.shedaniel.rei.api.common.entry.EntryIngredient
import me.shedaniel.rei.api.common.util.EntryIngredients
import me.shedaniel.rei.api.common.util.EntryStacks
import net.minecraft.recipe.RecipeEntry
import net.minecraft.registry.DynamicRegistryManager
import top.srcres258.tutorialmod.recipe.GemPolishingRecipe

class GemPolishingDisplay : BasicDisplay {
    constructor(inputs: List<EntryIngredient>, outputs: List<EntryIngredient>) : super(inputs, outputs)

    constructor(
        recipe: RecipeEntry<GemPolishingRecipe>
    ) : super(
        getInputList(recipe.value),
        listOf(EntryIngredient.of(EntryStacks.of(recipe.value.getResult(DynamicRegistryManager.EMPTY))))
    )

    override fun getCategoryIdentifier(): CategoryIdentifier<*> = GemPolishingCategory.GEM_POLISHING
}

private fun getInputList(recipe: GemPolishingRecipe?): List<EntryIngredient> =
    if (recipe == null) {
        listOf()
    } else {
        listOf(EntryIngredients.ofIngredient(recipe.ingredients[0]))
    }