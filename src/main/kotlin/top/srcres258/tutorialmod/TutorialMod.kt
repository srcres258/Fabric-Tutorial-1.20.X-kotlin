package top.srcres258.tutorialmod

import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.registry.FuelRegistry
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import top.srcres258.tutorialmod.block.ModBlocks
import top.srcres258.tutorialmod.item.ModItemGroups
import top.srcres258.tutorialmod.item.ModItems
import top.srcres258.tutorialmod.util.ModLootTableModifiers

object TutorialMod : ModInitializer {
	const val MOD_ID = "tutorialmod"
    val LOGGER: Logger = LoggerFactory.getLogger(MOD_ID)

	override fun onInitialize() {
		ModItemGroups.registerItemGroups()

		ModItems.registerModItems()
		ModBlocks.registerModBlocks()

		ModLootTableModifiers.modifyLootTables()

		FuelRegistry.INSTANCE.add(ModItems.COAL_BRIQUETTE, 200) // 200 is for melting one item
	}
}