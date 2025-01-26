package top.srcres258.tutorialmod.entity

import com.terraformersmc.terraform.boat.api.TerraformBoatType
import com.terraformersmc.terraform.boat.api.TerraformBoatTypeRegistry
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.util.Identifier
import top.srcres258.tutorialmod.TutorialMod
import top.srcres258.tutorialmod.block.ModBlocks
import top.srcres258.tutorialmod.item.ModItems

object ModBoats {
    val CHESTNUT_BOAT_ID = Identifier(TutorialMod.MOD_ID, "chestnut_boat")
    val CHESTNUT_CHEST_BOAT_ID = Identifier(TutorialMod.MOD_ID, "chestnut_chest_boat")

    val CHESTNUT_BOAT_KEY: RegistryKey<TerraformBoatType> = TerraformBoatTypeRegistry.createKey(CHESTNUT_BOAT_ID)

    fun registerBoats() {
        val chestnutBoat = TerraformBoatType.Builder()
            .item(ModItems.CHESTNUT_BOAT)
            .chestItem(ModItems.CHESTNUT_CHEST_BOAT)
            .planks(ModBlocks.CHESTNUT_PLANKS.asItem())
            .build()

        Registry.register(TerraformBoatTypeRegistry.INSTANCE, CHESTNUT_BOAT_KEY, chestnutBoat)
    }
}