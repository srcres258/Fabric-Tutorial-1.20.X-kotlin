package top.srcres258.tutorialmod.villager

import com.google.common.collect.ImmutableSet
import net.fabricmc.fabric.api.`object`.builder.v1.world.poi.PointOfInterestHelper
import net.minecraft.block.Block
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.sound.SoundEvents
import net.minecraft.util.Identifier
import net.minecraft.village.VillagerProfession
import net.minecraft.world.poi.PointOfInterestType
import top.srcres258.tutorialmod.TutorialMod
import top.srcres258.tutorialmod.block.ModBlocks

object ModVillagers {
    val SOUND_POI_KEY: RegistryKey<PointOfInterestType> = poiKey("soundpoi")
    val SOUND_POI: PointOfInterestType = registerPoi("soundpoi", ModBlocks.SOUND_BLOCK)

    val SOUND_MASTER: VillagerProfession = registerProfession("sound_master", SOUND_POI_KEY)

    private fun registerProfession(name: String, type: RegistryKey<PointOfInterestType>) =
        Registry.register(Registries.VILLAGER_PROFESSION, Identifier(TutorialMod.MOD_ID, name),
            VillagerProfession(name, { entry -> entry.matchesKey(type) }, { entry -> entry.matchesKey(type) },
                ImmutableSet.of(), ImmutableSet.of(), SoundEvents.ENTITY_VILLAGER_WORK_SHEPHERD))

    private fun registerPoi(name: String, block: Block) =
        PointOfInterestHelper.register(Identifier(TutorialMod.MOD_ID, name), 1, 1, block)

    private fun poiKey(name: String) =
        RegistryKey.of(RegistryKeys.POINT_OF_INTEREST_TYPE, Identifier(TutorialMod.MOD_ID, name))

    fun registerVillagers() {
        TutorialMod.LOGGER.info("Registering Villagers ${TutorialMod.MOD_ID}")
    }
}