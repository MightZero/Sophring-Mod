package com.mightzero.sophring;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

public interface EquippedStackAccessor {
    default ItemStack getEquippedStack(EquipmentSlot slot) {
        return ((LivingEntity) this).getEquippedStack(slot);
    }
}