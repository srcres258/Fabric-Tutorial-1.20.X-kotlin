package top.srcres258.tutorialmod.block.custom

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.CropBlock
import net.minecraft.state.StateManager
import net.minecraft.state.property.IntProperty
import net.minecraft.state.property.Properties
import top.srcres258.tutorialmod.item.ModItems

class TomatoCropBlock(settings: Settings) : CropBlock(settings) {
    companion object {
        const val MAX_AGE = Properties.AGE_5_MAX
        val AGE: IntProperty = Properties.AGE_5
    }

    override fun getSeedsItem() = ModItems.TOMATO_SEEDS

    override fun getAgeProperty() = AGE

    override fun getMaxAge() = MAX_AGE

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        builder.add(AGE)
    }
}