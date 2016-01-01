package com.semleti.customrecipes.client.gui;

import com.semleti.customrecipes.reference.Reference;
import com.semleti.customrecipes.utility.LogHelper;
import jline.internal.Log;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.common.brewing.VanillaBrewingRecipe;
import net.minecraftforge.fml.client.config.*;
import net.minecraftforge.fml.common.registry.GameData;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by semleti on 21.12.2015.
 */
public class ModGUIConfig extends GuiConfig {

    public ModGUIConfig(GuiScreen parent){

        super(parent, getConfigElements(), Reference.MOD_NAME, false, false, Reference.MOD_NAME);
        titleLine2 = "NAME: " + Reference.MOD_NAME;
        //super(parent, getConfigElements(), Reference.MOD_ID, false, false, Reference.MOD_NAME);
    }


    private static void getRecipes()
    {


        LogHelper.info(Item.itemRegistry.getKeys().toArray()[0].getClass());
        Object[] items = Item.itemRegistry.getKeys().toArray();
        LogHelper.info("##########################");
        LogHelper.info("starting to list items: " + items.length);
        for (int itemIndex = 0; itemIndex < items.length; itemIndex++) {
            //LogHelper.info(items[itemIndex] + " " + items[itemIndex].getClass());
            LogHelper.info(getObjInfo(items[itemIndex]));
        }

        LogHelper.info("##########################");
        LogHelper.info("starting to list smelting recipes:");
        Object[] smeltingRecipes = FurnaceRecipes.instance().getSmeltingList().values().toArray();
        Object[] smeltingKeys = FurnaceRecipes.instance().getSmeltingList().keySet().toArray();
        for (int recipeIndex = 0; recipeIndex < smeltingRecipes.length; recipeIndex++) {
            //LogHelper.info(((ItemStack)smeltingRecipes[recipeIndex]) + " " + smeltingRecipes[recipeIndex]);
            if (smeltingKeys[recipeIndex] instanceof ItemStack)
                LogHelper.info(getObjInfo(smeltingKeys[recipeIndex]) + " -> " + getObjInfo(smeltingRecipes[recipeIndex]) + " " +
                        FurnaceRecipes.instance().getSmeltingExperience((ItemStack) smeltingRecipes[recipeIndex]) + "exp");
            else
                LogHelper.info(getObjInfo(smeltingKeys[recipeIndex]) + " -> " + getObjInfo(smeltingRecipes[recipeIndex]));
        }

        LogHelper.info("##########################");
        LogHelper.info("starting to list brewing recipes:");
        Object[] brewingRecipes = BrewingRecipeRegistry.getRecipes().toArray();
        for (int recipeIndex = 0; recipeIndex < brewingRecipes.length; recipeIndex++) {
            //LogHelper.info(brewingRecipes[recipeIndex].getClass() + " " + brewingRecipes[recipeIndex]);
            if (brewingRecipes[recipeIndex] instanceof VanillaBrewingRecipe) {
                LogHelper.info(((VanillaBrewingRecipe) brewingRecipes[recipeIndex]).toString());
            } else
                LogHelper.error("NOT SUPORTED TYPE OF BREWING RECIPE: " + brewingRecipes[recipeIndex].getClass() + " " + brewingRecipes[recipeIndex]);
        }


        LogHelper.info("##########################");
        LogHelper.info("starting to list crafting recipes:");
        List<IRecipe> listR = CraftingManager.getInstance().getRecipeList();
        LogHelper.info("Number of recipes: " + listR.size());
        if (listR == null) {
            LogHelper.fatal("OH DAMN!");
        }
        for (int recipeIndex = 0; recipeIndex < 1000 && recipeIndex < listR.size() - 1; recipeIndex++) {
            if (listR.get(recipeIndex) == null || listR.get(recipeIndex).getRecipeOutput() == null || listR.get(recipeIndex).getRecipeOutput().getItem() == null || listR.get(recipeIndex).getRecipeOutput().getItem().getUnlocalizedName() == null) {
                LogHelper.fatal("NULL");
                continue;
            }

            listR.get(recipeIndex);
            listR.get(recipeIndex).getRecipeOutput();
            listR.get(recipeIndex).getRecipeOutput().getDisplayName();
            listR.get(recipeIndex).getRecipeOutput().getItem();
            listR.get(recipeIndex).getRecipeOutput().getItem().getUnlocalizedName();

            LogHelper.info("-------------------------------------");
            LogHelper.info(recipeIndex + "# " + listR.get(recipeIndex).getRecipeOutput().stackSize + " " + listR.get(recipeIndex).getRecipeOutput().getDisplayName() + " " + listR.get(recipeIndex).getRecipeOutput().getUnlocalizedName());
            LogHelper.info(listR.get(recipeIndex).getRecipeSize());

            if (listR.get(recipeIndex) instanceof ShapedRecipes) {
                LogHelper.info("Shaped Recipe");
                for (int ingredientIndex = 0; ingredientIndex < ((ShapedRecipes) listR.get(recipeIndex)).recipeItems.length; ingredientIndex++) {
                    LogHelper.info(getObjInfo(((ShapedRecipes) listR.get(recipeIndex)).recipeItems[ingredientIndex]));
                /*if (((ShapedRecipes) listR.get(recipeIndex)).recipeItems[ingredientIndex] == null)
                    LogHelper.info("no item");
                else {
                    LogHelper.info(((ShapedRecipes) listR.get(recipeIndex)).recipeItems[ingredientIndex].getDisplayName() + " " + ((ShapedRecipes) listR.get(recipeIndex)).recipeItems[ingredientIndex].getUnlocalizedName());
                }*/
                }
            } else if (listR.get(recipeIndex) instanceof ShapedOreRecipe) {
                LogHelper.info("Shaped Ore Recipe");
                LogHelper.info(getObjInfo(((ShapedOreRecipe) listR.get(recipeIndex)).getInput()));

            } else if (listR.get(recipeIndex) instanceof ShapelessRecipes) {
                LogHelper.info("Shapeless Recipe");
                LogHelper.info(getObjInfo(((ShapelessRecipes) listR.get(recipeIndex)).recipeItems));
            } else if (listR.get(recipeIndex) instanceof ShapelessOreRecipe) {
                LogHelper.info("Shapeless Ore Recipe");
                LogHelper.info(getObjInfo(((ShapelessOreRecipe) listR.get(recipeIndex)).getInput()));
            } else {
                LogHelper.error("NOT SUPORTED TYPE OF RECIPE: " + listR.get(recipeIndex).getClass() + " " + listR.get(recipeIndex));
            }

        }

    }


