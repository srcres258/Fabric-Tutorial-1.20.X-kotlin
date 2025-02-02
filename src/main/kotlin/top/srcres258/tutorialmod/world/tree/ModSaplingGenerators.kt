package top.srcres258.tutorialmod.world.tree

import net.minecraft.block.SaplingGenerator
import top.srcres258.tutorialmod.world.ModConfiguredFeatures
import java.util.*

object ModSaplingGenerators {
    val CHESTNUT = SaplingGenerator("chestnut", 0F,
        Optional.empty(),
        Optional.empty(),
        Optional.of(ModConfiguredFeatures.CHESTNUT_KEY),
        Optional.empty(),
        Optional.empty(),
        Optional.empty())
}