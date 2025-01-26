package top.srcres258.tutorialmod.world.tree.custom

import com.google.common.collect.ImmutableList
import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.block.BlockState
import net.minecraft.block.PillarBlock
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.math.random.Random
import net.minecraft.world.TestableWorld
import net.minecraft.world.gen.feature.TreeFeatureConfig
import net.minecraft.world.gen.foliage.FoliagePlacer
import net.minecraft.world.gen.trunk.TrunkPlacer
import net.minecraft.world.gen.trunk.TrunkPlacerType
import top.srcres258.tutorialmod.world.tree.ModTrunkPlacerTypes
import java.util.function.BiConsumer

class ChestnutTrunkPlacer(
    baseHeight: Int,
    firstRandomHeight: Int,
    secondRandomHeight: Int
) : TrunkPlacer(baseHeight, firstRandomHeight, secondRandomHeight) {
    companion object {
        val CODEC: Codec<ChestnutTrunkPlacer> = RecordCodecBuilder.create { objectInstance ->
            fillTrunkPlacerFields(objectInstance).apply(objectInstance, ::ChestnutTrunkPlacer)
        }
    }

    override fun getType(): TrunkPlacerType<*> = ModTrunkPlacerTypes.CHESTNUT_TRUNK_PLACER

    override fun generate(
        world: TestableWorld,
        replacer: BiConsumer<BlockPos, BlockState>,
        random: Random,
        height: Int,
        startPos: BlockPos,
        config: TreeFeatureConfig
    ): MutableList<FoliagePlacer.TreeNode> {
        setToDirt(world, replacer, random, startPos.down(), config)
        val height1 = height + random.nextBetween(firstRandomHeight, firstRandomHeight + 2) +
                random.nextBetween(secondRandomHeight - 1, secondRandomHeight + 1)

        for (i in 0 ..< height1) {
            getAndSetState(world, replacer, random, startPos.up(i), config)

            if (i % 2 == 0 && random.nextBoolean()) {
                val dirsAndAxes = arrayOf(
                    Pair(Direction.NORTH, Direction.Axis.Z),
                    Pair(Direction.SOUTH, Direction.Axis.Z),
                    Pair(Direction.EAST, Direction.Axis.X),
                    Pair(Direction.WEST, Direction.Axis.X),
                )

                for ((dir, axis) in dirsAndAxes) {
                    if (random.nextFloat() > 0.25F) {
                        for (x in 1 .. 4) {
                            val targetPos = startPos.up(i).offset(dir, x)
                            replacer.accept(
                                targetPos,
                                config.trunkProvider.get(random, targetPos)
                                    .with(PillarBlock.AXIS, axis)
                            )
                        }
                    }
                }
            }
        }

        return ImmutableList.of(FoliagePlacer.TreeNode(startPos.up(height1), 0, false))
    }
}