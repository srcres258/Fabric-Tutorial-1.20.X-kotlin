package top.srcres258.tutorialmod.item

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import top.srcres258.tutorialmod.TutorialMod
import top.srcres258.tutorialmod.block.ModBlocks

object ModItemGroups {
    val RUBY_GROUP: ItemGroup = Registry.register(
        Registries.ITEM_GROUP,
        Identifier(TutorialMod.MOD_ID, "ruby"),
        FabricItemGroup.builder()
            .displayName(Text.translatable("itemgroup.ruby"))
            .icon { ItemStack(ModItems.RUBY) }
            .entries { _, entries ->
                entries.run {
                    // items
                    run {
                        // mod
                        ModItems.run {
                            add(RUBY)
                            add(RAW_RUBY)

                            add(METAL_DETECTOR)

                            add(TOMATO)

                            add(COAL_BRIQUETTE)

                            add(RUBY_STAFF)

                            add(RUBY_PICKAXE)
                            add(RUBY_AXE)
                            add(RUBY_SHOVEL)
                            add(RUBY_SWORD)
                            add(RUBY_HOE)

                            add(RUBY_HELMET)
                            add(RUBY_CHESTPLATE)
                            add(RUBY_LEGGINGS)
                            add(RUBY_BOOTS)

                            add(TOMATO_SEEDS)

                            add(CORN_SEEDS)
                            add(CORN)

                            add(BAR_BRAWL_MUSIC_DISC)

                            add(PORCUPINE_SPAWN_EGG)

                            add(CHESTNUT_SIGN)
                            add(HANGING_CHESTNUT_SIGN)
                        }

                        // vanilla
                        add(Items.DIAMOND)
                    }

                    // blocks
                    run {
                        // mod
                        ModBlocks.run {
                            add(RUBY_BLOCK)
                            add(RAW_RUBY_BLOCK)

                            add(RUBY_ORE)
                            add(DEEPSLATE_RUBY_ORE)
                            add(NETHER_RUBY_ORE)
                            add(END_STONE_RUBY_ORE)

                            add(SOUND_BLOCK)

                            add(RUBY_STAIRS)
                            add(RUBY_SLAB)

                            add(RUBY_BUTTON)
                            add(RUBY_PRESSURE_PLATE)

                            add(RUBY_FENCE)
                            add(RUBY_FENCE_GATE)
                            add(RUBY_WALL)

                            add(RUBY_DOOR)
                            add(RUBY_TRAPDOOR)

                            add(DAHLIA)

                            add(GEM_POLISHING_STATION)

                            add(CHESTNUT_LOG)
                            add(CHESTNUT_WOOD)
                            add(STRIPPED_CHESTNUT_LOG)
                            add(STRIPPED_CHESTNUT_WOOD)

                            add(CHESTNUT_PLANKS)
                            add(CHESTNUT_LEAVES)
                        }
                    }
                }
            }
            .build()
    )

    fun registerItemGroups() {
        TutorialMod.LOGGER.info("Registering Item Groups for ${TutorialMod.MOD_ID}")
    }
}