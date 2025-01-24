package top.srcres258.tutorialmod.block.custom

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.client.item.TooltipContext
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.text.Text
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.world.BlockView
import net.minecraft.world.World

class SoundBlock(settings: Settings) : Block(settings) {
    @Deprecated("Deprecated in Java")
    override fun onUse(
        state: BlockState,
        world: World,
        pos: BlockPos,
        player: PlayerEntity,
        hand: Hand,
        hit: BlockHitResult
    ): ActionResult {
        world.playSound(player, pos, SoundEvents.BLOCK_NOTE_BLOCK_XYLOPHONE.value(), SoundCategory.BLOCKS,
            1F, 1F)
        return ActionResult.SUCCESS
    }

    override fun appendTooltip(stack: ItemStack, world: BlockView?, tooltip: MutableList<Text>, options: TooltipContext) {
        tooltip.add(Text.literal("Makes a sweet sound when right-clicked"))
        super.appendTooltip(stack, world, tooltip, options)
    }
}