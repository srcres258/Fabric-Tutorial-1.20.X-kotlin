package top.srcres258.tutorialmod.block.entity

import net.fabricmc.fabric.api.`object`.builder.v1.block.entity.FabricBlockEntityTypeBuilder
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier
import top.srcres258.tutorialmod.TutorialMod
import top.srcres258.tutorialmod.block.ModBlocks

object ModBlockEntities {
    val GEM_POLISHING_STATION_BLOCK_ENTITY: BlockEntityType<GemPolishingStationBlockEntity> =
        Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier(TutorialMod.MOD_ID, "gem_polishing_be"),
            FabricBlockEntityTypeBuilder.create(::GemPolishingStationBlockEntity, ModBlocks.GEM_POLISHING_STATION)
                .build())

    fun registerBlockEntities() {
        TutorialMod.LOGGER.info("Registering Block Entities for ${TutorialMod.MOD_ID}")
    }
}