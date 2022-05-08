package com.lumintorious.tfc_drying_rack.ct;

import net.minecraft.item.ItemStack;

import com.lumintorious.tfc_drying_rack.recipes.DryingRackRecipe;
import com.lumintorious.tfc_drying_rack.recipes.DryingRackRecipeHandler;
import crafttweaker.annotations.ZenRegister;
import net.dries007.tfc.compat.crafttweaker.CTHelper;
import net.dries007.tfc.objects.inventory.ingredient.IIngredient;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.tfcdryingrack.Rack")
@ZenRegister
public class CTDryingRack
{
    @SuppressWarnings("unchecked")
    @ZenMethod
    public static void addRecipe(String registryName, crafttweaker.api.item.IIngredient itemInput, crafttweaker.api.item.IItemStack output, int hours, float chance)
    {
        if (registryName == null)
        {
            throw new IllegalArgumentException("The registryName argument cannot be null.");
        }
        IIngredient inputIngredient = CTHelper.getInternalIngredient(itemInput);
        ItemStack outputStack = (ItemStack) output.getInternal();
        DryingRackRecipe recipe = new DryingRackRecipe(registryName, inputIngredient, outputStack, hours, chance);
        DryingRackRecipeHandler.addRecipe(recipe);
    }

    @ZenMethod
    public static void addRecipe(String registryName, crafttweaker.api.item.IIngredient itemInput, crafttweaker.api.item.IItemStack output, int hours)
    {
        addRecipe(registryName, itemInput, output, hours, 1.0f);
    }

    @ZenMethod
    public static void addRecipe(String registryName, crafttweaker.api.item.IIngredient itemInput, crafttweaker.api.item.IItemStack output)
    {
        addRecipe(registryName, itemInput, output, 0);
    }

    @ZenMethod
    public static void removeRecipe(String registryName)
    {
        DryingRackRecipe recipe = DryingRackRecipeHandler.getRecipe(registryName);
        DryingRackRecipeHandler.recipes.remove(recipe);
    }

    @ZenMethod
    public static void removeRecipe(crafttweaker.api.item.IIngredient input)
    {
        DryingRackRecipe recipe = DryingRackRecipeHandler.getRecipe(CTHelper.getInternalIngredient(input));
        DryingRackRecipeHandler.recipes.remove(recipe);
    }
}