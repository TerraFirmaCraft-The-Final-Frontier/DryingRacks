package com.lumintorious.tfc_drying_rack.recipes;

import java.util.HashSet;
import java.util.Set;

import net.minecraft.item.ItemStack;
import net.minecraftforge.registries.IForgeRegistryModifiable;

import com.lumintorious.tfc_drying_rack.DryingRackConfig;
import net.dries007.tfc.api.registries.TFCRegistries;
import net.dries007.tfc.objects.blocks.BlocksTFC;
import net.dries007.tfc.objects.inventory.ingredient.IIngredient;
import net.dries007.tfc.objects.items.ItemSeedsTFC;
import net.dries007.tfc.objects.items.ItemsTFC;
import net.dries007.tfc.util.agriculture.Crop;

public class DryingRackRecipeHandler
{
    public static final Set<DryingRackRecipe> recipes = new HashSet<>();

    public static void init()
    {
        if (DryingRackConfig.GENERAL.seedsFromVegetables) for (Crop crop : Crop.values())
        {
            try
            {
                IIngredient<ItemStack> input = IIngredient.of(crop.getFoodDrop(crop.getMaxStage()).getItem());
                ItemStack output = ItemSeedsTFC.get(crop, 1);
                int duration = 72;
                float chance = 0.15f;
                recipes.add(new DryingRackRecipe("tfc_drying_rack:" + crop + "_seed", input, output, duration, chance));
            }
            catch (Exception e)
            {

            }
        }

        if (DryingRackConfig.GENERAL.driedFlowers)
        {
            IForgeRegistryModifiable modRegistry = (IForgeRegistryModifiable) TFCRegistries.QUERN;
            TFCRegistries.QUERN.forEach((recipe) -> {
                IIngredient<ItemStack> input = recipe.getIngredients().get(0);
                ItemStack output = recipe.getOutputs().get(0);
                if (input.getValidIngredients().get(0).getItem().getRegistryName().toString().contains("plants/"))
                {
                    recipes.add(new DryingRackRecipe("tfc_drying_rack:dried_pre_" + recipe.getRegistryName(), input, output, 8, 1f));
                    modRegistry.remove(recipe.getRegistryName());
                }
            });
        }

        if (DryingRackConfig.GENERAL.thatchFromLeaves) recipes.add(new DryingRackRecipe("tfc_drying_rack:thatch_from_leaves", IIngredient.of("treeLeaves"), new ItemStack(BlocksTFC.THATCH), 92, 1.0f));

        if (DryingRackConfig.GENERAL.saltFromSaltPeter) recipes.add(new DryingRackRecipe("tfc_drying_rack:salt_from_saltpeter", IIngredient.of("dustSaltpeter"), new ItemStack(ItemsTFC.SALT), 24, 1.0f));
    }

    public static DryingRackRecipe getRecipe(ItemStack input)
    {
        for (DryingRackRecipe recipe : recipes)
        {
            if (recipe.input.testIgnoreCount(input))
            {
                return recipe;
            }
        }
        return null;
    }

    public static DryingRackRecipe getRecipe(IIngredient input)
    {
        for (DryingRackRecipe recipe : recipes)
        {
            if (recipe.input == input)
            {
                return recipe;
            }
        }
        return null;
    }

    public static DryingRackRecipe getRecipe(String registryName)
    {
        for (DryingRackRecipe recipe : recipes)
        {
            if (recipe.registryName.equals(registryName))
            {
                return recipe;
            }
        }
        return null;
    }

    public static void addRecipe(DryingRackRecipe recipe)
    {
        recipes.add(recipe);
    }
}