package com.lumintorious.tfc_drying_rack;

import java.io.IOException;

import net.minecraftforge.client.resource.VanillaResourceType;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

import com.lumintorious.tfc_drying_rack.recipes.DryingRackRecipeHandler;
import com.lumintorious.tfc_drying_rack.registry.DataGenerator;

@Mod(modid = TFCDryingRack.MODID, name = TFCDryingRack.NAME, version = TFCDryingRack.VERSION, dependencies = TFCDryingRack.DEPENDENCIES)
public class TFCDryingRack
{
    public static final String MODID = "tfc_drying_rack";
    public static final String NAME = "TFC:TFF Drying Racks";
    public static final String VERSION = "2.1.0";
    public static final String DEPENDENCIES = "required-after:tfc;required-after:resourceloader";

    @EventHandler
    public void init(FMLInitializationEvent event) throws IOException
    {
        if (FMLCommonHandler.instance().getSide() == Side.CLIENT)
        {
            DataGenerator.genForgeBlockStates(MODID);
            FMLClientHandler.instance().refreshResources(VanillaResourceType.MODELS);

            DataGenerator.genLangFile(MODID);
            FMLClientHandler.instance().refreshResources(VanillaResourceType.LANGUAGES);
        }

        DryingRackRecipeHandler.init();
    }
}