    private static List<IConfigElement> getConfigElements()
    {

        getRecipes();



        List<IConfigElement> list = new ArrayList<IConfigElement>();
        List<IConfigElement> listsList = new ArrayList<IConfigElement>();
        List<IConfigElement> stringsList = new ArrayList<IConfigElement>();
        List<IConfigElement> numbersList = new ArrayList<IConfigElement>();
        List<IConfigElement> craftingRecipiesList = new ArrayList<IConfigElement>();
        Pattern commaDelimitedPattern = Pattern.compile("([A-Za-z]+((,){1}( )*|$))+?");

        // Top Level Settings
        list.add(new DummyConfigElement("imABoolean", true, ConfigGuiType.BOOLEAN, "fml.config.sample.imABoolean").setRequiresMcRestart(true));
        list.add(new DummyConfigElement("imAnInteger", 42, ConfigGuiType.INTEGER, "fml.config.sample.imAnInteger", -1, 256).setRequiresMcRestart(true));
        list.add(new DummyConfigElement("imADouble", 42.4242D, ConfigGuiType.DOUBLE, "fml.config.sample.imADouble", -1.0D, 256.256D).setRequiresMcRestart(true));
        list.add(new DummyConfigElement("imAString", "http://www.montypython.net/scripts/string.php", ConfigGuiType.STRING, "fml.config.sample.imAString").setRequiresMcRestart(true));

        // Lists category
        listsList.add(new DummyConfigElement.DummyListElement("booleanList", new Boolean[] {true, false, true, false, true, false, true, false}, ConfigGuiType.BOOLEAN, "fml.config.sample.booleanList"));
        listsList.add(new DummyConfigElement.DummyListElement("booleanListFixed", new Boolean[] {true, false, true, false, true, false, true, false}, ConfigGuiType.BOOLEAN, "fml.config.sample.booleanListFixed", true));
        listsList.add(new DummyConfigElement.DummyListElement("booleanListMax", new Boolean[] {true, false, true, false, true, false, true, false}, ConfigGuiType.BOOLEAN, "fml.config.sample.booleanListMax", 10));
        listsList.add(new DummyConfigElement.DummyListElement("doubleList", new Double[] {0.0D, 1.1D, 2.2D, 3.3D, 4.4D, 5.5D, 6.6D, 7.7D, 8.8D, 9.9D}, ConfigGuiType.DOUBLE, "fml.config.sample.doubleList"));
        listsList.add(new DummyConfigElement.DummyListElement("doubleListFixed", new Double[] {0.0D, 1.1D, 2.2D, 3.3D, 4.4D, 5.5D, 6.6D, 7.7D, 8.8D, 9.9D}, ConfigGuiType.DOUBLE, "fml.config.sample.doubleListFixed", true));
        listsList.add(new DummyConfigElement.DummyListElement("doubleListMax", new Double[] {0.0D, 1.1D, 2.2D, 3.3D, 4.4D, 5.5D, 6.6D, 7.7D, 8.8D, 9.9D}, ConfigGuiType.DOUBLE, "fml.config.sample.doubleListMax", 15));
        listsList.add(new DummyConfigElement.DummyListElement("doubleListBounded", new Double[] {0.0D, 1.1D, 2.2D, 3.3D, 4.4D, 5.5D, 6.6D, 7.7D, 8.8D, 9.9D}, ConfigGuiType.DOUBLE, "fml.config.sample.doubleListBounded", -1.0D, 10.0D));
        listsList.add(new DummyConfigElement.DummyListElement("integerList", new Integer[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9}, ConfigGuiType.INTEGER, "fml.config.sample.integerList"));
        listsList.add(new DummyConfigElement.DummyListElement("integerListFixed", new Integer[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9}, ConfigGuiType.INTEGER, "fml.config.sample.integerListFixed", true));
        listsList.add(new DummyConfigElement.DummyListElement("integerListMax", new Integer[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9}, ConfigGuiType.INTEGER, "fml.config.sample.integerListMax", 15));
        listsList.add(new DummyConfigElement.DummyListElement("integerListBounded", new Integer[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9}, ConfigGuiType.INTEGER, "fml.config.sample.integerListBounded", -1, 10));
        listsList.add(new DummyConfigElement.DummyListElement("stringList", new String[] {"An", "items", "of", "string", "values"}, ConfigGuiType.STRING, "fml.config.sample.stringList"));
        listsList.add(new DummyConfigElement.DummyListElement("stringListFixed", new String[] {"A", "fixed", "length", "items", "of", "string", "values"}, ConfigGuiType.STRING, "fml.config.sample.stringListFixed", true));
        listsList.add(new DummyConfigElement.DummyListElement("stringListMax", new String[] {"An", "items", "of", "string", "values", "with", "a", "max", "length", "of", "15"}, ConfigGuiType.STRING, "fml.config.sample.stringListMax", 15));
        listsList.add(new DummyConfigElement.DummyListElement("stringListPattern", new String[] {"Valid", "Not Valid", "Is, Valid", "Comma, Separated, Value"}, ConfigGuiType.STRING, "fml.config.sample.stringListPattern", commaDelimitedPattern));

        list.add(new DummyConfigElement.DummyCategoryElement("lists", "fml.config.sample.ctgy.lists", listsList));

        // Strings category
        stringsList.add(new DummyConfigElement("basicString", "Just a regular String value, anything goes.", ConfigGuiType.STRING, "fml.config.sample.basicString"));
        stringsList.add(new DummyConfigElement("cycleString", "this", ConfigGuiType.STRING, "fml.config.sample.cycleString", new String[] {"this", "property", "cycles", "through", "a", "list", "of", "valid", "choices"}));
        stringsList.add(new DummyConfigElement("patternString", "only, comma, separated, words, can, be, entered, in, this, box", ConfigGuiType.STRING, "fml.config.sample.patternString", commaDelimitedPattern));
        stringsList.add(new DummyConfigElement("chatColorPicker", "c", ConfigGuiType.COLOR, "fml.config.sample.chatColorPicker", new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"}));
        stringsList.add(new DummyConfigElement("modIDSelector", "FML", ConfigGuiType.MOD_ID, "fml.config.sample.modIDSelector"));

        list.add(new DummyConfigElement.DummyCategoryElement("strings", "fml.config.sample.ctgy.strings", stringsList));

        // Numbers category
        numbersList.add((new DummyConfigElement("basicInteger", 42, ConfigGuiType.INTEGER, "fml.config.sample.basicInteger")));
        numbersList.add((new DummyConfigElement("boundedInteger", 42, ConfigGuiType.INTEGER, "fml.config.sample.boundedInteger", -1, 256)));
        numbersList.add((new DummyConfigElement("sliderInteger", 2000, ConfigGuiType.INTEGER, "fml.config.sample.sliderInteger", 100, 10000)).setCustomListEntryClass(GuiConfigEntries.NumberSliderEntry.class));
        numbersList.add(new DummyConfigElement("basicDouble", 42.4242D, ConfigGuiType.DOUBLE, "fml.config.sample.basicDouble"));
        numbersList.add(new DummyConfigElement("boundedDouble", 42.4242D, ConfigGuiType.DOUBLE, "fml.config.sample.boundedDouble", -1.0D, 256.256D));
        numbersList.add(new DummyConfigElement("sliderDouble", 42.4242D, ConfigGuiType.DOUBLE, "fml.config.sample.sliderDouble", -1.0D, 256.256D).setCustomListEntryClass(GuiConfigEntries.NumberSliderEntry.class));

        list.add(new DummyConfigElement.DummyCategoryElement("numbers", "fml.config.sample.ctgy.numbers", numbersList));

        //Crafting recipes category
        craftingRecipiesList.add((new DummyConfigElement("recipe 1",0,ConfigGuiType.BOOLEAN,"customrecipes.config.firstrecipe").setConfigEntryClass(CraftingConfigGuiType.class)));

        list.add(new DummyConfigElement.DummyCategoryElement("recipies","customrecipes.config.recipescategory",craftingRecipiesList));

        return list;
    }


    static public String getObjInfo(Object obj)
    {
        if(obj == null)
            return "no item";
        else if(obj instanceof ItemStack) {
            return "ITEMSTACK: " + ((ItemStack) obj).getDisplayName() + " " + ((ItemStack) obj).getUnlocalizedName();
        }
        else if(obj instanceof Item)
        {
            return "ITEM: " + ((Item)obj).getUnlocalizedName() + " " + ((Item)obj).getUnlocalizedName();
        }
        else if(obj instanceof ResourceLocation)
        {
            return "Resourcelocation: "+obj;
        }
        else if(obj instanceof java.util.List) {
            String str = "{";
            for (int subIngredientIndex = 0; subIngredientIndex < ((List) obj).size(); subIngredientIndex++) {
                str += getObjInfo(((List) obj).get(subIngredientIndex)) + ",";
            }
            return str.substring(0,str.length()-1) + "}";
        }
        else if(obj instanceof Object[]) {
            String str = "[";
            for(int subIngredientIndex = 0; subIngredientIndex < ((Object[])obj).length; subIngredientIndex++)
            {
                str += getObjInfo(((Object[])obj)[subIngredientIndex]) +",";
            }
            return str.substring(0,str.length()-1) + "]";
        }
        else
            return "NOT SUPORTED TYPE OF ITEM: " + obj.getClass() +" " + obj;
    }

}
