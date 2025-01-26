package top.srcres258.tutorialmod.block.custom

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.item.ItemPlacementContext
import net.minecraft.state.StateManager
import net.minecraft.state.property.DirectionProperty
import net.minecraft.util.math.Direction
import net.minecraft.util.math.random.Random

class DiceBlock(settings: Settings) : Block(settings) {
    companion object {
        val FACING: DirectionProperty = DirectionProperty.of("number",
            Direction.UP,
            Direction.NORTH,
            Direction.EAST,
            Direction.SOUTH,
            Direction.WEST,
            Direction.DOWN)

        fun randomDirection() =
            arrayOf(
                Direction.UP,
                Direction.NORTH,
                Direction.EAST,
                Direction.SOUTH,
                Direction.WEST,
                Direction.DOWN
            ).let { dirs -> dirs[Random.create().nextBetween(0, dirs.size - 1)] }
    }

    override fun getPlacementState(ctx: ItemPlacementContext): BlockState = randomBlockState()

    fun randomBlockState(): BlockState = defaultState.with(FACING, randomDirection())

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        builder.add(FACING)
    }
}