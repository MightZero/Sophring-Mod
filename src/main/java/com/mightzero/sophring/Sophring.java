package com.mightzero.sophring;

import com.mightzero.sophring.item.BellRender;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRendererRegistrationCallback;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.entity.ArmorStandEntityRenderer;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Arm;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import com.mightzero.sophring.item.Bell;
import com.mightzero.sophring.item.Vigour;

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
	public static final Item Vigour= new Vigour(new FabricItemSettings().rarity(Rarity.RARE));

	@Override
	public void onInitialize() {
		log("Welcome to Use Sophring Mod by MightZero");
		Registry.register(Registries.ITEM, new Identifier("sophring", "bell"), Bell);
		Registry.register(Registries.ITEM, new Identifier("sophring", "vigour"), Vigour);

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(content -> {
			content.addAfter(Items.TOTEM_OF_UNDYING, Bell);
		});

		AttackEntityCallback.EVENT.register((player, world, hand, entity, isHit) -> {
			if (entity instanceof LivingEntity && ! (entity instanceof PlayerEntity)) {
				if (!player.isSpectator() && !entity.getCommandTags().contains("Vigour_Fallen")&& ((LivingEntity)entity).getHealth()<=0.4*((LivingEntity)entity).getMaxHealth() ) {
					if(Math.random() < 0.5f)
					entity.dropItem(Vigour);
					entity.addCommandTag("Vigour_Fallen");
				}
			}
			return ActionResult.PASS;
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