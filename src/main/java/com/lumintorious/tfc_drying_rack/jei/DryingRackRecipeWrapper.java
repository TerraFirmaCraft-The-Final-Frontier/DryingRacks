package com.lumintorious.tfc_drying_rack.jei;

import java.util.Collections;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;

import com.lumintorious.tfc_drying_rack.recipes.DryingRackRecipe;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.wrapper.ICraftingRecipeWrapper;

@ParametersAreNonnullByDefault
public class DryingRackRecipeWrapper implements ICraftingRecipeWrapper
{
    private final List<ItemStack> inputs;
    private final DryingRackRecipe original;
    private final ItemStack output;

    public DryingRackRecipeWrapper(DryingRackRecipe recipe)
    {
        this.inputs = recipe.input.getValidIngredients();
        this.output = recipe.output.copy();
        this.original = recipe;
    }

    @Override
    public void getIngredients(IIngredients ingredients)
    {
        ingredients.setInputLists(VanillaTypes.ITEM, Collections.singletonList(inputs));
        ingredients.setOutput(VanillaTypes.ITEM, output);
    }

    @Override
    public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY)
    {
        String text;
        text = (int) (this.original.chance * 100) + "%";

        float x = 122f;
        float y = 5f;
        minecraft.fontRenderer.drawString(text, x, y, 0x000000, false);

        String text2;
        text2 = I18n.format("jei.tooltips.tfc.barrel.duration.short", this.original.time);

        float x2 = 22f;
        float y2 = 5f;
        minecraft.fontRenderer.drawString(text2, x2, y2, 0x000000, false);
    }
}