package top.srcres258.tutorialmod.item.custom

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.client.item.TooltipContext
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.ItemUsageContext
import net.minecraft.text.Text
import net.minecraft.util.ActionResult
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import top.srcres258.tutorialmod.util.ModTags

class MetalDetectorItem(settings: Settings) : Item(settings) {
    override fun useOnBlock(context: ItemUsageContext): ActionResult {
        val player = context.player ?: return ActionResult.PASS

        if (!context.world.isClient) {
            val posClicked = context.blockPos
            var foundBlock = false

            for (i in 0 .. posClicked.y + 64) {
                val curPos = posClicked.down(i)
                val state = context.world.getBlockState(curPos)

                if (isValuableBlock(state)) {
                    outputValuableCoordinates(curPos, player, state.block)
                    foundBlock = true
                    break
                }
            }

            if (!foundBlock) {
                player.sendMessage(Text.literal("No Valuables Found!"))
            }
        }

        context.stack.damage(1, player) { playerEntity ->
            playerEntity.sendToolBreakStatus(playerEntity.activeHand)
        }

        return ActionResult.SUCCESS
    }

    override fun appendTooltip(stack: ItemStack, world: World?, tooltip: MutableList<Text>, context: TooltipContext) {
        tooltip.add(Text.translatable("tooltip.tutorialmod.metal_detector.tooltip"))
        super.appendTooltip(stack, world, tooltip, context)
    }
}

private fun outputValuableCoordinates(pos: BlockPos, player: PlayerEntity, block: Block) {
    player.sendMessage(Text.literal("Found ${block.asItem().name.string} at " +
            "(${pos.x}, ${pos.y}, ${pos.z})"), false)
}

private fun isValuableBlock(state: BlockState) = state.isIn(ModTags.Blocks.METAL_DETECTOR_DETECTABLE_BLOCKS)