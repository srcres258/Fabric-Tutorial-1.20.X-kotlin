package top.srcres258.tutorialmod

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import top.srcres258.tutorialmod.datagen.ModBlockTagProvider
import top.srcres258.tutorialmod.datagen.ModItemTagProvider
import top.srcres258.tutorialmod.datagen.ModLootTableProvider
import top.srcres258.tutorialmod.datagen.ModModelProvider
import top.srcres258.tutorialmod.datagen.ModRecipeProvider

object TutorialModDataGenerator : DataGeneratorEntrypoint {
	override fun onInitializeDataGenerator(fabricDataGenerator: FabricDataGenerator) {
		fabricDataGenerator.createPack().let { pack ->
			pack.addProvider(::ModBlockTagProvider)
			pack.addProvider(::ModItemTagProvider)
			pack.addProvider(::ModLootTableProvider)
			pack.addProvider(::ModModelProvider)
			pack.addProvider(::ModRecipeProvider)
		}
	}
}