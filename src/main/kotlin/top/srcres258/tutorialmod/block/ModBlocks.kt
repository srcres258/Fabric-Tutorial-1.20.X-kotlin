package top.srcres258.tutorialmod.block

import com.terraformersmc.terraform.sign.block.TerraformHangingSignBlock
import com.terraformersmc.terraform.sign.block.TerraformSignBlock
import com.terraformersmc.terraform.sign.block.TerraformWallHangingSignBlock
import com.terraformersmc.terraform.sign.block.TerraformWallSignBlock
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.*
import net.minecraft.data.family.BlockFamilies
import net.minecraft.data.family.BlockFamily
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.item.BlockItem
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.util.Identifier
import net.minecraft.util.math.intprovider.UniformIntProvider
import top.srcres258.tutorialmod.TutorialMod
import top.srcres258.tutorialmod.block.custom.*
import top.srcres258.tutorialmod.sound.ModSounds

object ModBlocks {
    val RUBY_BLOCK: Block = registerBlock("ruby_block",
        Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).sounds(BlockSoundGroup.AMETHYST_BLOCK)))
    val RAW_RUBY_BLOCK: Block = registerBlock("raw_ruby_block",
        Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).sounds(BlockSoundGroup.AMETHYST_BLOCK)))

    val RUBY_ORE: Block = registerBlock("ruby_ore",
        ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.STONE).strength(2F),
            UniformIntProvider.create(2, 5)))
    val DEEPSLATE_RUBY_ORE: Block = registerBlock("deepslate_ruby_ore",
        ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.DEEPSLATE).strength(4F),
            UniformIntProvider.create(2, 5)))
    val NETHER_RUBY_ORE: Block = registerBlock("nether_ruby_ore",
        ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.NETHERRACK).strength(1.5F),
            UniformIntProvider.create(2, 5)))
    val END_STONE_RUBY_ORE: Block = registerBlock("end_stone_ruby_ore",
        ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.END_STONE).strength(4F),
            UniformIntProvider.create(4, 7)))

    val SOUND_BLOCK: Block = registerBlock("sound_block",
        SoundBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).sounds(ModSounds.SOUND_BLOCK_SOUNDS)))

    val RUBY_STAIRS: Block = registerBlock("ruby_stairs",
        StairsBlock(RUBY_BLOCK.defaultState, FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)))
    val RUBY_SLAB: Block = registerBlock("ruby_slab",
        SlabBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)))

    val RUBY_BUTTON: Block = registerBlock("ruby_button",
        ButtonBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK),
            BlockSetType.IRON, 10, true))
    val RUBY_PRESSURE_PLATE: Block = registerBlock("ruby_pressure_plate",
        PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING,
            FabricBlockSettings.copyOf(Blocks.IRON_BLOCK),
            BlockSetType.IRON))

    val RUBY_FENCE: Block = registerBlock("ruby_fence",
        FenceBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)))
    val RUBY_FENCE_GATE: Block = registerBlock("ruby_fence_gate",
        FenceGateBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK),
                WoodType.ACACIA))
    val RUBY_WALL: Block = registerBlock("ruby_wall",
        WallBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)))

    val RUBY_DOOR: Block = registerBlock("ruby_door",
        DoorBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).nonOpaque(),
            BlockSetType.IRON))
    val RUBY_TRAPDOOR: Block = registerBlock("ruby_trapdoor",
        TrapdoorBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).nonOpaque(),
            BlockSetType.IRON))

    val TOMATO_CROP: Block = Registry.register(Registries.BLOCK, Identifier(TutorialMod.MOD_ID, "tomato_crop"),
        TomatoCropBlock(FabricBlockSettings.copyOf(Blocks.WHEAT)))

    val CORN_CROP: Block = Registry.register(Registries.BLOCK, Identifier(TutorialMod.MOD_ID, "corn_crop"),
        CornCropBlock(FabricBlockSettings.copyOf(Blocks.WHEAT)))

    val DAHLIA: Block = registerBlock("dahlia",
        FlowerBlock(StatusEffects.FIRE_RESISTANCE, 10,
            FabricBlockSettings.copyOf(Blocks.ALLIUM).nonOpaque().noCollision()))
    val POTTED_DAHLIA: Block = Registry.register(Registries.BLOCK, Identifier(TutorialMod.MOD_ID, "potted_dahlia"),
        FlowerPotBlock(DAHLIA, FabricBlockSettings.copyOf(Blocks.POTTED_ALLIUM).nonOpaque()))

    val GEM_POLISHING_STATION: Block = registerBlock("gem_polishing_station",
        GemPolishingStationBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).nonOpaque()))

    val CHESTNUT_LOG: Block = registerBlock("chestnut_log",
        PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_LOG).strength(4F)))
    val CHESTNUT_WOOD: Block = registerBlock("chestnut_wood",
        PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_WOOD).strength(4F)))
    val STRIPPED_CHESTNUT_LOG: Block = registerBlock("stripped_chestnut_log",
        PillarBlock(FabricBlockSettings.copyOf(Blocks.STRIPPED_OAK_LOG).strength(4F)))
    val STRIPPED_CHESTNUT_WOOD: Block = registerBlock("stripped_chestnut_wood",
        PillarBlock(FabricBlockSettings.copyOf(Blocks.STRIPPED_OAK_WOOD).strength(4F)))

    val CHESTNUT_PLANKS: Block = registerBlock("chestnut_planks",
        Block(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS).strength(4F)))
    val CHESTNUT_LEAVES: Block = registerBlock("chestnut_leaves",
        LeavesBlock(FabricBlockSettings.copyOf(Blocks.OAK_LEAVES).strength(4F).nonOpaque()))

    val CHESTNUT_SIGN_TEXTURE = Identifier(TutorialMod.MOD_ID, "entity/signs/chestnut")
    val CHESTNUT_HANGING_SIGN_TEXTURE = Identifier(TutorialMod.MOD_ID, "entity/signs/hanging/chestnut")
    val CHESTNUT_HANGING_GUI_SIGN_TEXTURE = Identifier(TutorialMod.MOD_ID, "textures/gui/hanging_signs/chestnut")

    val STANDING_CHESTNUT_SIGN: Block = Registry.register(Registries.BLOCK,
        Identifier(TutorialMod.MOD_ID, "chestnut_standing_sign"),
        TerraformSignBlock(CHESTNUT_SIGN_TEXTURE, FabricBlockSettings.copyOf(Blocks.OAK_SIGN)))
    val WALL_CHESTNUT_SIGN: Block = Registry.register(Registries.BLOCK,
        Identifier(TutorialMod.MOD_ID, "chestnut_wall_sign"),
        TerraformWallSignBlock(CHESTNUT_SIGN_TEXTURE, FabricBlockSettings.copyOf(Blocks.OAK_WALL_SIGN)))
    val HANGING_CHESTNUT_SIGN: Block = Registry.register(Registries.BLOCK,
        Identifier(TutorialMod.MOD_ID, "chestnut_hanging_sign"),
        TerraformHangingSignBlock(CHESTNUT_HANGING_SIGN_TEXTURE, CHESTNUT_HANGING_GUI_SIGN_TEXTURE,
            FabricBlockSettings.copyOf(Blocks.OAK_HANGING_SIGN)))
    val WALL_HANGING_CHESTNUT_SIGN: Block = Registry.register(Registries.BLOCK,
        Identifier(TutorialMod.MOD_ID, "chestnut_wall_hanging_sign"),
        TerraformWallHangingSignBlock(CHESTNUT_HANGING_SIGN_TEXTURE, CHESTNUT_HANGING_GUI_SIGN_TEXTURE,
            FabricBlockSettings.copyOf(Blocks.OAK_WALL_HANGING_SIGN)))

    val CHESTNUT_FAMILY: BlockFamily = BlockFamilies.register(CHESTNUT_PLANKS)
        .sign(STANDING_CHESTNUT_SIGN, WALL_CHESTNUT_SIGN)
        .group("wooden")
        .unlockCriterionName("has_planks")
        .build()

    val DICE_BLOCK: Block = Registry.register(Registries.BLOCK, Identifier(TutorialMod.MOD_ID, "dice_block"),
        DiceBlock(FabricBlockSettings.copyOf(Blocks.STONE)))

    private fun registerBlock(name: String, block: Block) =
        Registry.register(
            Registries.BLOCK,
            Identifier(TutorialMod.MOD_ID, name),
            block.also { registerBlockItem(name, it) }
        )

    private fun registerBlockItem(name: String, block: Block) =
        Registry.register(
            Registries.ITEM,
            Identifier(TutorialMod.MOD_ID, name),
            BlockItem(block, FabricItemSettings())
        )

    fun registerModBlocks() {
        TutorialMod.LOGGER.info("Registering ModBlocks for ${TutorialMod.MOD_ID}")
    }
}