package top.srcres258.tutorialmod.datagen

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.minecraft.data.server.tag.TagProvider
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.RegistryWrapper
import net.minecraft.registry.tag.PointOfInterestTypeTags
import net.minecraft.util.Identifier
import net.minecraft.world.poi.PointOfInterestType
import top.srcres258.tutorialmod.TutorialMod
import java.util.concurrent.CompletableFuture

class ModPoiTagProvider(
    output: FabricDataOutput,
    registryLookupFuture: CompletableFuture<RegistryWrapper.WrapperLookup>
) : TagProvider<PointOfInterestType>(output, RegistryKeys.POINT_OF_INTEREST_TYPE, registryLookupFuture) {
    override fun configure(lookup: RegistryWrapper.WrapperLookup?) {
        val goctb = ::getOrCreateTagBuilder

        goctb(PointOfInterestTypeTags.ACQUIRABLE_JOB_SITE)
            .addOptional(Identifier(TutorialMod.MOD_ID, "soundpoi"))
    }
}