package top.srcres258.tutorialmod.world.tree

import net.minecraft.world.gen.foliage.FoliagePlacerType
import top.srcres258.tutorialmod.TutorialMod
import top.srcres258.tutorialmod.mixin.FoliagePlacerTypeInvoker
import top.srcres258.tutorialmod.world.tree.custom.ChestnutFoliagePlacer

object ModFoliagePlacerTypes {
    val CHESTNUT_FOLIAGE_PLACER: FoliagePlacerType<*> = FoliagePlacerTypeInvoker.callRegister(
        "chestnut_foliage_placer", ChestnutFoliagePlacer.CODEC)

    fun register() {
        TutorialMod.LOGGER.info("Registering Foliage Placer for ${TutorialMod.MOD_ID}")
    }
}