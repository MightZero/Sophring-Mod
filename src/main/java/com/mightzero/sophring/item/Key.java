package com.mightzero.sophring.item;

import com.mightzero.sophring.model.KeyRender;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRendererRegistrationCallback;
import net.minecraft.client.render.entity.ArmorStandEntityRenderer;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.Equipment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Key extends Item implements Equipment {
    public Key(Settings settings)
    {
        super(settings);
        LivingEntityFeatureRendererRegistrationCallback.EVENT.register((entityType, entityRenderer, registrationHelper, context) -> {
            if (entityRenderer instanceof ArmorStandEntityRenderer || entityRenderer instanceof PlayerEntityRenderer) {
                registrationHelper.register(new KeyRender<>(entityRenderer));
            }
        });
    }
    public boolean isDamageable() {
        return true;
    }
    @Override
    public boolean damage(DamageSource source){return false;}
    @Override
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }
    @Override
    public EquipmentSlot getSlotType() {
        return EquipmentSlot.MAINHAND;
    }
}
