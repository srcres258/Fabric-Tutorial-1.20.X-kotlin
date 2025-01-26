package top.srcres258.tutorialmod

import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricDefaultAttributeRegistry
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry
import net.fabricmc.fabric.api.registry.FuelRegistry
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import top.srcres258.tutorialmod.block.ModBlocks
import top.srcres258.tutorialmod.block.entity.ModBlockEntities
import top.srcres258.tutorialmod.entity.ModBoats
import top.srcres258.tutorialmod.entity.ModEntities
import top.srcres258.tutorialmod.entity.custom.PorcupineEntity
import top.srcres258.tutorialmod.item.ModItemGroups
import top.srcres258.tutorialmod.item.ModItems
import top.srcres258.tutorialmod.recipe.ModRecipes
import top.srcres258.tutorialmod.screen.ModScreenHandlers
import top.srcres258.tutorialmod.sound.ModSounds
import top.srcres258.tutorialmod.util.ModCustomTrades
import top.srcres258.tutorialmod.util.ModLootTableModifiers
import top.srcres258.tutorialmod.villager.ModVillagers
import top.srcres258.tutorialmod.world.gen.ModWorldGeneration

object TutorialMod : ModInitializer {
	const val MOD_ID = "tutorialmod"
    val LOGGER: Logger = LoggerFactory.getLogger(MOD_ID)

	override fun onInitialize() {
		ModItemGroups.registerItemGroups()

		ModItems.registerModItems()
		ModBlocks.registerModBlocks()

		ModLootTableModifiers.modifyLootTables()

		ModCustomTrades.registerCustomTrades()
		ModVillagers.registerVillagers()

		ModSounds.registerSounds()

		ModBlockEntities.registerBlockEntities()

		ModScreenHandlers.registerScreenHandlers()

		ModRecipes.registerRecipes()

		FuelRegistry.INSTANCE.add(ModItems.COAL_BRIQUETTE, 200) // 200 is for melting one item

		FabricDefaultAttributeRegistry.register(ModEntities.PORCUPINE, PorcupineEntity.createPorcupineAttributes())

		StrippableBlockRegistry.register(ModBlocks.CHESTNUT_LOG, ModBlocks.STRIPPED_CHESTNUT_LOG)
		StrippableBlockRegistry.register(ModBlocks.CHESTNUT_WOOD, ModBlocks.STRIPPED_CHESTNUT_WOOD)

		FlammableBlockRegistry.getDefaultInstance().run {
			add(ModBlocks.CHESTNUT_LOG, 5, 5)
			add(ModBlocks.CHESTNUT_WOOD, 5, 5)
			add(ModBlocks.STRIPPED_CHESTNUT_LOG, 5, 5)
			add(ModBlocks.STRIPPED_CHESTNUT_WOOD, 5, 5)

			add(ModBlocks.CHESTNUT_PLANKS, 5, 20)
			add(ModBlocks.CHESTNUT_LEAVES, 30, 60)
		}

		ModBoats.registerBoats()

		ModWorldGeneration.generateModWorldGen()
	}
}