package top.srcres258.tutorialmod.world.biome

import com.mojang.datafixers.util.Pair
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.util.Identifier
import net.minecraft.world.biome.Biome
import net.minecraft.world.biome.BiomeKeys
import net.minecraft.world.biome.source.util.MultiNoiseUtil
import terrablender.api.Region
import terrablender.api.RegionType
import java.util.function.Consumer

class ModOverworldRegion(
    name: Identifier,
    weight: Int
) : Region(name, RegionType.OVERWORLD, weight) {
    override fun addBiomes(
        registry: Registry<Biome>,
        mapper: Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>>
    ) {
        addModifiedVanillaOverworldBiomes(mapper) { builder ->
            builder.replaceBiome(BiomeKeys.FOREST, ModBiomes.TEST_BIOME)
        }
    }
}