package top.srcres258.tutorialmod.world.biome.surface

import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.world.gen.surfacebuilder.MaterialRules
import top.srcres258.tutorialmod.block.ModBlocks
import top.srcres258.tutorialmod.world.biome.ModBiomes

object ModMaterialRules {
    private val DIRT = makeStateRule(Blocks.DIRT)
    private val GRASS_BLOCK = makeStateRule(Blocks.GRASS_BLOCK)
    private val RUBY = makeStateRule(ModBlocks.RUBY_BLOCK)
    private val RAW_RUBY = makeStateRule(ModBlocks.RAW_RUBY_BLOCK)

    fun makeRules(): MaterialRules.MaterialRule {
        val isAtOrAboveWaterLevel = MaterialRules.water(-1, 0)

        val grassSurface = MaterialRules.sequence(MaterialRules.condition(isAtOrAboveWaterLevel, GRASS_BLOCK), DIRT)

        return MaterialRules.sequence(
            MaterialRules.sequence(
                MaterialRules.condition(
                    MaterialRules.biome(ModBiomes.TEST_BIOME),
                    MaterialRules.condition(
                        MaterialRules.STONE_DEPTH_FLOOR,
                        RAW_RUBY
                    )
                ),
                MaterialRules.condition(
                    MaterialRules.STONE_DEPTH_CEILING,
                    RUBY
                )
            ),
            // Default to a grass and dirt surface
            MaterialRules.condition(
                MaterialRules.STONE_DEPTH_FLOOR,
                grassSurface
            )
        )
    }

    private fun makeStateRule(block: Block): MaterialRules.MaterialRule =
        MaterialRules.block(block.defaultState)
}