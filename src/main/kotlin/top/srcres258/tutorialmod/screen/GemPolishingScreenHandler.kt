package top.srcres258.tutorialmod.screen

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.item.ItemStack
import net.minecraft.network.PacketByteBuf
import net.minecraft.screen.ArrayPropertyDelegate
import net.minecraft.screen.PropertyDelegate
import net.minecraft.screen.ScreenHandler
import net.minecraft.screen.slot.Slot
import top.srcres258.tutorialmod.block.entity.GemPolishingStationBlockEntity

class GemPolishingScreenHandler(
    syncId: Int,
    playerInventory: PlayerInventory,
    val blockEntity: GemPolishingStationBlockEntity,
    private val propertyDelegate: PropertyDelegate
) : ScreenHandler(ModScreenHandlers.GEM_POLISHING_SCREEN_HANDLER, syncId) {
    init {
        val inventory = blockEntity
        checkSize(inventory, 2)
        inventory.onOpen(playerInventory.player)

        addSlot(Slot(inventory, 0, 80, 11))
        addSlot(Slot(inventory, 1, 80, 59))

        addPlayerInventory(playerInventory)
        addPlayerHotbar(playerInventory)

        addProperties(propertyDelegate)
    }

    constructor(
        syncId: Int,
        inventory: PlayerInventory,
        buf: PacketByteBuf
    ) : this(
        syncId,
        inventory,
        inventory.player.world.getBlockEntity(buf.readBlockPos()) as GemPolishingStationBlockEntity,
        ArrayPropertyDelegate(2)
    )

    val isCrafting
        get() = propertyDelegate[0] > 0

    val scaledProgress: Int
        get() {
            val progress = propertyDelegate[0]
            val maxProgress = propertyDelegate[1] // Max Progress
            val progressArrowSize = 26 // This is the width in pixels of your arrow

            return if (maxProgress != 0 && progress != 0) progress * progressArrowSize / maxProgress else 0
        }

    override fun quickMove(player: PlayerEntity, slotN: Int): ItemStack {
        var newStack = ItemStack.EMPTY
        val slot = slots[slotN]
        if (slot.hasStack()) {
            val originalStack = slot.stack
            newStack = originalStack.copy()
            val inventory = blockEntity
            if (slotN < inventory.size()) {
                if (!insertItem(originalStack, inventory.size(), slots.size, true)) {
                    return ItemStack.EMPTY
                }
            } else if (!insertItem(originalStack, 0, inventory.size(), false)) {
                return ItemStack.EMPTY
            }

            if (originalStack.isEmpty) {
                slot.stack = ItemStack.EMPTY
            } else {
                slot.markDirty()
            }
        }

        return newStack
    }

    override fun canUse(player: PlayerEntity) = blockEntity.canPlayerUse(player)

    private fun addPlayerInventory(inv: PlayerInventory) {
        for (i in 0 ..< 3) {
            for (j in 0 ..< 9) {
                addSlot(Slot(inv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18))
            }
        }
    }

    private fun addPlayerHotbar(inv: PlayerInventory) {
        for (i in 0 ..< 9) {
            addSlot(Slot(inv, i, 8 + i * 18, 142))
        }
    }
}