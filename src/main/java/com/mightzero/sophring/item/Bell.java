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

public class Bell extends Item implements Equipment {
    public Bell(Settings settings) {
        super(settings);
        DispenserBlock.registerBehavior(this, ArmorItem.DISPENSER_BEHAVIOR);
    }
    @Override
    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        return ingredient.isOf(Items.BLUE_ICE);
    }
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        return this.equipAndSwap(this, world, user, hand);
    }
    public EquipmentSlot getSlotType() {
        return EquipmentSlot.FEET;
    }
    public boolean isDamageable() {
        return true;
    }
    public boolean damage(DamageSource source){return false;}
    @Override
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        // 默认为白色文本
        tooltip.add(Text.translatable("item.sophring.bell_tooltip_1"));
        tooltip.add(Text.translatable("item.sophring.bell_tooltip_2"));
        tooltip.add(Text.translatable("item.sophring.bell_tooltip_3"));
        if(itemStack.getDamage()>=100)
        tooltip.add(Text.translatable("item.sophring.bell_tooltip_repair").formatted(Formatting.RED));
    }
}
