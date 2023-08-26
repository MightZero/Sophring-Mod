package com.mightzero.sophring.mixin;
import com.mightzero.sophring.SophringItems;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.*;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.item.Items;
@Mixin(AnvilScreenHandler.class)
public abstract class AnvilScreenHandlerMixin extends ForgingScreenHandler {
    @Shadow @Final private Property levelCost;

    public AnvilScreenHandlerMixin(@Nullable ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(type, syncId, playerInventory, context);
    }

    @Inject(method = "updateResult", at = @At("HEAD"), cancellable = true)
    private void onUpdateResult(CallbackInfo ci) {
        ItemStack inputStack = this.input.getStack(0);
        ItemStack materialStack = this.input.getStack(1);
        if (inputStack.getItem() == SophringItems.Bell ) {
            this.levelCost.set(1);
            inputStack.setRepairCost(0);
            if(materialStack.getItem() == SophringItems.Vigour && inputStack.getDamage()>0) {
                ItemStack resultStack = inputStack.copy();
                resultStack.setDamage(0);
                this.output.setStack(0, resultStack);
                this.sendContentUpdates();
                ci.cancel();
            }
            if(materialStack.getItem() == Items.ENCHANTED_BOOK){
                this.output.setStack(0, ItemStack.EMPTY);
                this.sendContentUpdates();
                ci.cancel();
            }
        }
        this.sendContentUpdates();
    }
    @Inject(method = "setNewItemName", at = @At("HEAD"), cancellable = true)
    public void setNewItemName(String newItemName,CallbackInfoReturnable<Boolean> callback) {
        if(this.input.getStack(0).getItem()== SophringItems.Bell)callback.setReturnValue(false);
    }
}

