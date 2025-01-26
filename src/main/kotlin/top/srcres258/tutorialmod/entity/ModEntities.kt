package top.srcres258.tutorialmod.entity

import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricEntityTypeBuilder
import net.minecraft.entity.EntityDimensions
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnGroup
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier
import top.srcres258.tutorialmod.TutorialMod
import top.srcres258.tutorialmod.entity.custom.DiceProjectileEntity
import top.srcres258.tutorialmod.entity.custom.PorcupineEntity

object ModEntities {
    val PORCUPINE: EntityType<PorcupineEntity> = Registry.register(Registries.ENTITY_TYPE,
        Identifier(TutorialMod.MOD_ID, "porcupine"),
        FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, ::PorcupineEntity)
            .dimensions(EntityDimensions.fixed(1F, 1F))
            .build())

    val DICE_PROJECTILE: EntityType<DiceProjectileEntity> = Registry.register(Registries.ENTITY_TYPE,
        Identifier(TutorialMod.MOD_ID, "dice_projectile"),
        FabricEntityTypeBuilder.create(SpawnGroup.MISC, ::DiceProjectileEntity)
            .dimensions(EntityDimensions.fixed(0.25F, 0.25F))
            .build())
}