package top.srcres258.tutorialmod

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.minecraft.registry.RegistryBuilder
import net.minecraft.registry.RegistryKeys
import top.srcres258.tutorialmod.datagen.*
import top.srcres258.tutorialmod.world.ModConfiguredFeatures
import top.srcres258.tutorialmod.world.ModPlacedFeatures
import top.srcres258.tutorialmod.world.biome.ModBiomes

object TutorialModDataGenerator : DataGeneratorEntrypoint {
	override fun onInitializeDataGenerator(fabricDataGenerator: FabricDataGenerator) {
		fabricDataGenerator.createPack().let { pack ->
			pack.addProvider(::ModBlockTagProvider)
			pack.addProvider(::ModItemTagProvider)
			pack.addProvider(::ModLootTableProvider)
			pack.addProvider(::ModModelProvider)
			pack.addProvider(::ModRecipeProvider)
			pack.addProvider(::ModPoiTagProvider)
			pack.addProvider(::ModWorldGenerator)
		}
	}

	override fun buildRegistry(registryBuilder: RegistryBuilder) {
		registryBuilder.run {
			addRegistry(RegistryKeys.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap)
			addRegistry(RegistryKeys.PLACED_FEATURE, ModPlacedFeatures::bootstrap)
			addRegistry(RegistryKeys.BIOME, ModBiomes::boostrap)
		}
	}
}