package top.srcres258.tutorialmod.world

import net.minecraft.registry.Registerable
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.entry.RegistryEntry
import net.minecraft.util.Identifier
import net.minecraft.world.gen.YOffset
import net.minecraft.world.gen.feature.ConfiguredFeature
import net.minecraft.world.gen.feature.PlacedFeature
import net.minecraft.world.gen.feature.PlacedFeatures
import net.minecraft.world.gen.feature.VegetationPlacedFeatures
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier
import net.minecraft.world.gen.placementmodifier.PlacementModifier
import top.srcres258.tutorialmod.TutorialMod
import top.srcres258.tutorialmod.block.ModBlocks

object ModPlacedFeatures {
    val RUBY_ORE_PLACED_KEY: RegistryKey<PlacedFeature> = registerKey("ruby_ore_placed")
    val NETHER_RUBY_ORE_PLACED_KEY: RegistryKey<PlacedFeature> = registerKey("nether_ruby_ore_placed")
    val END_RUBY_ORE_PLACED_KEY: RegistryKey<PlacedFeature> = registerKey("end_ruby_ore_placed")

    val CHESTNUT_PLACED_KEY: RegistryKey<PlacedFeature> = registerKey("chestnut_placed")

    fun bootstrap(context: Registerable<PlacedFeature>) {
        val lookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE)

        fun reg(pfKey: RegistryKey<PlacedFeature>, cfKey: RegistryKey<ConfiguredFeature<*, *>>) {
            register(context, pfKey, lookup.getOrThrow(cfKey),
                ModOrePlacement.modifiersWithCount(12, // Veins per Chunk
                    HeightRangePlacementModifier.uniform(YOffset.fixed(-80), YOffset.fixed(80)))
            )
        }
        reg(RUBY_ORE_PLACED_KEY, ModConfiguredFeatures.RUBY_ORE_KEY)
        reg(NETHER_RUBY_ORE_PLACED_KEY, ModConfiguredFeatures.NETHER_RUBY_ORE_KEY)
        reg(END_RUBY_ORE_PLACED_KEY, ModConfiguredFeatures.END_RUBY_ORE_KEY)

        register(
            context,
            CHESTNUT_PLACED_KEY,
            lookup.getOrThrow(ModConfiguredFeatures.CHESTNUT_KEY),
            VegetationPlacedFeatures.treeModifiersWithWouldSurvive(
                PlacedFeatures.createCountExtraModifier(2, 0.1F, 2),
                ModBlocks.CHESTNUT_SAPLING
            )
        )
    }

    private fun registerKey(name: String) =
        RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier(TutorialMod.MOD_ID, name))

    private fun register(
        context: Registerable<PlacedFeature>,
        key: RegistryKey<PlacedFeature>,
        configuration: RegistryEntry<ConfiguredFeature<*, *>>,
        modifiers: List<PlacementModifier>
    ) {
        // The `toList` extension method means to create a copy of the given list.
        context.register(key, PlacedFeature(configuration, modifiers.toList()))
    }
}