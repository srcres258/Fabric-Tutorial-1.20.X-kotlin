package top.srcres258.tutorialmod.item

import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.item.FoodComponent

object ModFoodComponents {
    val TOMATO: FoodComponent = FoodComponent.Builder()
        .hunger(3)
        .saturationModifier(0.25F)
        .statusEffect(StatusEffectInstance(StatusEffects.LUCK, 200), 0.25F)
        .build()
}