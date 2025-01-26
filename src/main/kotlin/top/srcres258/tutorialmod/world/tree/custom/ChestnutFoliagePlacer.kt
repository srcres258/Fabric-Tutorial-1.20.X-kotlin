package top.srcres258.tutorialmod.world.tree.custom

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.util.math.intprovider.IntProvider
import net.minecraft.util.math.random.Random
import net.minecraft.world.TestableWorld
import net.minecraft.world.gen.feature.TreeFeatureConfig
import net.minecraft.world.gen.foliage.FoliagePlacer
import net.minecraft.world.gen.foliage.FoliagePlacerType
import top.srcres258.tutorialmod.world.tree.ModFoliagePlacerTypes

class ChestnutFoliagePlacer(
    radius: IntProvider,
    offset: IntProvider,
    private val height: Int
) : FoliagePlacer(radius, offset) {
    companion object {
        val CODEC: Codec<ChestnutFoliagePlacer> = RecordCodecBuilder.create { instance ->
            fillFoliagePlacerFields(instance)
                .and(Codec.intRange(0, 12)
                    .fieldOf("height")
                    .forGetter { it.height })
                .apply(instance, ::ChestnutFoliagePlacer)
        }
    }

    override fun getType(): FoliagePlacerType<*> = ModFoliagePlacerTypes.CHESTNUT_FOLIAGE_PLACER

    override fun generate(
        world: TestableWorld,
        placer: BlockPlacer,
        random: Random,
        config: TreeFeatureConfig,
        trunkHeight: Int,
        treeNode: TreeNode,
        foliageHeight: Int,
        radius: Int,
        offset: Int
    ) {
        // generateSquare(world, placer, random, config, treeNode.getCenter());
        // radius on how many blocks it extends into x and z direction
        // y how much offset in the y direction from treeNode.getCenter()
        // y if it is dependent on i, also offsets each new layer in the y direction

        for (i in 0 .. height) {
            generateSquare(world, placer, random, config, treeNode.center.up(i), 2, 1, treeNode.isGiantTrunk)
        }
    }

    override fun getRandomHeight(random: Random?, trunkHeight: Int, config: TreeFeatureConfig?) = height

    override fun isInvalidForLeaves(
        random: Random?,
        dx: Int,
        y: Int,
        dz: Int,
        radius: Int,
        giantTrunk: Boolean
    ) = false
}