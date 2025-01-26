package top.srcres258.tutorialmod.world.tree

import net.minecraft.block.sapling.SaplingGenerator
import net.minecraft.registry.RegistryKey
import net.minecraft.util.math.random.Random
import net.minecraft.world.gen.feature.ConfiguredFeature
import top.srcres258.tutorialmod.world.ModConfiguredFeatures

class ChestnutSaplingGenerator : SaplingGenerator() {
    override fun getTreeFeature(random: Random, bees: Boolean): RegistryKey<ConfiguredFeature<*, *>> =
        ModConfiguredFeatures.CHESTNUT_KEY
}