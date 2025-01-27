package top.srcres258.tutorialmod.world.dimension

import net.minecraft.registry.Registerable
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.BlockTags
import net.minecraft.util.Identifier
import net.minecraft.util.math.intprovider.UniformIntProvider
import net.minecraft.world.World
import net.minecraft.world.dimension.DimensionOptions
import net.minecraft.world.dimension.DimensionType
import net.minecraft.world.dimension.DimensionTypes
import top.srcres258.tutorialmod.TutorialMod
import java.util.*

object ModDimensions {
    val KAUPENDIM_KEY: RegistryKey<DimensionOptions> = RegistryKey.of(RegistryKeys.DIMENSION,
        Identifier(TutorialMod.MOD_ID, "kaupendim"))
    val KAUPENDIM_LEVEL_KEY: RegistryKey<World> = RegistryKey.of(RegistryKeys.WORLD,
        Identifier(TutorialMod.MOD_ID, "kaupendim"))
    val KAUPENDIM_TYPE: RegistryKey<DimensionType> = RegistryKey.of(RegistryKeys.DIMENSION_TYPE,
        Identifier(TutorialMod.MOD_ID, "kaupendim_type"))

    fun bootstrapType(context: Registerable<DimensionType>) {
        context.register(
            KAUPENDIM_TYPE,
            DimensionType(
                OptionalLong.of(12000),
                false,
                false,
                false,
                true,
                1.0,
                true,
                false,
                0,
                256,
                256,
                BlockTags.INFINIBURN_OVERWORLD,
                DimensionTypes.OVERWORLD_ID,
                1F,
                DimensionType.MonsterSettings(
                    false,
                    false,
                    UniformIntProvider.create(0, 0),
                    0
                )
            )
        )
    }
}