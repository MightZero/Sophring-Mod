package com.mightzero.sophring;

import com.mightzero.sophring.item.BellRender;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRendererRegistrationCallback;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.ArmorStandEntityRenderer;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import com.mightzero.sophring.item.Bell;

public class Sophring implements ModInitializer ,ClientModInitializer{
	public void log(String msg)
	{
		LOGGER.info(msg);
	}
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("sophring");
	public static final Item Bell= new Bell(new FabricItemSettings().maxDamage(101).rarity(Rarity.EPIC));
	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		log("Welcome to Use Sophring Mod by MightZero");
		Registry.register(Registries.ITEM, new Identifier("sophring", "bell"), Bell);
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(content -> {
			content.addAfter(Items.TOTEM_OF_UNDYING, Bell);
		});
		LivingEntityFeatureRendererRegistrationCallback.EVENT.register((entityType, entityRenderer, registrationHelper, context) -> {
			if (entityRenderer instanceof ArmorStandEntityRenderer || entityRenderer instanceof PlayerEntityRenderer) {
				registrationHelper.register(new BellRender<>(entityRenderer));
			}
		});
		log("Sophring Mod is Loaded");
	}

	@Override
	public void onInitializeClient() {

	}
}