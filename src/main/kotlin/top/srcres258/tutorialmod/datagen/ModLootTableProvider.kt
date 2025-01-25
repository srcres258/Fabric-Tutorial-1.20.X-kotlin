package top.srcres258.tutorialmod.datagen

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider
import net.minecraft.block.Block
import net.minecraft.enchantment.Enchantments
import net.minecraft.item.Item
import net.minecraft.loot.condition.BlockStatePropertyLootCondition
import net.minecraft.loot.entry.ItemEntry
import net.minecraft.loot.function.ApplyBonusLootFunction
import net.minecraft.loot.function.SetCountLootFunction
import net.minecraft.loot.provider.number.UniformLootNumberProvider
import net.minecraft.predicate.StatePredicate
import top.srcres258.tutorialmod.block.ModBlocks
import top.srcres258.tutorialmod.block.custom.CornCropBlock
import top.srcres258.tutorialmod.block.custom.TomatoCropBlock
import top.srcres258.tutorialmod.item.ModItems

class ModLootTableProvider(dataOutput: FabricDataOutput) : FabricBlockLootTableProvider(dataOutput) {
    override fun generate() {
        addDrop(ModBlocks.RUBY_BLOCK)
        addDrop(ModBlocks.RAW_RUBY_BLOCK)
        addDrop(ModBlocks.SOUND_BLOCK)

        addDrop(ModBlocks.RUBY_ORE, copperLikeOreDrops(ModBlocks.RUBY_ORE, ModItems.RAW_RUBY))
        addDrop(ModBlocks.DEEPSLATE_RUBY_ORE, copperLikeOreDrops(ModBlocks.DEEPSLATE_RUBY_ORE, ModItems.RAW_RUBY))
        addDrop(ModBlocks.NETHER_RUBY_ORE, copperLikeOreDrops(ModBlocks.NETHER_RUBY_ORE, ModItems.RAW_RUBY))
        addDrop(ModBlocks.END_STONE_RUBY_ORE, copperLikeOreDrops(ModBlocks.END_STONE_RUBY_ORE, ModItems.RAW_RUBY))

        addDrop(ModBlocks.RUBY_STAIRS)
        addDrop(ModBlocks.RUBY_TRAPDOOR)
        addDrop(ModBlocks.RUBY_WALL)
        addDrop(ModBlocks.RUBY_FENCE)
        addDrop(ModBlocks.RUBY_FENCE_GATE)
        addDrop(ModBlocks.RUBY_BUTTON)
        addDrop(ModBlocks.RUBY_PRESSURE_PLATE)

        addDrop(ModBlocks.RUBY_DOOR, doorDrops(ModBlocks.RUBY_DOOR))
        addDrop(ModBlocks.RUBY_SLAB, slabDrops(ModBlocks.RUBY_SLAB))

        addDrop(ModBlocks.TOMATO_CROP, cropDrops(ModBlocks.TOMATO_CROP, ModItems.TOMATO, ModItems.TOMATO_SEEDS,
            BlockStatePropertyLootCondition.builder(ModBlocks.TOMATO_CROP)
                .properties(StatePredicate.Builder.create().exactMatch(TomatoCropBlock.AGE, TomatoCropBlock.MAX_AGE))))
        addDrop(ModBlocks.CORN_CROP, cropDrops(ModBlocks.CORN_CROP, ModItems.CORN, ModItems.CORN_SEEDS,
            BlockStatePropertyLootCondition.builder(ModBlocks.CORN_CROP)
                .properties(StatePredicate.Builder.create()
                    .exactMatch(CornCropBlock.AGE, CornCropBlock.FIRST_STAGE_MAX_AGE))
                .or(BlockStatePropertyLootCondition.builder(ModBlocks.CORN_CROP)
                    .properties(StatePredicate.Builder.create()
                        .exactMatch(CornCropBlock.AGE, CornCropBlock.FIRST_STAGE_MAX_AGE +
                                CornCropBlock.SECOND_STAGE_MAX_AGE)))))

        addDrop(ModBlocks.DAHLIA)
        addPottedPlantDrops(ModBlocks.POTTED_DAHLIA)
    }

    private fun copperLikeOreDrops(drop: Block, item: Item) =
        dropsWithSilkTouch(drop, applyExplosionDecay(drop,
            ItemEntry.builder(item)
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2F, 5F)))
                .apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE))))
}