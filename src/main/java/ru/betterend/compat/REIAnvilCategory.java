package ru.betterend.compat;

import java.util.Collections;
import java.util.List;

import org.jetbrains.annotations.NotNull;

import com.google.common.collect.Lists;

import it.unimi.dsi.fastutil.ints.IntList;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.EntryStack;
import me.shedaniel.rei.api.TransferRecipeCategory;
import me.shedaniel.rei.api.widgets.Widgets;
import me.shedaniel.rei.gui.entries.RecipeEntry;
import me.shedaniel.rei.gui.entries.SimpleRecipeEntry;
import me.shedaniel.rei.gui.widget.Widget;
import net.minecraft.block.Blocks;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import ru.betterend.util.LangUtil;

public class REIAnvilCategory implements TransferRecipeCategory<REIAnvilDisplay> {

	@Override
	public @NotNull Identifier getIdentifier() {
		return REIPlugin.SMITHING;
	}

	@Override
	public @NotNull String getCategoryName() {
		return LangUtil.translate(Blocks.ANVIL.getTranslationKey());
	}
	
	@Override
	public @NotNull EntryStack getLogo() {
		return REIPlugin.ANVIL;
	}
	
	@Override
	public @NotNull List<Widget> setupDisplay(REIAnvilDisplay display, Rectangle bounds) {
		Point startPoint = new Point(bounds.getCenterX() - 41, bounds.y + 10);
		List<Widget> widgets = Lists.newArrayList();
		widgets.add(Widgets.createRecipeBase(bounds));
		int x = startPoint.x + 10;
		int y = startPoint.y;
		widgets.add(Widgets.createResultSlotBackground(new Point(x + 61, y + 9)));
		List<List<EntryStack>> inputEntries = display.getInputEntries();
		widgets.add(Widgets.createArrow(new Point(x + 24, y + 8)));
		widgets.add(Widgets.createSlot(new Point(x - 20, y + 8)).entries(inputEntries.get(0)).markInput());
		widgets.add(Widgets.createSlot(new Point(x + 1, y + 8)).entries(inputEntries.get(1)).markInput());
		widgets.add(Widgets.createSlot(new Point(x + 61, y + 9)).entries(display.getResultingEntries().get(0)).disableBackground().markOutput());
		return widgets;
	}

	@Override
	public void renderRedSlots(MatrixStack matrices, List<Widget> widgets, Rectangle bounds, REIAnvilDisplay display,
			IntList redSlots) {
		Point startPoint = new Point(bounds.getCenterX() - 41, bounds.getCenterY() - 27);
		matrices.push();
		matrices.translate(0, 0, 400);
		if (redSlots.contains(0)) {
			DrawableHelper.fill(matrices, startPoint.x - 20, startPoint.y + 8, startPoint.x - 20 + 16, startPoint.y + 8 + 16, 1090453504);
			DrawableHelper.fill(matrices, startPoint.x + 1, startPoint.y + 8, startPoint.x + 1 + 16, startPoint.y + 8 + 16, 1090453504);
		}
		matrices.pop();
	}
	
	@Override
	public @NotNull RecipeEntry getSimpleRenderer(REIAnvilDisplay recipe) {
		return SimpleRecipeEntry.from(Collections.singletonList(recipe.getInputEntries().get(0)), recipe.getResultingEntries());
	}
	
	@Override
	public int getDisplayHeight() {
		return 49;
	}

}
