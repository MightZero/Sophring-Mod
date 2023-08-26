package com.mightzero.sophring.mixin;
import com.mightzero.sophring.EquippedStackAccessor;
import com.mightzero.sophring.SophringEffects;
import com.mightzero.sophring.SophringItems;
import dev.emi.trinkets.api.TrinketComponent;
import dev.emi.trinkets.api.TrinketsApi;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.*;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.Shadow;


@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements EquippedStackAccessor {
    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    public LivingEntity getEntity() {
        return (LivingEntity) (Entity) this;
    }

    @Shadow
    public native void setHealth(float health);

    @Shadow
    public abstract boolean addStatusEffect(StatusEffectInstance effect);

    @Shadow
    public abstract float getMaxHealth();
    @Shadow
    public abstract boolean hasStatusEffect(StatusEffect effect);

    @Shadow
    public abstract ItemStack getEquippedStack(EquipmentSlot slot);

    public void addEffect() {
        this.setHealth(this.getMaxHealth() * 0.7f);
        this.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 1000, 2));
        this.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 500, 1));
        this.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 800, 0));
    }

    @Inject(method = "tryUseTotem", at = @At("HEAD"), cancellable = true)
    public void tryUseRing(DamageSource source, CallbackInfoReturnable<Boolean> callback) {
        if (this.getEntity().isPlayer()) {
            TrinketComponent comp = TrinketsApi.getTrinketComponent(this.getEntity()).get();
            ItemStack aglet = comp.getInventory().get("feet").get("aglet").getStack(0);
            if (aglet.getItem() == SophringItems.Bell && aglet.getDamage() < 100) {
                aglet.setDamage(aglet.getDamage() + 100);
                aglet.setRepairCost(0);
                addEffect();
                callback.setReturnValue(true);
                return;
            }
        }
    }
    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    public void warm_dream_damage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        Entity Attacker = source.getAttacker();
        if (Attacker instanceof LivingEntity) {
            if (((LivingEntity) Attacker).hasStatusEffect(SophringEffects.WARM_DREAM))
                cir.setReturnValue(false);
        }
        if (this.hasStatusEffect(SophringEffects.WARM_DREAM)) {
            cir.setReturnValue(false);
        }
    }
    private int sneak_time=0;
    private int ticks=0;
    private int sneak_count=0;
    @Inject(method = "tick", at = @At("HEAD"))
    private void use_key(CallbackInfo ci) {
        Entity entity = this;
        if(entity instanceof LivingEntity) {
            LivingEntity e=(LivingEntity) entity;
            if(e.isPlayer()) {
                if(this.isSneaking())sneak_time+=1;
                else {
                    ticks+=1;
                    if(ticks>100)
                    {
                        ticks=0;
                        sneak_count=0;

                    }
                    if (sneak_time > 0) {
                        sneak_count += 1;
                    }
                    sneak_time = 0;
                }
                TrinketComponent comp = TrinketsApi.getTrinketComponent(e).get();
                ItemStack necklace = comp.getInventory().get("chest").get("necklace").getStack(0);
                if (e.getHealth() < e.getMaxHealth() / 2) {
                    if (sneak_count >= 3) {
                        if (necklace.getItem() == SophringItems.Key) {
                            e.addStatusEffect(new StatusEffectInstance(SophringEffects.WARM_DREAM, 1200, 0));
                            necklace.setCount(0);
                        }
                        sneak_count = 0;
                    }
                } else {
                    sneak_count = 0;
                }
            }
        }
    }

}

