package com.mightzero.sophring;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Sophring implements ModInitializer,ClientModInitializer{
	public final static String MODID="sophring";
	public final static String TRINKETS_ID = "trinkets";
	public void log(String msg)
	{
		LOGGER.info(msg);
	}
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MODID);
	@Override
	public void onInitialize() {

		log("Welcome to Use Sophring Mod by MightZero");
		SophringItems.RegItems();
		log("Sophring Items Loaded");
		SophringEffects.RegEffects();
		log("Sophring Effects Loaded");
		log("Sophring Mod Loaded");
	}
	@Override
	public void onInitializeClient() {

	}
}