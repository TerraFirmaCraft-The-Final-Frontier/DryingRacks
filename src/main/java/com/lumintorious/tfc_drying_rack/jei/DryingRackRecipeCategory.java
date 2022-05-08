package com.lumintorious.tfc_drying_rack.jei;

import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import com.lumintorious.tfc_drying_rack.TFCDryingRack;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import net.dries007.tfc.compat.jei.BaseRecipeCategory;

@ParametersAreNonnullByDefault
public class DryingRackRecipeCategory extends BaseRecipeCategory<DryingRackRecipeWrapper>
{
    private static final ResourceLocation location = new ResourceLocation(TFCDryingRack.MODID, "textures/gui/drying_rack.png");
    private final IDrawableStatic background;

    public DryingRackRecipeCategory(IGuiHelper guiHelper, String uid)
    {
        super(guiHelper.createBlankDrawable(167, 18), uid);
        background = guiHelper.createDrawable(location, 0, 0, 167, 18);
    }

    @Override
    public void drawExtras(Minecraft minecraft)
    {
        background.draw(minecraft);
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, DryingRackRecipeWrapper recipeWrapper, IIngredients ingredients)
    {
        IGuiItemStackGroup isg = recipeLayout.getItemStacks();

        isg.init(0, true, 0, 0);
        isg.init(1, false, 149, 0);

        List<ItemStack> inputs = ingredients.getInputs(VanillaTypes.ITEM).get(0);
        List<ItemStack> outputs = ingredients.getOutputs(VanillaTypes.ITEM).get(0);

        isg.set(0, inputs.get(0));
        isg.set(1, outputs);
    }
}