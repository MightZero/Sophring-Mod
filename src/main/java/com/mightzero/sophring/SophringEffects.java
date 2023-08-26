package com.mightzero.sophring;

import com.mightzero.sophring.effect.WarmDreamEffect;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public abstract class SophringEffects {
    public static StatusEffect WARM_DREAM = new WarmDreamEffect();
    public static void RegEffects()
    {
        Registry.register(Registries.STATUS_EFFECT, new Identifier("sophring", "warm_dream"), WARM_DREAM);
    }
}
