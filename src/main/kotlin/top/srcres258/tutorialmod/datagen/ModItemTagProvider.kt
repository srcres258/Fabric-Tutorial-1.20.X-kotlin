package top.srcres258.tutorialmod.datagen

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.registry.RegistryWrapper
import net.minecraft.registry.tag.ItemTags
import top.srcres258.tutorialmod.item.ModItems
import java.util.concurrent.CompletableFuture

class ModItemTagProvider(
    output: FabricDataOutput,
    completableFuture: CompletableFuture<RegistryWrapper.WrapperLookup>
) : FabricTagProvider.ItemTagProvider(output, completableFuture) {
    override fun configure(p0: RegistryWrapper.WrapperLookup?) {
        val goctb = ::getOrCreateTagBuilder

        goctb(ItemTags.TRIMMABLE_ARMOR)
            .add(ModItems.RUBY_HELMET, ModItems.RUBY_CHESTPLATE, ModItems.RUBY_LEGGINGS, ModItems.RUBY_BOOTS)

        goctb(ItemTags.MUSIC_DISCS)
            .add(ModItems.BAR_BRAWL_MUSIC_DISC)

        goctb(ItemTags.CREEPER_DROP_MUSIC_DISCS)
            .add(ModItems.BAR_BRAWL_MUSIC_DISC)
    }
}