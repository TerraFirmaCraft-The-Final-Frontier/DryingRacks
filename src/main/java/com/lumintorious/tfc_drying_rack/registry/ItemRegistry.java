package com.lumintorious.tfc_drying_rack.registry;

import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

import com.lumintorious.tfc_drying_rack.TFCDryingRack;

@Mod.EventBusSubscriber(modid = TFCDryingRack.MODID)
@GameRegistry.ObjectHolder(TFCDryingRack.MODID)
public final class ItemRegistry
{
    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event)
    {
        IForgeRegistry<Item> r = event.getRegistry();
        BlockRegistry.getAllNormalItemBlocks().forEach(x -> registerItemBlock(r, x));
    }

    @SuppressWarnings("ConstantConditions")
    private static void registerItemBlock(IForgeRegistry<Item> r, ItemBlock item)
    {
        item.setRegistryName(item.getBlock().getRegistryName());
        item.setCreativeTab(item.getBlock().getCreativeTab());
        r.register(item);
    }
}