package com.lumintorious.tfc_drying_rack.registry;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.stream.JsonWriter;
import org.apache.commons.lang3.text.WordUtils;
import net.minecraft.launchwrapper.Launch;

import net.dries007.tfc.api.registries.TFCRegistries;
import net.dries007.tfc.api.types.Metal;
import net.dries007.tfc.api.types.Tree;

public class DataGenerator
{
    public static File rootFolder = Launch.minecraftHome == null ? new File(".") : Launch.minecraftHome;

    public static void genForgeBlockStates(String modid) throws IOException
    {
        File folders = new File(rootFolder + "/resources/" + modid + "/blockstates/");
        folders.mkdirs();

        for (Tree wood : TFCRegistries.TREES.getValuesCollection())
        {
            File json = new File(rootFolder + "/resources/" + modid + "/blockstates/" + wood + ".json");

            JsonWriter jsonWriter = new JsonWriter(new FileWriter(json));
            jsonWriter.setIndent("    ");
            jsonWriter.setLenient(false);
            jsonWriter.beginObject();
            jsonWriter.name("forge_marker").value(1);
            jsonWriter.name("defaults").beginObject();
            jsonWriter.name("model").value("tfc_drying_rack:drying_rack");
            jsonWriter.name("textures").beginObject();
            jsonWriter.name("particle").value("tfc:blocks/wood/log/" + wood);
            jsonWriter.name("propeller_block").value("tfc:blocks/wood/log/" + wood);
            jsonWriter.name("top").value("tfc:blocks/wood/planks/" + wood);
            jsonWriter.endObject();
            jsonWriter.endObject();
            jsonWriter.name("variants").beginObject();
            jsonWriter.name("normal").beginArray().beginObject().endObject().endArray();
            jsonWriter.name("inventory").beginArray().beginObject().endObject().endArray();
            jsonWriter.endObject();
            jsonWriter.endObject();
            jsonWriter.close();
        }

        for (Metal metal : TFCRegistries.METALS.getValuesCollection())
        {
            File json = new File(rootFolder + "/resources/" + modid + "/blockstates/" + metal + ".json");

            JsonWriter jsonWriter = new JsonWriter(new FileWriter(json));
            jsonWriter.setIndent("    ");
            jsonWriter.setLenient(false);
            jsonWriter.beginObject();
            jsonWriter.name("forge_marker").value(1);
            jsonWriter.name("defaults").beginObject();
            jsonWriter.name("model").value("tfc_drying_rack:drying_rack_metal");
            jsonWriter.name("textures").beginObject();
            jsonWriter.name("0").value("tfc:blocks/metal/" + metal);
            jsonWriter.name("1").value("tfc:blocks/metal/wrought_iron");
            jsonWriter.name("2").value("minecraft:blocks/glass");
            jsonWriter.name("particle").value("tfc:blocks/metal/" + metal);
            jsonWriter.endObject();
            jsonWriter.endObject();
            jsonWriter.name("variants").beginObject();
            jsonWriter.name("normal").beginArray().beginObject().endObject().endArray();
            jsonWriter.name("inventory").beginArray().beginObject().endObject().endArray();
            jsonWriter.endObject();
            jsonWriter.endObject();
            jsonWriter.close();
        }
    }

    public static void genLangFile(String modid) throws IOException
    {
        File folders = new File(rootFolder + "/resources/" + modid + "/lang/");
        folders.mkdirs();

        File lang = new File(rootFolder + "/resources/" + modid + "/lang/" + "en_us.lang");

        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(lang));
        fileWriter.write("# JEI");
        fileWriter.newLine();
        fileWriter.write("jei.category.tfc_drying_rack.drying_rack=Drying");
        fileWriter.newLine();
        fileWriter.write("# WOODEN DRYING RACKS");
        for (Tree wood : TFCRegistries.TREES.getValuesCollection())
        {
            String woodKey = WordUtils.capitalize(wood.getRegistryName().getPath().replace("_", " "));
            fileWriter.newLine();
            fileWriter.write("tile.tfc_drying_rack." + wood + ".name=" + woodKey + " Drying Rack");
        }

        fileWriter.newLine();
        fileWriter.write("# METAL DRYING RACKS");
        for (Metal metal : TFCRegistries.METALS.getValuesCollection())
        {
            String metalKey = WordUtils.capitalize(metal.getRegistryName().getPath().replace("_", " "));
            fileWriter.newLine();
            fileWriter.write("tile.tfc_drying_rack." + metal + ".name=" + metalKey + " Drying Rack");
        }
        fileWriter.close();
    }
}