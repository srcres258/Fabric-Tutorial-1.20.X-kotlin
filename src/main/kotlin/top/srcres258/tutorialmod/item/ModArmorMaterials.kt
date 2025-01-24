package top.srcres258.tutorialmod.item

import net.minecraft.item.ArmorItem
import net.minecraft.item.ArmorMaterial
import net.minecraft.recipe.Ingredient
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import top.srcres258.tutorialmod.TutorialMod

private val BASE_DURABILITY = intArrayOf(11, 16, 15, 13)

enum class ModArmorMaterials(
    private val name0: String,
    private val durabilityMultiplier: Int,
    private val protectionAmounts: IntArray,
    private val enchantability: Int,
    private val equipSound: SoundEvent,
    private val toughness: Float,
    private val knockbackResistance: Float,
    private val repairIngredient: () -> Ingredient
) : ArmorMaterial {
    RUBY("ruby", 25, intArrayOf(3, 8, 6, 3), 19, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 2F, 0.1F,
        { Ingredient.ofItems(ModItems.RUBY) });

    override fun getDurability(type: ArmorItem.Type) = BASE_DURABILITY[type.ordinal] * durabilityMultiplier

    override fun getProtection(type: ArmorItem.Type) = protectionAmounts[type.ordinal]


    override fun getEnchantability() = enchantability

    override fun getEquipSound() = equipSound

    override fun getRepairIngredient() = repairIngredient()

    override fun getName() = "${TutorialMod.MOD_ID}:$name0"

    override fun getToughness() = toughness

    override fun getKnockbackResistance() = knockbackResistance
}