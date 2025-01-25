package top.srcres258.tutorialmod.item

import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.item.AliasedBlockItem
import net.minecraft.item.ArmorItem
import net.minecraft.item.AxeItem
import net.minecraft.item.HoeItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroups
import net.minecraft.item.MusicDiscItem
import net.minecraft.item.PickaxeItem
import net.minecraft.item.ShovelItem
import net.minecraft.item.SwordItem
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier
import top.srcres258.tutorialmod.TutorialMod
import top.srcres258.tutorialmod.block.ModBlocks
import top.srcres258.tutorialmod.item.custom.MetalDetectorItem
import top.srcres258.tutorialmod.item.custom.ModArmorItem
import top.srcres258.tutorialmod.sound.ModSounds

object ModItems {
    val RUBY: Item = registerItem("ruby", Item(FabricItemSettings()))
    val RAW_RUBY: Item = registerItem("raw_ruby", Item(FabricItemSettings()))

    val METAL_DETECTOR: Item = registerItem("metal_detector",
        MetalDetectorItem(FabricItemSettings().maxDamage(64)))

    val TOMATO: Item = registerItem("tomato", Item(FabricItemSettings().food(ModFoodComponents.TOMATO)))

    val COAL_BRIQUETTE: Item = registerItem("coal_briquette", Item(FabricItemSettings()))

    val RUBY_STAFF: Item = registerItem("ruby_staff", Item(FabricItemSettings().maxCount(1)))

    val RUBY_PICKAXE: Item = registerItem("ruby_pickaxe",
        PickaxeItem(ModToolMaterial.RUBY, 2, 2F, FabricItemSettings()))
    val RUBY_AXE: Item = registerItem("ruby_axe",
        AxeItem(ModToolMaterial.RUBY, 3F, 1F, FabricItemSettings()))
    val RUBY_SHOVEL: Item = registerItem("ruby_shovel",
        ShovelItem(ModToolMaterial.RUBY, 0F, 0F, FabricItemSettings()))
    val RUBY_SWORD: Item = registerItem("ruby_sword",
        SwordItem(ModToolMaterial.RUBY, 5, 3F, FabricItemSettings()))
    val RUBY_HOE: Item = registerItem("ruby_hoe",
        HoeItem(ModToolMaterial.RUBY, 0, 0F, FabricItemSettings()))

    val RUBY_HELMET: Item = registerItem("ruby_helmet",
        ModArmorItem(ModArmorMaterials.RUBY, ArmorItem.Type.HELMET, FabricItemSettings()))
    val RUBY_CHESTPLATE: Item = registerItem("ruby_chestplate",
        ArmorItem(ModArmorMaterials.RUBY, ArmorItem.Type.CHESTPLATE, FabricItemSettings()))
    val RUBY_LEGGINGS: Item = registerItem("ruby_leggings",
        ArmorItem(ModArmorMaterials.RUBY, ArmorItem.Type.LEGGINGS, FabricItemSettings()))
    val RUBY_BOOTS: Item = registerItem("ruby_boots",
        ArmorItem(ModArmorMaterials.RUBY, ArmorItem.Type.BOOTS, FabricItemSettings()))

    val TOMATO_SEEDS: Item = registerItem("tomato_seeds",
        AliasedBlockItem(ModBlocks.TOMATO_CROP, FabricItemSettings()))

    val CORN_SEEDS: Item = registerItem("corn_seeds",
        AliasedBlockItem(ModBlocks.CORN_CROP, FabricItemSettings()))
    val CORN: Item = registerItem("corn", Item(FabricItemSettings()))

    val BAR_BRAWL_MUSIC_DISC: Item = registerItem("bar_brawl_music_disc",
        MusicDiscItem(7, ModSounds.BAR_BRAWL, FabricItemSettings().maxCount(1), 122))

    private fun addItemsToIngredientItemGroup(entries: FabricItemGroupEntries) {
        entries.add(RUBY)
        entries.add(RAW_RUBY)
    }

    fun registerItem(name: String, item: Item): Item =
        Registry.register(Registries.ITEM, Identifier(TutorialMod.MOD_ID, name), item)

    fun registerModItems() {
        TutorialMod.LOGGER.info("Registering Mod Items for ${TutorialMod.MOD_ID}")

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(::addItemsToIngredientItemGroup)
    }
}