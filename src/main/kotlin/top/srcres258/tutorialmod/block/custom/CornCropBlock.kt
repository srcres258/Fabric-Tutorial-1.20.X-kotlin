package top.srcres258.tutorialmod.block.custom

import net.minecraft.block.*
import net.minecraft.server.world.ServerWorld
import net.minecraft.state.StateManager
import net.minecraft.state.property.IntProperty
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.random.Random
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.BlockView
import net.minecraft.world.World
import net.minecraft.world.WorldView
import top.srcres258.tutorialmod.item.ModItems
import kotlin.math.min

class CornCropBlock(settings: Settings?) : CropBlock(settings) {
    companion object {
        const val FIRST_STAGE_MAX_AGE = 7
        const val SECOND_STAGE_MAX_AGE = 1
        private val AGE_TO_SHAPE = arrayOf<VoxelShape>(
            createCuboidShape(0.0, 0.0, 0.0, 16.0, 2.0, 16.0),
            createCuboidShape(0.0, 0.0, 0.0, 16.0, 4.0, 16.0),
            createCuboidShape(0.0, 0.0, 0.0, 16.0, 6.0, 16.0),
            createCuboidShape(0.0, 0.0, 0.0, 16.0, 8.0, 16.0),
            createCuboidShape(0.0, 0.0, 0.0, 16.0, 10.0, 16.0),
            createCuboidShape(0.0, 0.0, 0.0, 16.0, 12.0, 16.0),
            createCuboidShape(0.0, 0.0, 0.0, 16.0, 14.0, 16.0),
            createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 16.0),
            createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 16.0)
        )

        val AGE: IntProperty = IntProperty.of("age", 0, FIRST_STAGE_MAX_AGE + SECOND_STAGE_MAX_AGE)
    }

    @Deprecated("Deprecated in Java")
    override fun getOutlineShape(
        state: BlockState,
        world: BlockView,
        pos: BlockPos,
        context: ShapeContext
    ) = AGE_TO_SHAPE[getAge(state)]

    @Deprecated("Deprecated in Java")
    override fun randomTick(state: BlockState, world: ServerWorld, pos: BlockPos, random: Random) {
        if (world.getBaseLightLevel(pos, 0) >= 9) {
            val curAge = getAge(state)
            if (curAge <= maxAge) {
                val f = getAvailableMoisture(this, world, pos)
                if (random.nextInt((25F / f).toInt() + 1) == 0) {
                    if (curAge == FIRST_STAGE_MAX_AGE) {
                        if (world.getBlockState(pos.up()).isOf(Blocks.AIR)) {
                            world.setBlockState(pos.up(), withAge(curAge + 1), 2)
                        }
                    } else {
                        world.setBlockState(pos, withAge(curAge + 1), 2)
                    }
                }
            }
        }
    }

    override fun applyGrowth(world: World, pos: BlockPos, state: BlockState) {
        val nextAge = min(getAge(state) + getGrowthAmount(world), maxAge)

        if (getAge(state) == FIRST_STAGE_MAX_AGE && world.getBlockState(pos.up()).isOf(Blocks.AIR)) {
            world.setBlockState(pos.up(), withAge(nextAge), 2)
        } else {
            world.setBlockState(pos, withAge(nextAge - 1), 2)
        }
    }

    @Deprecated("Deprecated in Java")
    override fun canPlaceAt(state: BlockState, world: WorldView, pos: BlockPos) =
        super.canPlaceAt(state, world, pos) || world.getBlockState(pos.down()).isOf(this) &&
                world.getBlockState(pos.down()).get(AGE) == FIRST_STAGE_MAX_AGE

    override fun getMaxAge() = FIRST_STAGE_MAX_AGE + SECOND_STAGE_MAX_AGE

    override fun getSeedsItem() = ModItems.CORN_SEEDS

    override fun getAgeProperty() = AGE

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        builder.add(AGE)
    }

    override fun isFertilizable(world: WorldView, pos: BlockPos, state: BlockState, isClient: Boolean) =
        if (getAge(state) < FIRST_STAGE_MAX_AGE) {
            true
        } else {
            getAge(state) == FIRST_STAGE_MAX_AGE && world.getBlockState(pos.up()).isOf(Blocks.AIR)
        }
}