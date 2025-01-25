package top.srcres258.tutorialmod.block.entity

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.Inventories
import net.minecraft.inventory.SimpleInventory
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NbtCompound
import net.minecraft.network.PacketByteBuf
import net.minecraft.recipe.RecipeEntry
import net.minecraft.registry.DynamicRegistryManager
import net.minecraft.screen.PropertyDelegate
import net.minecraft.screen.ScreenHandler
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.text.Text
import net.minecraft.util.collection.DefaultedList
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import top.srcres258.tutorialmod.recipe.GemPolishingRecipe
import top.srcres258.tutorialmod.screen.GemPolishingScreenHandler
import java.util.Optional

private const val INPUT_SLOT = 0
private const val OUTPUT_SLOT = 1

class GemPolishingStationBlockEntity(
    pos: BlockPos,
    state: BlockState
) : BlockEntity(ModBlockEntities.GEM_POLISHING_STATION_BLOCK_ENTITY, pos, state),
    ExtendedScreenHandlerFactory, ImplementedInventory {
    private val inventory: DefaultedList<ItemStack> = DefaultedList.ofSize(2, ItemStack.EMPTY)

    private val propertyDelegate = object : PropertyDelegate {
        override fun get(index: Int) = when (index) {
            0 -> this@GemPolishingStationBlockEntity.progress
            1 -> this@GemPolishingStationBlockEntity.maxProgress
            else -> 0
        }

        override fun set(index: Int, value: Int) {
            when (index) {
                0 -> this@GemPolishingStationBlockEntity.progress = value
                1 -> this@GemPolishingStationBlockEntity.maxProgress = value
            }
        }

        override fun size() = 2
    }

    private var progress = 0
    private var maxProgress = 72

    override fun writeScreenOpeningData(player: ServerPlayerEntity, buf: PacketByteBuf) {
        buf.writeBlockPos(pos)
    }

    override fun getDisplayName(): Text = Text.literal("Gem Polishing Station")

    override val items: DefaultedList<ItemStack>
        get() = inventory

    override fun writeNbt(nbt: NbtCompound) {
        super.writeNbt(nbt)
        Inventories.writeNbt(nbt, inventory)
        nbt.putInt("gem_polishing_station.progress", progress)
    }

    override fun readNbt(nbt: NbtCompound) {
        super.readNbt(nbt)
        Inventories.readNbt(nbt, inventory)
        progress = nbt.getInt("gem_polishing_station.progress")
    }

    override fun createMenu(syncId: Int, playerInventory: PlayerInventory, player: PlayerEntity): ScreenHandler =
        GemPolishingScreenHandler(syncId, playerInventory, this, propertyDelegate)

    fun tick(world: World, pos: BlockPos, state: BlockState) {
        if (world.isClient) {
            return
        }

        if (isOutputSlotEmptyOrReceivable) {
            if (hasRecipe) {
                increaseCraftProgress()
                markDirty(world, pos, state)

                if (hasCraftingFinished) {
                    craftItem()
                    resetProgress()
                }
            } else {
                resetProgress()
            }
        } else {
            resetProgress()
            markDirty(world, pos, state)
        }
    }

    private fun resetProgress() {
        progress = 0
    }

    private fun craftItem() {
        // We're sure that the current recipe is present when this method is called,
        // so call Optional.get without checking.
        val recipe = currentRecipe.get()
        val curOutput = getStack(OUTPUT_SLOT)
        val recipeOutput = recipe.value.getResult(DynamicRegistryManager.EMPTY)

        removeStack(INPUT_SLOT, 1)
        setStack(OUTPUT_SLOT, ItemStack(recipeOutput.item, curOutput.count + recipeOutput.count))
    }

    private val hasCraftingFinished
        get() = progress >= maxProgress

    private fun increaseCraftProgress() {
        progress++
    }

    private val hasRecipe: Boolean
        get() {
            val recipe = currentRecipe
            return if (recipe.isPresent) {
                val recipeOutput = recipe.get().value.getResult(DynamicRegistryManager.EMPTY)
                canInsertAmountIntoOutputSlot(recipeOutput) && canInsertItemIntoOutputSlot(recipeOutput.item)
            } else {
                false
            }
        }

    private val currentRecipe: Optional<RecipeEntry<GemPolishingRecipe>>
        get() = world.let { world ->
            if (world == null) {
                Optional.empty()
            } else {
                val size = size()
                world.recipeManager.getFirstMatch(
                    GemPolishingRecipe.Type,
                    SimpleInventory(size).also { inv ->
                        for (i in 0 ..< size) {
                            inv.setStack(i, getStack(i))
                        }
                    },
                    world
                )
            }
        }

    private fun canInsertItemIntoOutputSlot(pItem: Item) =
        getStack(OUTPUT_SLOT).run { item == pItem || isEmpty }

    private fun canInsertAmountIntoOutputSlot(result: ItemStack) =
        getStack(OUTPUT_SLOT).let { output ->
            output.count + result.count <= output.maxCount
        }

    private val isOutputSlotEmptyOrReceivable
        get() = getStack(OUTPUT_SLOT).run { isEmpty || count < maxCount }

    override fun markDirty() {
        super<BlockEntity>.markDirty()
    }
}