package top.srcres258.tutorialmod.item.custom

import net.minecraft.entity.Entity
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ArmorItem
import net.minecraft.item.ArmorMaterial
import net.minecraft.item.ItemStack
import net.minecraft.world.World
import top.srcres258.tutorialmod.item.ModArmorMaterials

private val MATERIAL_TO_EFFECT_MAP = mapOf<ArmorMaterial, StatusEffectInstance>(
    Pair(ModArmorMaterials.RUBY, StatusEffectInstance(StatusEffects.HASTE, 400, 1, false, false, true))
)

class ModArmorItem(
    material: ArmorMaterial,
    type: Type,
    settings: Settings
) : ArmorItem(material, type, settings) {
    override fun inventoryTick(stack: ItemStack, world: World, entity: Entity, slot: Int, selected: Boolean) {
        if (!world.isClient) {
            if (entity is PlayerEntity && hasFullSuitOfArmorOn(entity)) {
                evaluateArmorEffects(entity)
            }
        }

        super.inventoryTick(stack, world, entity, slot, selected)
    }
}

private fun evaluateArmorEffects(player: PlayerEntity) {
    for ((mapArmorMaterial, mapStatusEffect) in MATERIAL_TO_EFFECT_MAP) {
        if (hasCorrectArmorOn(mapArmorMaterial, player)) {
            addStatusEffectForMaterial(player, mapArmorMaterial, mapStatusEffect)
        }
    }
}

private fun addStatusEffectForMaterial(
    player: PlayerEntity,
    mapArmorMaterial: ArmorMaterial,
    mapStatusEffect: StatusEffectInstance
) {
    val hasPlayerEffect = player.hasStatusEffect(mapStatusEffect.effectType)

    if (hasCorrectArmorOn(mapArmorMaterial, player) && !hasPlayerEffect) {
        player.addStatusEffect(StatusEffectInstance(mapStatusEffect))
    }
}

private fun hasFullSuitOfArmorOn(player: PlayerEntity) =
    player.inventory.armor.all { !it.isEmpty }

private fun hasCorrectArmorOn(material: ArmorMaterial, player: PlayerEntity) =
    player.inventory.armor.all { armorStack ->
        val armor = armorStack.item
        if (armor is ArmorItem) {
            armor.material == material
        } else {
            false
        }
    }