package com.semleti.customrecipes;

import com.semleti.customrecipes.reference.Reference;
import com.semleti.customrecipes.utility.LogHelper;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

import java.util.List;

/**
 * Created by semleti on 30.12.2015.
 */
@Mod(modid = Reference.MOD_ID,name = Reference.MOD_NAME,version = Reference.VERSION,guiFactory = Reference.GUI_FACTORY_CLASS)

public class CustomRecipes {

    @Mod.EventHandler
    public void postInit(final FMLPostInitializationEvent fml) {

    }
}
