package top.srcres258.tutorialmod.item

import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.item.AxeItem
import net.minecraft.item.HoeItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroups
import net.minecraft.item.PickaxeItem
import net.minecraft.item.ShovelItem
import net.minecraft.item.SwordItem
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier
import top.srcres258.tutorialmod.TutorialMod
import top.srcres258.tutorialmod.item.custom.MetalDetectorItem

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