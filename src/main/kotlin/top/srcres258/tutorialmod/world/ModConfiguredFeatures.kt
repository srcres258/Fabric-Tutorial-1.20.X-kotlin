package top.srcres258.tutorialmod.world

import net.minecraft.block.Blocks
import net.minecraft.registry.Registerable
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.BlockTags
import net.minecraft.structure.rule.BlockMatchRuleTest
import net.minecraft.structure.rule.TagMatchRuleTest
import net.minecraft.util.Identifier
import net.minecraft.world.gen.feature.ConfiguredFeature
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.FeatureConfig
import net.minecraft.world.gen.feature.OreFeatureConfig
import top.srcres258.tutorialmod.TutorialMod
import top.srcres258.tutorialmod.block.ModBlocks

object ModConfiguredFeatures {
    val RUBY_ORE_KEY: RegistryKey<ConfiguredFeature<*, *>> = registerKey("ruby_ore")
    val NETHER_RUBY_ORE_KEY: RegistryKey<ConfiguredFeature<*, *>> = registerKey("nether_ruby_ore")
    val END_RUBY_ORE_KEY: RegistryKey<ConfiguredFeature<*, *>> = registerKey("end_ruby_ore")

    fun bootstrap(context: Registerable<ConfiguredFeature<*, *>>) {
        val stoneReplacables = TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES)
        val deepslateReplacables = TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES)
        val netherReplacables = TagMatchRuleTest(BlockTags.BASE_STONE_NETHER)
        val endReplacables = BlockMatchRuleTest(Blocks.END_STONE)

        val overworldRubyOres = listOf(
            OreFeatureConfig.createTarget(stoneReplacables, ModBlocks.RUBY_ORE.defaultState),
            OreFeatureConfig.createTarget(deepslateReplacables, ModBlocks.DEEPSLATE_RUBY_ORE.defaultState)
        )
        val netherRubyOres = listOf(OreFeatureConfig.createTarget(netherReplacables, ModBlocks.NETHER_RUBY_ORE.defaultState))
        val endRubyOres = listOf(OreFeatureConfig.createTarget(endReplacables, ModBlocks.END_STONE_RUBY_ORE.defaultState))

        fun reg(key: RegistryKey<ConfiguredFeature<*, *>>, ores: List<OreFeatureConfig.Target>) {
            register(context, key, Feature.ORE, OreFeatureConfig(ores, 12))
        }
        reg(RUBY_ORE_KEY, overworldRubyOres)
        reg(NETHER_RUBY_ORE_KEY, netherRubyOres)
        reg(END_RUBY_ORE_KEY, endRubyOres)
    }

    private fun registerKey(name: String): RegistryKey<ConfiguredFeature<*, *>> =
        RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier(TutorialMod.MOD_ID, name))

    private fun <FC : FeatureConfig, F : Feature<FC>> register(
        context: Registerable<ConfiguredFeature<*, *>>,
        key: RegistryKey<ConfiguredFeature<*, *>>,
        feature: F,
        configuration: FC
    ) = context.register(key, ConfiguredFeature(feature, configuration))
}