package top.srcres258.tutorialmod.block.entity

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.inventory.Inventories
import net.minecraft.inventory.SidedInventory
import net.minecraft.item.ItemStack
import net.minecraft.util.collection.DefaultedList
import net.minecraft.util.math.Direction
import kotlin.math.min

interface ImplementedInventory : SidedInventory {
    companion object {
        fun of(items: DefaultedList<ItemStack>) =
            object : ImplementedInventory {
                override val items = items
            }

        fun ofSize(size: Int) = of(DefaultedList.ofSize(size, ItemStack.EMPTY))
    }

    val items: DefaultedList<ItemStack>

    override fun getAvailableSlots(side: Direction) = (0 ..< items.size).toList().toIntArray()

    override fun canInsert(slot: Int, stack: ItemStack, dir: Direction?) = true

    override fun canExtract(slot: Int, stack: ItemStack, dir: Direction) = true

    override fun size() = items.size

    override fun isEmpty() = !items.any { stack -> !stack.isEmpty }

    override fun getStack(slot: Int) = items[slot]

    override fun removeStack(slot: Int, amount: Int): ItemStack =
        Inventories.splitStack(items, slot, amount).also { result ->
            if (!result.isEmpty) {
                markDirty()
            }
        }

    override fun removeStack(slot: Int): ItemStack =
        Inventories.removeStack(items, slot)

    override fun setStack(slot: Int, stack: ItemStack) {
        items[slot] = stack
        stack.count = min(stack.count, maxCountPerStack)
        markDirty()
    }

    override fun clear() {
        items.clear()
    }

    override fun markDirty() {
        // Override if you want behavior.
    }

    override fun canPlayerUse(player: PlayerEntity) = true
}