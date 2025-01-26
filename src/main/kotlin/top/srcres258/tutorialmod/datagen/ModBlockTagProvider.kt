package top.srcres258.tutorialmod.datagen

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.RegistryWrapper
import net.minecraft.registry.tag.BlockTags
import net.minecraft.registry.tag.TagKey
import net.minecraft.util.Identifier
import top.srcres258.tutorialmod.block.ModBlocks
import top.srcres258.tutorialmod.util.ModTags
import java.util.concurrent.CompletableFuture

class ModBlockTagProvider(
    output: FabricDataOutput,
    registriesFuture: CompletableFuture<RegistryWrapper.WrapperLookup>
) : FabricTagProvider.BlockTagProvider(output, registriesFuture) {
    override fun configure(p0: RegistryWrapper.WrapperLookup?) {
        val goctb = ::getOrCreateTagBuilder

        goctb(ModTags.Blocks.METAL_DETECTOR_DETECTABLE_BLOCKS)
            .add(ModBlocks.RUBY_ORE)
            .forceAddTag(BlockTags.GOLD_ORES)
            .forceAddTag(BlockTags.EMERALD_ORES)
            .forceAddTag(BlockTags.REDSTONE_ORES)
            .forceAddTag(BlockTags.LAPIS_ORES)
            .forceAddTag(BlockTags.DIAMOND_ORES)
            .forceAddTag(BlockTags.IRON_ORES)
            .forceAddTag(BlockTags.COPPER_ORES)
            .forceAddTag(BlockTags.COAL_ORES)

        goctb(BlockTags.PICKAXE_MINEABLE)
            .add(ModBlocks.RAW_RUBY_BLOCK)
            .add(ModBlocks.RUBY_BLOCK)
            .add(ModBlocks.RUBY_ORE)
            .add(ModBlocks.DEEPSLATE_RUBY_ORE)
            .add(ModBlocks.NETHER_RUBY_ORE)
            .add(ModBlocks.END_STONE_RUBY_ORE)
            .add(ModBlocks.SOUND_BLOCK)

        goctb(BlockTags.NEEDS_STONE_TOOL)
            .add(ModBlocks.RUBY_BLOCK)

        goctb(BlockTags.NEEDS_IRON_TOOL)
            .add(ModBlocks.RAW_RUBY_BLOCK)
            .add(ModBlocks.RUBY_ORE)

        goctb(TagKey.of(RegistryKeys.BLOCK, Identifier("fabric", "needs_tool_level_4")))
            .add(ModBlocks.END_STONE_RUBY_ORE)

        goctb(TagKey.of(RegistryKeys.BLOCK, Identifier("fabric", "needs_tool_level_5")))
            .add(ModBlocks.SOUND_BLOCK)

        goctb(BlockTags.FENCES)
            .add(ModBlocks.RUBY_FENCE)
        goctb(BlockTags.FENCE_GATES)
            .add(ModBlocks.RUBY_FENCE_GATE)
        goctb(BlockTags.WALLS)
            .add(ModBlocks.RUBY_WALL)

        goctb(BlockTags.PLANKS)
            .add(ModBlocks.CHESTNUT_PLANKS)

        goctb(BlockTags.LOGS_THAT_BURN)
            .add(ModBlocks.CHESTNUT_LOG)
            .add(ModBlocks.CHESTNUT_WOOD)
            .add(ModBlocks.STRIPPED_CHESTNUT_LOG)
            .add(ModBlocks.STRIPPED_CHESTNUT_WOOD)
    }
}