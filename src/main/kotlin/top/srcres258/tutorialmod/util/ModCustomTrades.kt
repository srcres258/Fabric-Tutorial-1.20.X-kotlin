package top.srcres258.tutorialmod.util

import net.fabricmc.fabric.api.`object`.builder.v1.trade.TradeOfferHelper
import net.minecraft.enchantment.EnchantmentLevelEntry
import net.minecraft.enchantment.Enchantments
import net.minecraft.item.EnchantedBookItem
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.village.TradeOffer
import net.minecraft.village.VillagerProfession
import top.srcres258.tutorialmod.item.ModItems

object ModCustomTrades {
    fun registerCustomTrades() {
        val rvo = TradeOfferHelper::registerVillagerOffers
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