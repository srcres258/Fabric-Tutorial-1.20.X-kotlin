package top.srcres258.tutorialmod.world.tree

import net.minecraft.world.gen.trunk.TrunkPlacerType
import top.srcres258.tutorialmod.TutorialMod
import top.srcres258.tutorialmod.mixin.TrunkPlacerTypeInvoker
import top.srcres258.tutorialmod.world.tree.custom.ChestnutTrunkPlacer

object ModTrunkPlacerTypes {
    val CHESTNUT_TRUNK_PLACER: TrunkPlacerType<*> = TrunkPlacerTypeInvoker.callRegister("chestnut_trunk_placer",
        ChestnutTrunkPlacer.CODEC)

    fun register() {
        TutorialMod.LOGGER.info("Registering Trunk Placers for ${TutorialMod.MOD_ID}")
    }
}