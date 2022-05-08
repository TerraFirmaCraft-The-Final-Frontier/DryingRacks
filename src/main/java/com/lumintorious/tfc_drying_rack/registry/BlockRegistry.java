package com.lumintorious.tfc_drying_rack.registry;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import com.lumintorious.tfc_drying_rack.TFCDryingRack;
import com.lumintorious.tfc_drying_rack.objects.base.BlockBase;
import com.lumintorious.tfc_drying_rack.objects.rack.BlockRack;


@Mod.EventBusSubscriber(modid = TFCDryingRack.MODID)
public class BlockRegistry
{
    public static final List<Block> BLOCKS = new ArrayList<>();
    public static final List<Item> ITEM_BLOCKS = new ArrayList<>();

    public static BlockBase DRYING_RACK;

    public static BlockBase register(BlockBase block)
    {
        BLOCKS.add(block);
        ITEM_BLOCKS.add(block.getItemBlock());
        return block;
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event)
    {
        DRYING_RACK = register(new BlockRack("drying_rack"));
        event.getRegistry().registerAll(BLOCKS.toArray(new Block[0]));
    }

    //@SubscribeEvent
    //public static void registerItems(RegistryEvent.Register<Item> event)
    //{
    //    event.getRegistry().registerAll(ITEM_BLOCKS.toArray(new Item[0]));
    //}
}