package com.lumintorious.tfc_drying_rack.jei;

import net.minecraft.item.ItemStack;

import com.lumintorious.tfc_drying_rack.TFCDryingRack;
import com.lumintorious.tfc_drying_rack.recipes.DryingRackRecipe;
import com.lumintorious.tfc_drying_rack.recipes.DryingRackRecipeHandler;
import com.lumintorious.tfc_drying_rack.registry.BlockRegistry;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;

@mezz.jei.api.JEIPlugin
public class JEIPlugin implements IModPlugin
{
    public static final String rackID = TFCDryingRack.MODID + ".drying_rack";

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry)
    {
        IJeiHelpers helpers = registry.getJeiHelpers();
        IGuiHelper guiHelpers = helpers.getGuiHelper();
        registry.addRecipeCategories(new DryingRackRecipeCategory(guiHelpers, rackID));
    }

    @Override
    public void register(IModRegistry registry)
    {
        registerHandling(registry);
    }

    private void registerHandling(IModRegistry registry)
    {
        registry.handleRecipes(DryingRackRecipe.class, DryingRackRecipeWrapper::new, rackID);
        registry.addRecipes(DryingRackRecipeHandler.recipes, rackID);
        for (int i = 0; i < BlockRegistry.getAllDryingRackBlocks().size(); i++)
        {
            registry.addRecipeCatalyst(new ItemStack(BlockRegistry.getAllDryingRackBlocks().get(i)), rackID);
        }

        for (int i = 0; i < BlockRegistry.getAllDryingRackMetalBlocks().size(); i++)
        {
            registry.addRecipeCatalyst(new ItemStack(BlockRegistry.getAllDryingRackMetalBlocks().get(i)), rackID);
        }
    }
}