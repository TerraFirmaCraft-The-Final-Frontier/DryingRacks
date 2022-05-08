package com.lumintorious.tfc_drying_rack.objects.base;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;

import net.dries007.tfc.api.capability.size.Size;
import net.dries007.tfc.api.capability.size.Weight;
import net.dries007.tfc.objects.items.ItemTFC;

public class ItemBase extends ItemTFC
{
    public ItemBase(String registryName)
    {
        this.setRegistryName(registryName);
        this.setTranslationKey(registryName);
    }

    public void registerModel(String id)
    {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(this.getRegistryName(), id));
    }

    @Override
    public Size getSize(ItemStack arg0)
    {
        return Size.TINY;
    }

    @Override
    public Weight getWeight(ItemStack arg0)
    {
        return Weight.VERY_LIGHT;
    }
}