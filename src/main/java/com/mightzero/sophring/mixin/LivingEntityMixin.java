package com.mightzero.sophring.mixin;
import com.mightzero.sophring.EquippedStackAccessor;
import com.mightzero.sophring.Sophring;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.*;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements EquippedStackAccessor{
    protected LivingEntityMixin(EntityType<?> entityType_1, World world_1) {
        super(entityType_1, world_1);
    }
    @Shadow public native void setHealth(float health);

    @Shadow public abstract boolean addStatusEffect(StatusEffectInstance effect);

    //@Shadow public native void getEquippedStack(EquipmentSlot slot);
    @Inject(method="tryUseTotem",at=@At("HEAD"),cancellable = true)
    public void tryUseRing(DamageSource source, CallbackInfoReturnable<Boolean> callback)
    {
        ItemStack boots = this.getEquippedStack(EquipmentSlot.FEET);
        if(boots.getItem() ==Sophring.Bell&&boots.getDamage()<100) {
            boots.setDamage(boots.getDamage()+100);
            boots.setRepairCost(0);
            this.setHealth(1.0F);
            this.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 900, 1));
            this.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 100, 1));
            this.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 800, 0));
            this.getWorld().sendEntityStatus(this, (byte) 35);
            callback.setReturnValue(true);
       }
    }
}

