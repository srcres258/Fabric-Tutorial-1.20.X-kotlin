package top.srcres258.tutorialmod

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import top.srcres258.tutorialmod.datagen.*

object TutorialModDataGenerator : DataGeneratorEntrypoint {
	override fun onInitializeDataGenerator(fabricDataGenerator: FabricDataGenerator) {
		fabricDataGenerator.createPack().let { pack ->
			pack.addProvider(::ModBlockTagProvider)
			pack.addProvider(::ModItemTagProvider)
			pack.addProvider(::ModLootTableProvider)
			pack.addProvider(::ModModelProvider)
			pack.addProvider(::ModRecipeProvider)
			pack.addProvider(::ModPoiTagProvider)
		}
	}
}