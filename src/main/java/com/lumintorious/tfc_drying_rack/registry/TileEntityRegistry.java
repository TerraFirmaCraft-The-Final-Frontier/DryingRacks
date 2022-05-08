package com.lumintorious.tfc_drying_rack.registry;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

import com.lumintorious.tfc_drying_rack.TFCDryingRack;
import com.lumintorious.tfc_drying_rack.objects.rack.TileEntityRack;

public class TileEntityRegistry
{
    public static void register()
    {
        GameRegistry.registerTileEntity(TileEntityRack.class, new ResourceLocation(TFCDryingRack.MODID, TileEntityRack.NAME));
    }
}