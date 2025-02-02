package top.srcres258.tutorialmod.block.custom

import com.mojang.serialization.MapCodec
import net.minecraft.block.*
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.BlockEntityTicker
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.ItemScatterer
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.BlockView
import net.minecraft.world.World
import top.srcres258.tutorialmod.block.entity.GemPolishingStationBlockEntity
import top.srcres258.tutorialmod.block.entity.ModBlockEntities

private val SHAPE: VoxelShape = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 12.0, 16.0)

class GemPolishingStationBlock(settings: Settings) : BlockWithEntity(settings) {
    companion object {
        val CODEC: MapCodec<GemPolishingStationBlock> = createCodec(::GemPolishingStationBlock)
    }

    override fun getCodec(): MapCodec<out BlockWithEntity> = CODEC

    @Deprecated("Deprecated in Java")
    override fun getOutlineShape(
        state: BlockState?,
        world: BlockView?,
        pos: BlockPos?,
        context: ShapeContext?
    ) = SHAPE

    @Deprecated("Deprecated in Java")
    override fun getRenderType(state: BlockState?) = BlockRenderType.MODEL

    override fun createBlockEntity(pos: BlockPos, state: BlockState) =
        GemPolishingStationBlockEntity(pos, state)

    @Deprecated("Deprecated in Java")
    override fun onStateReplaced(
        state: BlockState,
        world: World,
        pos: BlockPos,
        newState: BlockState,
        moved: Boolean
    ) {
        if (state.block != newState.block) {
            val blockEntity = world.getBlockEntity(pos)
            if (blockEntity is GemPolishingStationBlockEntity) {
                ItemScatterer.spawn(world, pos, blockEntity)
                world.updateComparators(pos, this)
            }
            super.onStateReplaced(state, world, pos, newState, moved)
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onUse(
        state: BlockState,
        world: World,
        pos: BlockPos,
        player: PlayerEntity,
        hand: Hand,
        hit: BlockHitResult
    ): ActionResult {
        if (!world.isClient) {
            val screenHandlerFactory = world.getBlockEntity(pos)

            if (screenHandlerFactory != null) {
                player.openHandledScreen(screenHandlerFactory as GemPolishingStationBlockEntity)
            }
        }

        return ActionResult.SUCCESS
    }

    override fun <T : BlockEntity> getTicker(
        world: World,
        state: BlockState,
        type: BlockEntityType<T>
    ): BlockEntityTicker<T>? =
        validateTicker(type, ModBlockEntities.GEM_POLISHING_STATION_BLOCK_ENTITY)
        { world1, pos, state1, blockEntity -> blockEntity.tick(world1, pos, state1) }
}