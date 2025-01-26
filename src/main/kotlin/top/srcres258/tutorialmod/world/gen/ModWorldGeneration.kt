package top.srcres258.tutorialmod.world.gen

object ModWorldGeneration {
    fun generateModWorldGen() {
        ModOreGeneration.generateOres()
        ModTreeGeneration.generateTrees()
    }
}