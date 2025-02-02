package top.srcres258.tutorialmod.recipe

import com.mojang.serialization.Codec
import com.mojang.serialization.DataResult
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.inventory.SimpleInventory
import net.minecraft.item.ItemStack
import net.minecraft.network.PacketByteBuf
import net.minecraft.recipe.Ingredient
import net.minecraft.recipe.Recipe
import net.minecraft.recipe.RecipeSerializer
import net.minecraft.recipe.RecipeType
import net.minecraft.registry.DynamicRegistryManager
import net.minecraft.util.collection.DefaultedList
import net.minecraft.util.dynamic.Codecs
import net.minecraft.world.World

class GemPolishingRecipe(
    private val recipeItems: List<Ingredient>,
    private val output: ItemStack
) : Recipe<SimpleInventory> {
    override fun matches(inventory: SimpleInventory, world: World) =
        if (world.isClient) {
            false
        } else {
            recipeItems[0].test(inventory.getStack(0))
        }

    override fun craft(inventory: SimpleInventory, registryManager: DynamicRegistryManager) = output

    override fun fits(width: Int, height: Int) = true

    override fun getResult(registryManager: DynamicRegistryManager) = output

    override fun getIngredients(): DefaultedList<Ingredient> =
        DefaultedList.ofSize<Ingredient>(recipeItems.size)
            .also { list ->
                list.addAll(recipeItems)
            }

    override fun getSerializer() = Serializer

    override fun getType() = Type

    object Type : RecipeType<GemPolishingRecipe> {
        const val ID = "gem_polishing"

        override fun toString() = ID
    }

    object Serializer : RecipeSerializer<GemPolishingRecipe> {
        const val ID = "gem_polishing"

        val CODEC: Codec<GemPolishingRecipe> = RecordCodecBuilder.create { ins ->
            ins.group(
                validateAmount(Ingredient.DISALLOW_EMPTY_CODEC, 9)
                    .fieldOf("ingredients")
                    .forGetter(GemPolishingRecipe::getIngredients),
                ItemStack.RECIPE_RESULT_CODEC
                    .fieldOf("output")
                    .forGetter { r -> r.output }
            ).apply(ins, ::GemPolishingRecipe)
        }

        private fun validateAmount(delegate: Codec<Ingredient>, maxSize: Int) =
            Codecs.validate(
                Codecs.validate(delegate.listOf()) { list ->
                    if (list.size > maxSize) {
                        DataResult.error { "Recipe has too many ingredients!" }
                    } else {
                        DataResult.success(list)
                    }
                }
            ) { list ->
                if (list.isEmpty()) {
                    DataResult.error { "Recipe has no ingredients!" }
                } else {
                    DataResult.success(list)
                }
            }

        override fun codec() = CODEC

        override fun read(buf: PacketByteBuf) =
            GemPolishingRecipe(
                // inputs
                DefaultedList.ofSize(buf.readInt(), Ingredient.EMPTY)
                    .also { inputs ->
                        for (i in 0 ..< inputs.size) {
                            inputs[i] = Ingredient.fromPacket(buf)
                        }
                    },
                // output
                buf.readItemStack()
            )

        override fun write(buf: PacketByteBuf, recipe: GemPolishingRecipe) {
            buf.writeInt(recipe.ingredients.size)

            for (ingredient in recipe.ingredients) {
                ingredient.write(buf)
            }

            // Since the DynamicRegistryManager argument is not used,
            // it's okay to pass in any one implementation of DynamicRegistryManager.
            buf.writeItemStack(recipe.getResult(DynamicRegistryManager.EMPTY))
        }

        override fun toString() = ID
    }
}