package top.srcres258.tutorialmod.entity.custom

import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.projectile.thrown.ThrownItemEntity
import net.minecraft.item.Item
import net.minecraft.network.listener.ClientPlayPacketListener
import net.minecraft.network.packet.Packet
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.world.World
import top.srcres258.tutorialmod.block.ModBlocks
import top.srcres258.tutorialmod.block.custom.DiceBlock
import top.srcres258.tutorialmod.entity.ModEntities
import top.srcres258.tutorialmod.item.ModItems

class DiceProjectileEntity : ThrownItemEntity {
    constructor(entityType: EntityType<out ThrownItemEntity>, world: World)
            : super(entityType, world)

    constructor(livingEntity: LivingEntity, world: World)
            : super(ModEntities.DICE_PROJECTILE, livingEntity, world)

    override fun getDefaultItem(): Item = ModItems.DICE

    override fun createSpawnPacket(): Packet<ClientPlayPacketListener> =
        EntitySpawnS2CPacket(this)

    override fun onBlockHit(blockHitResult: BlockHitResult) {
        if (!world.isClient) {
            world.sendEntityStatus(this, 3)
            world.setBlockState(blockPos, (ModBlocks.DICE_BLOCK as DiceBlock).randomBlockState(), 3)
        }

        discard()
        super.onBlockHit(blockHitResult)
    }
}