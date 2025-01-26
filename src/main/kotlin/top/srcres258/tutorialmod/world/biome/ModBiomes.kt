package top.srcres258.tutorialmod.world.biome

import net.minecraft.client.sound.MusicType
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnGroup
import net.minecraft.registry.Registerable
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.entry.RegistryEntry
import net.minecraft.sound.BiomeMoodSound
import net.minecraft.util.Identifier
import net.minecraft.world.biome.Biome
import net.minecraft.world.biome.BiomeEffects
import net.minecraft.world.biome.GenerationSettings
import net.minecraft.world.biome.SpawnSettings
import net.minecraft.world.gen.GenerationStep
import net.minecraft.world.gen.feature.DefaultBiomeFeatures
import net.minecraft.world.gen.feature.VegetationPlacedFeatures
import top.srcres258.tutorialmod.TutorialMod
import top.srcres258.tutorialmod.entity.ModEntities
import top.srcres258.tutorialmod.sound.ModSounds

object ModBiomes {
    val TEST_BIOME: RegistryKey<Biome> = RegistryKey.of(RegistryKeys.BIOME,
        Identifier(TutorialMod.MOD_ID, "test_biome"))

    fun boostrap(context: Registerable<Biome>) {
        context.register(TEST_BIOME, testBiome(context))
    }

    fun globalOverworldGeneration(builder: GenerationSettings.LookupBackedBuilder) {
        DefaultBiomeFeatures.addLandCarvers(builder)
        DefaultBiomeFeatures.addAmethystGeodes(builder)
        DefaultBiomeFeatures.addDungeons(builder)
        DefaultBiomeFeatures.addMineables(builder)
        DefaultBiomeFeatures.addSprings(builder)
        DefaultBiomeFeatures.addFrozenTopLayer(builder)
    }

    fun testBiome(context: Registerable<Biome>): Biome =
        Biome.Builder()
            .precipitation(true)
            .downfall(0.4F)
            .temperature(0.7F)
            .generationSettings(
                // biome settings
                GenerationSettings.LookupBackedBuilder(
                    context.getRegistryLookup(RegistryKeys.PLACED_FEATURE),
                    context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER)
                )
                    .also { builder ->
                        globalOverworldGeneration(builder)
                        DefaultBiomeFeatures.addMossyRocks(builder)
                        DefaultBiomeFeatures.addDefaultOres(builder)
                        DefaultBiomeFeatures.addExtraGoldOre(builder)

                        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION,
                            VegetationPlacedFeatures.TREES_PLAINS)
                        DefaultBiomeFeatures.addForestFlowers(builder)
                        DefaultBiomeFeatures.addLargeFerns(builder)

                        DefaultBiomeFeatures.addDefaultMushrooms(builder)
                        DefaultBiomeFeatures.addDefaultVegetation(builder)
                    }
                    .build()
            )
            .spawnSettings(
                // spawn settings
                SpawnSettings.Builder()
                    .also { builder ->
                        builder.spawn(SpawnGroup.CREATURE, SpawnSettings.SpawnEntry(
                            ModEntities.PORCUPINE, 2, 3, 5
                        ))
                        builder.spawn(SpawnGroup.CREATURE, SpawnSettings.SpawnEntry(
                            EntityType.WOLF, 5, 4, 4
                        ))

                        DefaultBiomeFeatures.addFarmAnimals(builder)
                        DefaultBiomeFeatures.addBatsAndMonsters(builder)
                    }
                    .build()
            )
            .effects(
                BiomeEffects.Builder()
                    .waterColor(0xE82E3B)
                    .waterFogColor(0xBF1B26)
                    .skyColor(0x30C918)
                    .grassColor(0x7F03FC)
                    .foliageColor(0xD203FC)
                    .fogColor(0x22A1E6)
                    .moodSound(BiomeMoodSound.CAVE)
                    .music(MusicType.createIngameMusic(RegistryEntry.of(ModSounds.BAR_BRAWL)))
                    .build()
            )
            .build()
}