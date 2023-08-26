package com.mightzero.sophring.item;

import com.mightzero.sophring.SophringItems;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.ActionResult;

public class Vigour extends Item {
    public Vigour(Settings setting){
        super(setting);
        //Process the falling of Vigour
        AttackEntityCallback.EVENT.register((player, world, hand, entity, isHit) -> {
            if (entity instanceof LivingEntity && ! (entity instanceof PlayerEntity)) {
                if (!player.isSpectator() && !entity.getCommandTags().contains("Vigour_Fallen")&& ((LivingEntity)entity).getHealth()<=0.4*((LivingEntity)entity).getMaxHealth() ) {
                    if(Math.random() < 0.5f) {
                        entity.dropItem(SophringItems.Vigour);
                        entity.addCommandTag("Vigour_Fallen");
                    }
                }
            }
            return ActionResult.PASS;
        });
    }

}

