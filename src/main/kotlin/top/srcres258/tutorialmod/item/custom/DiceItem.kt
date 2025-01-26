package top.srcres258.tutorialmod.item.custom

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.stat.Stats
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.world.World
import top.srcres258.tutorialmod.entity.custom.DiceProjectileEntity

class DiceItem(settings: Settings) : Item(settings) {
    override fun use(world: World, user: PlayerEntity, hand: Hand): TypedActionResult<ItemStack> {
        val stack = user.getStackInHand(hand)
        world.playSound(null, user.x, user.y, user.z,
            SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL,
            0.5F, 0.4F / (world.random.nextFloat() * 0.4F + 0.8F))

        if (!world.isClient) {
            world.spawnEntity(DiceProjectileEntity(user, world).also { entity ->
                entity.setItem(stack)
                entity.setVelocity(user, user.pitch, user.yaw, 0F, 1.5F, 1F)
            })
        }

        user.incrementStat(Stats.USED.getOrCreateStat(this))
        if (!user.abilities.creativeMode) {
            stack.decrement(1)
        }

        return TypedActionResult.success(stack, world.isClient)
    }
}