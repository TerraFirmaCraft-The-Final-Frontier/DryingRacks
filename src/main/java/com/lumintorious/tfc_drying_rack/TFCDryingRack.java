package com.lumintorious.tfc_drying_rack;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import com.lumintorious.tfc_drying_rack.recipes.DryingRackRecipeHandler;

@Mod(modid = TFCDryingRack.MODID, name = TFCDryingRack.NAME, version = TFCDryingRack.VERSION, dependencies = TFCDryingRack.DEPENDENCIES)
public class TFCDryingRack
{
    public static final String MODID = "tfc_drying_rack";
    public static final String NAME = "TFC:TFF Drying Racks";
    public static final String VERSION = "2.0.0";
    public static final String DEPENDENCIES = "required-after:tfc";

    @Instance
    public static TFCDryingRack instance;

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        DryingRackRecipeHandler.init();
    }
}