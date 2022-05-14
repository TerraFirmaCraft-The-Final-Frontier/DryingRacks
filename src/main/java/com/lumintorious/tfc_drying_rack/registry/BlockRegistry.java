package com.lumintorious.tfc_drying_rack.registry;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

import com.lumintorious.tfc_drying_rack.TFCDryingRack;
import com.lumintorious.tfc_drying_rack.objects.rack.BlockRack;
import com.lumintorious.tfc_drying_rack.objects.rack.BlockRackMetal;
import com.lumintorious.tfc_drying_rack.objects.rack.TileEntityRack;
import com.lumintorious.tfc_drying_rack.objects.rack.TileEntityRackMetal;
import net.dries007.tfc.api.registries.TFCRegistries;
import net.dries007.tfc.api.types.Metal;
import net.dries007.tfc.api.types.Tree;
import net.dries007.tfc.objects.CreativeTabsTFC;
import net.dries007.tfc.objects.items.itemblock.ItemBlockTFC;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = TFCDryingRack.MODID)
@GameRegistry.ObjectHolder(TFCDryingRack.MODID)
public final class BlockRegistry
{
    private static ImmutableList<ItemBlock> allNormalItemBlocks;
    private static ImmutableList<BlockRack> allDryingRackBlocks;
    private static ImmutableList<BlockRackMetal> allDryingRackMetalBlocks;

    public static ImmutableList<ItemBlock> getAllNormalItemBlocks()
    {
        return allNormalItemBlocks;
    }

    public static ImmutableList<BlockRack> getAllDryingRackBlocks()
    {
        return allDryingRackBlocks;
    }

    public static ImmutableList<BlockRackMetal> getAllDryingRackMetalBlocks()
    {
        return allDryingRackMetalBlocks;
    }

    @SubscribeEvent
    @SuppressWarnings("ConstantConditions")
    public static void registerBlocks(RegistryEvent.Register<Block> event)
    {
        IForgeRegistry<Block> r = event.getRegistry();

        Builder<ItemBlock> normalItemBlocks = ImmutableList.builder();
        Builder<BlockRack> dryingRacks = ImmutableList.builder();
        Builder<BlockRackMetal> dryingRacksMetal = ImmutableList.builder();

        for (Tree wood : TFCRegistries.TREES.getValuesCollection())
        {
            dryingRacks.add(register(r, wood.getRegistryName().getPath(), new BlockRack(wood), CreativeTabsTFC.CT_MISC));
        }

        for (Metal metal : TFCRegistries.METALS.getValuesCollection())
        {
            if (!metal.getRegistryName().getPath().contains("unknown"))
            {
                dryingRacksMetal.add(register(r, metal.getRegistryName().getPath(), new BlockRackMetal(metal), CreativeTabsTFC.CT_MISC));
            }
        }

        allDryingRackBlocks = dryingRacks.build();
        allDryingRackBlocks.forEach(x -> normalItemBlocks.add(new ItemBlockTFC(x)));

        allDryingRackMetalBlocks = dryingRacksMetal.build();
        allDryingRackMetalBlocks.forEach(x -> normalItemBlocks.add(new ItemBlockTFC(x)));

        allNormalItemBlocks = normalItemBlocks.build();

        register(TileEntityRack.class, "drying_rack");
        register(TileEntityRackMetal.class, "drying_rack_metal");
    }

    private static <T extends Block> T register(IForgeRegistry<Block> r, String name, T block, CreativeTabs ct)
    {
        block.setCreativeTab(ct);
        return register(r, name, block);
    }

    private static <T extends Block> T register(IForgeRegistry<Block> r, String name, T block)
    {
        block.setRegistryName(TFCDryingRack.MODID, name);
        block.setTranslationKey(TFCDryingRack.MODID + "." + name.replace('/', '.'));
        r.register(block);
        System.out.println("Block registered: " + name);
        return block;
    }

    private static <T extends TileEntity> void register(Class<T> te, String name)
    {
        TileEntity.register(TFCDryingRack.MODID + ":" + name, te);
        System.out.println("Tile Entity registered: " + name);
    }

    public BlockRegistry()
    {
    }
}