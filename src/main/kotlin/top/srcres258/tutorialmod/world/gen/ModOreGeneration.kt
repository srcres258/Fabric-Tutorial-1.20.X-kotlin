package top.srcres258.tutorialmod.world.gen

import net.fabricmc.fabric.api.biome.v1.BiomeModifications
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors
import net.minecraft.world.gen.GenerationStep
import top.srcres258.tutorialmod.world.ModPlacedFeatures

object ModOreGeneration {
    fun generateOres() {
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
            GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.RUBY_ORE_PLACED_KEY)
        BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(),
            GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.NETHER_RUBY_ORE_PLACED_KEY)
        BiomeModifications.addFeature(BiomeSelectors.foundInTheEnd(),
            GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.END_RUBY_ORE_PLACED_KEY)
    }
}