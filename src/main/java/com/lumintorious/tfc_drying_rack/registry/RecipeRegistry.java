package com.lumintorious.tfc_drying_rack.registry;

import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistryModifiable;

import com.lumintorious.tfc_drying_rack.DryingRackConfig;
import com.lumintorious.tfc_drying_rack.TFCDryingRack;
import net.dries007.tfc.api.recipes.barrel.BarrelRecipe;
import net.dries007.tfc.api.registries.TFCRegistries;
import net.dries007.tfc.objects.fluids.FluidsTFC;
import net.dries007.tfc.objects.inventory.ingredient.IIngredient;
import net.dries007.tfc.util.calendar.ICalendar;

@Mod.EventBusSubscriber(modid = TFCDryingRack.MODID)
public class RecipeRegistry
{
    @SubscribeEvent
    public static void onRecipeRegister(RegistryEvent.Register<IRecipe> event)
    {
        IForgeRegistryModifiable<IRecipe> modRegistry = (IForgeRegistryModifiable<IRecipe>) event.getRegistry();
        String[] colors = new String[] {"white", "light_gray", "gray", "dark_gray", "yellow", "lime", "green", "cyan", "blue", "light_blue", "purple", "magenta", "red", "orange", "brown", "black"};

        if (DryingRackConfig.GENERAL.dyesNeedWater) for (EnumDyeColor dyeColor : EnumDyeColor.values())
        {
            String color = dyeColor == EnumDyeColor.SILVER ? "light_gray" : dyeColor.getName();
            int dyeMeta = dyeColor.getMetadata();

            modRegistry.remove(new ResourceLocation("minecraft:" + color + "_wool"));
            modRegistry.remove(new ResourceLocation("minecraft:" + color + "_stained_hardened_clay"));
            modRegistry.remove(new ResourceLocation("minecraft:" + color + "_concrete_powder"));
            modRegistry.remove(new ResourceLocation("tfc:vanilla/concrete_powder/" + color + "_concrete_powder"));
            modRegistry.remove(new ResourceLocation("minecraft:" + color + "_stained_glass"));

            BarrelRecipe barrelrec = new BarrelRecipe(IIngredient.of(FluidsTFC.getFluidFromDye(dyeColor).get(), 125), IIngredient.of(Blocks.STAINED_GLASS), null, new ItemStack(Blocks.STAINED_GLASS, 1, dyeMeta), ICalendar.TICKS_IN_HOUR).setRegistryName("glass_" + color);
            BarrelRecipe barrelrec2 = new BarrelRecipe(IIngredient.of(FluidsTFC.getFluidFromDye(dyeColor).get(), 125), IIngredient.of(Blocks.STAINED_HARDENED_CLAY), null, new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, dyeMeta), ICalendar.TICKS_IN_HOUR).setRegistryName("stained_clay_alt_" + color);

            TFCRegistries.BARREL.register(barrelrec2);
            TFCRegistries.BARREL.register(barrelrec);
        }
    }
}