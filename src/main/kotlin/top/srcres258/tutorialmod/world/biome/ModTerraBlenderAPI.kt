package top.srcres258.tutorialmod.world.biome

import net.minecraft.util.Identifier
import terrablender.api.Regions
import terrablender.api.SurfaceRuleManager
import terrablender.api.TerraBlenderApi
import top.srcres258.tutorialmod.TutorialMod
import top.srcres258.tutorialmod.world.biome.surface.ModMaterialRules

object ModTerraBlenderAPI : TerraBlenderApi {
    override fun onTerraBlenderInitialized() {
        Regions.register(ModOverworldRegion(Identifier(TutorialMod.MOD_ID, "overworld"), 4))

        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, TutorialMod.MOD_ID,
            ModMaterialRules.makeRules())
    }
}