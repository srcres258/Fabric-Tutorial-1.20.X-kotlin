package top.srcres258.tutorialmod.compat

import me.shedaniel.math.Point
import me.shedaniel.rei.api.client.gui.Renderer
import me.shedaniel.rei.api.client.gui.widgets.Widget
import me.shedaniel.rei.api.client.registry.display.DisplayCategory
import me.shedaniel.rei.api.common.category.CategoryIdentifier
import me.shedaniel.rei.api.common.display.basic.BasicDisplay
import me.shedaniel.rei.api.common.util.EntryStacks
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import top.srcres258.tutorialmod.TutorialMod
import top.srcres258.tutorialmod.block.ModBlocks
import me.shedaniel.math.Rectangle
import me.shedaniel.rei.api.client.gui.widgets.Widgets

class GemPolishingCategory : DisplayCategory<BasicDisplay> {
    companion object {
        val TEXTURE = Identifier(TutorialMod.MOD_ID, "textures/gui/gem_polishing_station_gui.png")
        val GEM_POLISHING: CategoryIdentifier<GemPolishingDisplay> =
            CategoryIdentifier.of(TutorialMod.MOD_ID, "gem_polishing")
    }

    override fun getCategoryIdentifier(): CategoryIdentifier<out BasicDisplay> = GEM_POLISHING

    override fun getTitle(): Text = Text.literal("Gem Polishing Station")

    override fun getIcon(): Renderer = EntryStacks.of(ModBlocks.GEM_POLISHING_STATION.asItem().defaultStack)

    override fun setupDisplay(display: BasicDisplay, bounds: Rectangle): MutableList<Widget> =
        mutableListOf<Widget>().also { widgets ->
            val startPoint = Point(bounds.centerX - 87, bounds.centerY - 35)

            widgets.add(Widgets.createTexturedWidget(TEXTURE, Rectangle(startPoint.x, startPoint.y, 175, 82)))
            widgets.add(Widgets.createSlot(Point(startPoint.x + 80, startPoint.y + 11))
                .entries(display.inputEntries[0]))
            widgets.add(Widgets.createSlot(Point(startPoint.x + 80, startPoint.y + 59))
                .markOutput()
                .entries(display.outputEntries[0]))
        }

    override fun getDisplayHeight(): Int = 90
}