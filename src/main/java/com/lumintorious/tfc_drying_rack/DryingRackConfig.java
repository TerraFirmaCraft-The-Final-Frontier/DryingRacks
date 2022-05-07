package com.lumintorious.tfc_drying_rack;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = TFCDryingRack.MODID, category = "")
@Mod.EventBusSubscriber(modid = TFCDryingRack.MODID)
@Config.LangKey("config." + TFCDryingRack.MODID)
@SuppressWarnings("WeakerAccess")
public class DryingRackConfig {
	
	    @Config.Comment("General settings")
	    @Config.LangKey("config." + TFCDryingRack.MODID + ".general")
	    public static final GeneralCFG GENERAL = new GeneralCFG();

	    @SubscribeEvent
	    public static void onConfigChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event)
	    {
	        if (event.getModID().equals(TFCDryingRack.MODID))
	        {
	            ConfigManager.sync(TFCDryingRack.MODID, Config.Type.INSTANCE);
	        }
	    }

	    public static class GeneralCFG
	    {
	    	 @Config.Comment("If true, you can dry leaves to get thatch.")
	         @Config.LangKey("config." + TFCDryingRack.MODID + ".general.thatchFromLeaves")
	         public boolean thatchFromLeaves = false;
	    	 
	    	 @Config.Comment("If true, you can dry saltpeter powder to get salt.")
	         @Config.LangKey("config." + TFCDryingRack.MODID + ".general.saltFromSaltPeter")
	         public boolean saltFromSaltPeter = false;
	    	
	    	 @Config.Comment("If true, recipes for drying vegetables to get seeds are added.")
	         @Config.LangKey("config." + TFCDryingRack.MODID + ".general.seedsFromVegetables")
	         public boolean seedsFromVegetables = false;
	    	 
	    	 @Config.Comment("If true, flowers are dried for their dye instead of crushed in the quern.")
	         @Config.LangKey("config." + TFCDryingRack.MODID + ".general.driedFlowers")
	         public boolean driedFlowers = false;
	    	 
	    	 @Config.Comment("If true, dyes MUST be used in water for them to work. They are dry dust, so you can't just throw it on the glass ;)")
	         @Config.LangKey("config." + TFCDryingRack.MODID + ".general.dyesNeedWater")
	         public boolean dyesNeedWater = true;
	    }
}
