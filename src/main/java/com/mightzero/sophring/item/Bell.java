package com.mightzero.sophring.item;

import net.minecraft.block.DispenserBlock;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.List;

import com.mightzero.sophring.Sophring;
public class Bell extends Item implements Equipment {
    public Bell(Settings settings) {
        super(settings);
        DispenserBlock.registerBehavior(this, ArmorItem.DISPENSER_BEHAVIOR);
    }
    @Override
    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        return ingredient.isOf(Sophring.Vigour);
    }
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        return this.equipAndSwap(this, world, user, hand);
    }
    @Override
    public EquipmentSlot getSlotType() {
        return EquipmentSlot.FEET;
    }
    @Override
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
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(Text.translatable("item.sophring.bell_tooltip_1"));
        tooltip.add(Text.translatable("item.sophring.bell_tooltip_2"));
        tooltip.add(Text.translatable("item.sophring.bell_tooltip_3"));
        if(itemStack.getDamage()>=100)
            tooltip.add(Text.translatable("item.sophring.bell_tooltip_repair").formatted(Formatting.RED));
    }
}
