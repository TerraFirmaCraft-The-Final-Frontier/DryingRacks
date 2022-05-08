package com.lumintorious.tfc_drying_rack.objects.rack;

import javax.annotation.Nonnull;

import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

import com.lumintorious.tfc_drying_rack.recipes.DryingRackRecipe;
import com.lumintorious.tfc_drying_rack.recipes.DryingRackRecipeHandler;
import net.dries007.tfc.objects.blocks.BlocksTFC;
import net.dries007.tfc.objects.te.TEInventory;
import net.dries007.tfc.objects.te.TEPlacedItemFlat;
import net.dries007.tfc.util.calendar.CalendarTFC;

public class TileEntityRack extends TEInventory implements ITickable
{
    public static final int ID = 1;
    public static final String NAME = "drying_rack";
    private ItemStack last = new ItemStack(Items.AIR);
    private DryingRackRecipe recipe = null;
    private long dueDate = -1;

    public TileEntityRack()
    {
        super(12);
    }

    @Override
    public ITextComponent getDisplayName()
    {
        return new TextComponentString(I18n.format(NAME));
    }

    @Nonnull
    @Override
    public NBTTagCompound getTileData()
    {
        NBTTagCompound nbt = new NBTTagCompound();
        this.writeToNBT(nbt);
        return nbt;
    }

    @Override
    public void update()
    {
        if (!world.isRemote)
        {
            long currentHours = CalendarTFC.CALENDAR_TIME.getTotalHours();
            BlockPos newPos = pos.add(0, 1, 0);
            TileEntity te = world.getTileEntity(newPos);
            if (te instanceof TEPlacedItemFlat)
            {
                TEPlacedItemFlat tile = (TEPlacedItemFlat) te;

                if (this.recipe != null && dueDate != -1 && currentHours >= dueDate)
                { //if its over due date
                    tile.setStack(new ItemStack(Items.AIR));
                    world.setBlockState(newPos, Blocks.AIR.getDefaultState());
                    ItemStack newStack = ItemStack.EMPTY;
                    if (Math.random() <= this.recipe.chance)
                    {
                        world.setBlockState(newPos, BlocksTFC.PLACED_ITEM_FLAT.getDefaultState());
                        newStack = this.recipe.output.copy();
                        newStack.setCount(Math.min(newStack.getCount() * last.getCount(), 64));
                        ((TEPlacedItemFlat) world.getTileEntity(newPos)).setStack(newStack);
                    }

                    this.last = newStack;
                    this.recipe = null;
                    this.dueDate = -1;

                }
                else
                {
                    ItemStack stack = tile.getStack();
                    if (last != stack)
                    {
                        last = stack;
                        DryingRackRecipe recipe = DryingRackRecipeHandler.getRecipe(stack);
                        if (recipe != null)
                        {
                            this.recipe = recipe;
                            this.dueDate = currentHours + (long) recipe.time;
                        }
                    }
                }
            }
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        this.last = new ItemStack(nbt.getCompoundTag("last"));
        this.dueDate = nbt.getLong("dueDate");
        String recipeName = nbt.getString("recipe");
        this.recipe = recipeName.equals("") ? null : DryingRackRecipeHandler.getRecipe(recipeName);
        super.readFromNBT(nbt);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt)
    {
        NBTTagCompound last = new NBTTagCompound();
        this.last.writeToNBT(last);
        nbt.setTag("last", last);
        nbt.setLong("dueDate", this.dueDate);
        nbt.setString("recipe", this.recipe == null ? "" : this.recipe.registryName);
        return super.writeToNBT(nbt);
    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket()
    {
        NBTTagCompound nbt = new NBTTagCompound();
        this.writeToNBT(nbt);
        int metadata = getBlockMetadata();
        return new SPacketUpdateTileEntity(this.pos, metadata, nbt);
    }

    @Override
    public NBTTagCompound getUpdateTag()
    {
        NBTTagCompound nbt = new NBTTagCompound();
        this.writeToNBT(nbt);
        return nbt;
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt)
    {
        this.readFromNBT(pkt.getNbtCompound());
    }

    @Override
    public void handleUpdateTag(NBTTagCompound tag)
    {
        this.readFromNBT(tag);
    }
}