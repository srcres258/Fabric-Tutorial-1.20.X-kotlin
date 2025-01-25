package top.srcres258.tutorialmod.util

import net.fabricmc.fabric.api.`object`.builder.v1.trade.TradeOfferHelper
import net.minecraft.enchantment.EnchantmentLevelEntry
import net.minecraft.enchantment.Enchantments
import net.minecraft.item.EnchantedBookItem
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.village.TradeOffer
import net.minecraft.village.TradeOffers
import net.minecraft.village.VillagerProfession
import top.srcres258.tutorialmod.block.ModBlocks
import top.srcres258.tutorialmod.item.ModItems
import top.srcres258.tutorialmod.villager.ModVillagers
import java.util.function.Consumer

object ModCustomTrades {
    fun registerCustomTrades() {
        val rvo: (VillagerProfession, Int, Consumer<MutableList<TradeOffers.Factory>>) -> Unit =
            TradeOfferHelper::registerVillagerOffers
        val rwto = TradeOfferHelper::registerWanderingTraderOffers

        rvo(VillagerProfession.FARMER, 1) { factories ->
            factories.add { entity, random ->
                TradeOffer(
                    ItemStack(Items.EMERALD, 2),
                    ItemStack(ModItems.TOMATO, 7),
                    6, 5, 0.05F
                )
            }
            factories.add { entity, random ->
                TradeOffer(
                    ItemStack(Items.EMERALD, 7),
                    ItemStack(ModItems.TOMATO_SEEDS, 1),
                    2, 7, 0.075F
                )
            }
        }
        rvo(VillagerProfession.FARMER, 2) { factories ->
            factories.add { entity, random ->
                TradeOffer(
                    ItemStack(Items.GOLD_INGOT, 16),
                    ItemStack(Items.DIAMOND, 12),
                    ItemStack(ModItems.CORN_SEEDS, 1),
                    2, 7, 0.075F
                )
            }
        }

        rvo(VillagerProfession.LIBRARIAN, 1) { factories ->
            factories.add { entity, random ->
                TradeOffer(
                    ItemStack(ModItems.RUBY, 32),
                    EnchantedBookItem.forEnchantment(EnchantmentLevelEntry(Enchantments.PIERCING, 2)),
                    3, 12, 0.075F
                )
            }
        }

        rvo(ModVillagers.SOUND_MASTER, 1) { factories ->
            factories.add { entity, random ->
                TradeOffer(
                    ItemStack(ModItems.CORN, 32),
                    ItemStack(ModBlocks.SOUND_BLOCK, 2),
                    6, 12, 0.075F
                )
            }
        }
        rvo(ModVillagers.SOUND_MASTER, 2) { factories ->
            factories.add { entity, random ->
                TradeOffer(
                    ItemStack(ModItems.RUBY_SWORD, 1),
                    ItemStack(ModItems.RUBY_HELMET, 1),
                    2, 12, 0.075F
                )
            }
        }

        rwto(1) { factories ->
            factories.add { entity, random ->
                TradeOffer(
                    ItemStack(ModItems.RAW_RUBY, 16),
                    ItemStack(ModItems.METAL_DETECTOR, 1),
                    1, 12, 0.075F
                )
            }
        }

        rwto(2) { factories ->
            factories.add { entity, random ->
                TradeOffer(
                    ItemStack(ModItems.RAW_RUBY, 1),
                    ItemStack(ModItems.COAL_BRIQUETTE, 1),
                    1, 12, 0.075F
                )
            }
        }
    }
}