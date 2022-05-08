package com.lumintorious.tfc_drying_rack.objects.rack;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import net.dries007.tfc.api.capability.size.IItemSize;
import net.dries007.tfc.api.capability.size.Size;
import net.dries007.tfc.api.capability.size.Weight;
import net.dries007.tfc.api.types.Tree;
import net.dries007.tfc.objects.blocks.BlocksTFC;
import net.dries007.tfc.objects.te.TEPlacedItemFlat;

public class BlockRack extends Block implements ITileEntityProvider, IItemSize
{
    private static final Map<Tree, BlockRack> MAP = new HashMap<>();

    public Tree wood;

    public BlockRack(Tree wood)
    {
        super(Material.WOOD, MapColor.AIR);
        if (MAP.put(wood, this) != null) throw new IllegalStateException("There can only be one.");
        this.wood = wood;
        setSoundType(SoundType.WOOD);
        setHarvestLevel("axe", 0);
        setHardness(0.5f);
        setResistance(3f);
    }

    @Nonnull
    @Override
    public Size getSize(@Nonnull ItemStack stack)
    {
        return Size.LARGE;
    }

    @Nonnull
    @Override
    public Weight getWeight(@Nonnull ItemStack stack)
    {
        return Weight.MEDIUM;
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (worldIn.isRemote)
        {
            return true;
        }
        BlockPos newPos = pos.add(0, 1, 0);
        if (worldIn.getTileEntity(newPos) instanceof TEPlacedItemFlat)
        {
            worldIn.setBlockState(newPos, Blocks.AIR.getDefaultState());
        }
        else
        {
            ItemStack inHand = playerIn.getHeldItem(EnumHand.MAIN_HAND);
            if (inHand.getCount() != 0)
            {
                ItemStack one = inHand.copy();
                int took = Math.min(inHand.getMaxStackSize() / 8, inHand.getCount());
                one.setCount(took);
                inHand.setCount(inHand.getCount() - took);

                worldIn.setBlockState(newPos, Blocks.AIR.getDefaultState());
                worldIn.setBlockState(newPos, BlocksTFC.PLACED_ITEM_FLAT.getDefaultState());
                ((TEPlacedItemFlat) worldIn.getTileEntity(newPos)).setStack(one);
            }
        }
        return true;
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean isSideSolid(IBlockState base_state, IBlockAccess world, BlockPos pos, EnumFacing side)
    {
        return side == EnumFacing.UP;
    }

    @Override
    public boolean hasTileEntity(IBlockState state)
    {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileEntityRack();
    }
}