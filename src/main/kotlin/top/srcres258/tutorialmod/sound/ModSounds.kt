package top.srcres258.tutorialmod.sound

import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.sound.SoundEvent
import net.minecraft.util.Identifier
import top.srcres258.tutorialmod.TutorialMod

object ModSounds {
    val METAL_DETECTOR_FOUND_ORE: SoundEvent = registerSoundEvent("metal_detector_found_ore")

    val SOUND_BLOCK_BREAK: SoundEvent = registerSoundEvent("sound_block_break")
    val SOUND_BLOCK_STEP: SoundEvent = registerSoundEvent("sound_block_step")
    val SOUND_BLOCK_PLACE: SoundEvent = registerSoundEvent("sound_block_place")
    val SOUND_BLOCK_HIT: SoundEvent = registerSoundEvent("sound_block_hit")
    val SOUND_BLOCK_FALL: SoundEvent = registerSoundEvent("sound_block_fall")

    val SOUND_BLOCK_SOUNDS = BlockSoundGroup(1F, 1F,
        SOUND_BLOCK_BREAK, SOUND_BLOCK_STEP, SOUND_BLOCK_PLACE, SOUND_BLOCK_HIT, SOUND_BLOCK_FALL)

    private fun registerSoundEvent(name: String) =
        Identifier(TutorialMod.MOD_ID, name).let { id ->
            Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id))
        }

    fun registerSounds() {
        TutorialMod.LOGGER.info("Registering Sounds for ${TutorialMod.MOD_ID}")
    }
}