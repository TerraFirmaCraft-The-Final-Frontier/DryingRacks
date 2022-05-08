package com.lumintorious.tfc_drying_rack;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import com.lumintorious.tfc_drying_rack.proxy.CommonProxy;
import com.lumintorious.tfc_drying_rack.recipes.DryingRackRecipeHandler;
import com.lumintorious.tfc_drying_rack.registry.TileEntityRegistry;

@Mod(modid = TFCDryingRack.MODID, name = TFCDryingRack.NAME, version = TFCDryingRack.VERSION, dependencies = TFCDryingRack.DEPENDENCIES)
public class TFCDryingRack
{
    public static final String MODID = "tfc_drying_rack";
    public static final String NAME = "TFC:TFF Drying Racks";
    public static final String VERSION = "2.0.0";
    public static final String DEPENDENCIES = "required-after:tfc";

    @SidedProxy(clientSide = "com.lumintorious.tfc_drying_rack.proxy.ClientProxy", serverSide = "com.lumintorious.tfc_drying_rack.proxy.CommonProxy")
    public static CommonProxy proxy;

    @Instance
    public static TFCDryingRack instance;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        TileEntityRegistry.register();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        DryingRackRecipeHandler.init();
    }
}