package top.srcres258.tutorialmod.world

import net.minecraft.world.gen.placementmodifier.BiomePlacementModifier
import net.minecraft.world.gen.placementmodifier.CountPlacementModifier
import net.minecraft.world.gen.placementmodifier.PlacementModifier
import net.minecraft.world.gen.placementmodifier.RarityFilterPlacementModifier
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier

object ModOrePlacement {
    fun modifiers(countModifier: PlacementModifier, heightModifier: PlacementModifier): List<PlacementModifier> =
        listOf(countModifier, SquarePlacementModifier.of(), heightModifier, BiomePlacementModifier.of())

    fun modifiersWithCount(count: Int, heightModifier: PlacementModifier): List<PlacementModifier> =
        modifiers(CountPlacementModifier.of(count), heightModifier)

    fun modifiersWithRarity(chance: Int, heightModifier: PlacementModifier): List<PlacementModifier> =
        modifiers(RarityFilterPlacementModifier.of(chance), heightModifier)
}