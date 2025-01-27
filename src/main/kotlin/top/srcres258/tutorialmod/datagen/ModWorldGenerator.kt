package top.srcres258.tutorialmod.datagen

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.RegistryWrapper
import java.util.concurrent.CompletableFuture

class ModWorldGenerator(
    output: FabricDataOutput,
    registriesFuture: CompletableFuture<RegistryWrapper.WrapperLookup>
) : FabricDynamicRegistryProvider(output, registriesFuture) {
    override fun getName(): String = "World Gen"

    override fun configure(registries: RegistryWrapper.WrapperLookup, entries: Entries) {
        arrayOf(
            RegistryKeys.CONFIGURED_FEATURE,
            RegistryKeys.PLACED_FEATURE,
            RegistryKeys.BIOME,
            RegistryKeys.DIMENSION_TYPE
        ).forEach { entries.addAll(registries.getWrapperOrThrow(it)) }
    }
}