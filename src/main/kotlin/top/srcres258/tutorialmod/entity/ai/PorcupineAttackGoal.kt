package top.srcres258.tutorialmod.entity.ai

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.ai.goal.MeleeAttackGoal
import net.minecraft.util.Hand
import top.srcres258.tutorialmod.entity.custom.PorcupineEntity
import kotlin.math.max

class PorcupineAttackGoal(
    private val entity: PorcupineEntity,
    speed: Double,
    pauseWhenMobIdle: Boolean
) : MeleeAttackGoal(entity, speed, pauseWhenMobIdle) {
    private var attackDelay = 20
    private var ticksUntilNextAttack = 20
    private var shouldCountTillNextAttack = false

    override fun start() {
        super.start()
        attackDelay = 20
        ticksUntilNextAttack = 20
    }

    override fun attack(target: LivingEntity) {
        if (isEnemyWithinAttackDistance(target)) {
            shouldCountTillNextAttack = true

            if (isTimeToStartAttackAnimation) {
                entity.isAttacking = true
            }

            if (isTimeToAttack) {
                entity.lookControl.lookAt(target.x, target.eyeY, target.z)
                performAttack(target)
            }
        } else {
            resetAttackCooldown()
            shouldCountTillNextAttack = false
            entity.isAttacking = false
            entity.attackAnimationTimeout = 0
        }
    }

    private fun isEnemyWithinAttackDistance(enemy: LivingEntity) = entity.distanceTo(enemy) <= 2F

    private fun resetAttackCooldown() {
        ticksUntilNextAttack = getTickCount(attackDelay * 2)
    }

    private val isTimeToStartAttackAnimation
        get() = ticksUntilNextAttack <= attackDelay

    private val isTimeToAttack
        get() = ticksUntilNextAttack <= 0

    private fun performAttack(enemy: LivingEntity) {
        resetAttackCooldown()
        entity.swingHand(Hand.MAIN_HAND)
        entity.tryAttack(enemy)
    }

    override fun tick() {
        super.tick()
        if (shouldCountTillNextAttack) {
            ticksUntilNextAttack = max(ticksUntilNextAttack - 1, 0)
        }
    }

    override fun stop() {
        entity.isAttacking = false
        super.stop()
    }
}