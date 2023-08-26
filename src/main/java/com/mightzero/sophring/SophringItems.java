package com.mightzero.sophring;

import com.mightzero.sophring.item.*;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public abstract class SophringItems {
    public static final Item Bell= new Bell(new FabricItemSettings()
            .maxDamage(101).
            rarity(Rarity.EPIC));
    public static final Item Vigour= new Vigour(new FabricItemSettings()
            .rarity(Rarity.RARE));
    public static final Item Key=new Key(new FabricItemSettings()
            .maxCount(1)
            .rarity(Rarity.EPIC));
    public static final Item Icy_Crystal=new Item(new FabricItemSettings()
            .rarity(Rarity.EPIC));
    public static final RegistryKey<ItemGroup> Sophring_Item_Group = RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier(Sophring.MODID, "sophring_item"));
    public static boolean RegItems()
    {
        try {
            Registry.register(Registries.ITEM_GROUP, Sophring_Item_Group, FabricItemGroup.builder()
                    .icon(() -> new ItemStack(Icy_Crystal))
                    .displayName(Text.translatable("itemGroup.sophring_item"))
                    .build());
            Registry.register(Registries.ITEM, new Identifier(Sophring.MODID,"icy_crystal"),Icy_Crystal);
            Registry.register(Registries.ITEM, new Identifier(Sophring.MODID, "bell"), Bell);
            ItemGroupEvents.modifyEntriesEvent(Sophring_Item_Group).register(content -> {
                content.add(Bell);
            });
            Registry.register(Registries.ITEM, new Identifier(Sophring.MODID, "vigour"), Vigour);
            ItemGroupEvents.modifyEntriesEvent(Sophring_Item_Group).register(content -> {
                content.add(Vigour);
            });
            Registry.register(Registries.ITEM, new Identifier(Sophring.MODID,"key"),Key);
            ItemGroupEvents.modifyEntriesEvent(Sophring_Item_Group).register(content -> {
                content.add(Key);
            });
        }
        catch (Exception e)
        {
            return false;
        }
        return true;
    }
}
