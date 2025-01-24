package top.srcres258.tutorialmod.item

import net.minecraft.item.ToolMaterial
import net.minecraft.recipe.Ingredient

enum class ModToolMaterial(
    private val miningLevel: Int,
    private val itemDurability: Int,
    private val miningSpeed: Float,
    private val attackDamage: Float,
    private val enchantability: Int,
    private val repairIngredient: () -> Ingredient
) : ToolMaterial {
    RUBY(5, 650, 4.5F, 3.5F, 26, { Ingredient.ofItems(ModItems.RUBY) });

    override fun getDurability() = itemDurability

    override fun getMiningSpeedMultiplier() = miningSpeed

    override fun getAttackDamage() = attackDamage

    override fun getMiningLevel() = miningLevel

    override fun getEnchantability() = enchantability

    override fun getRepairIngredient() = repairIngredient()
}