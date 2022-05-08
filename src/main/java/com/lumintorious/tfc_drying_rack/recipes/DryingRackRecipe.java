package com.lumintorious.tfc_drying_rack.recipes;

import net.minecraft.item.ItemStack;

import net.dries007.tfc.objects.inventory.ingredient.IIngredient;

public class DryingRackRecipe
{
    public final String registryName;
    public final IIngredient<ItemStack> input;
    public final ItemStack output;
    public final int time;
    public final float chance;

    public DryingRackRecipe(String registryName, IIngredient<ItemStack> input, ItemStack output, int time, float chance)
    {
        this.registryName = registryName;
        this.input = input;
        this.output = output;
        this.time = time;
        this.chance = chance;
    }

    public DryingRackRecipe(String registryName, IIngredient<ItemStack> input, ItemStack output, int time)
    {
        this(registryName, input, output, time, 1.0f);
    }

    public DryingRackRecipe(String registryName, IIngredient<ItemStack> input, ItemStack output)
    {
        this(registryName, input, output, 0, 1.0f);
    }
}