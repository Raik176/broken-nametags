package org.rhm.broken_nametags.item;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.rhm.broken_nametags.component.ComponentRegistry;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

public class BrokenNametagItem extends Item {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public BrokenNametagItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isFoil(ItemStack itemStack) {
        return super.isFoil(itemStack) || getEntityName(itemStack) != null;
    }

    @Override
    //? if >=1.20.5 {
    public void appendHoverText(ItemStack itemStack, TooltipContext tooltipContext, List<Component> list, TooltipFlag tooltipFlag) {
    //?} else {
    /*public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> list, TooltipFlag tooltipFlag) {
    *///?}
        Component name = getEntityName(itemStack);
        Component deathMsg = getEntityDeathMessage(itemStack);
        long deathTime = getEntityDeathTime(itemStack);

        if (name != null) {
            list.add(Component.translatable(getDescriptionId() + ".base").withStyle(ChatFormatting.GRAY));
            list.add(Component.translatable(getDescriptionId() + ".name", Component.empty().append(name).withStyle(ChatFormatting.GOLD)).withStyle(ChatFormatting.GRAY));
            if (deathTime != 0)
                list.add(Component.translatable(
                        getDescriptionId() + ".time",
                        Component.empty().append(Instant.ofEpochMilli(deathTime).atZone(ZoneId.systemDefault()).format(formatter) + " " + ZoneId.systemDefault().getDisplayName(TextStyle.SHORT, Locale.getDefault())).withStyle(ChatFormatting.GOLD)
                ).withStyle(ChatFormatting.GRAY));
            if (deathMsg != null)
                list.add(Component.translatable(getDescriptionId() + ".deathMessage", Component.empty().append(deathMsg).withStyle(ChatFormatting.GOLD)).withStyle(ChatFormatting.GRAY));
        }

        //? if >=1.20.5 {
        super.appendHoverText(itemStack, tooltipContext, list, tooltipFlag);
        //?} else {
        /*super.appendHoverText(itemStack, level, list, tooltipFlag);
        *///?}
    }

    @Nullable
    private Component getEntityName(ItemStack itemStack) {
        //? if >=1.20.5 {
        ComponentRegistry.BrokenNametagComponent component = itemStack.get(ComponentRegistry.BROKEN_NAMETAG_DATA.get());
        if (component != null) return component.name();
        //?} else {
        /*CompoundTag tag = itemStack.getTag();
        if (tag != null && tag.contains("data"))
            return Component.Serializer.fromJson(tag.getCompound("data").getString("name"));
        *///?}
        return null;
    }

    @Nullable
    private Component getEntityDeathMessage(ItemStack itemStack) {
        //? if >=1.20.5 {
        ComponentRegistry.BrokenNametagComponent component = itemStack.get(ComponentRegistry.BROKEN_NAMETAG_DATA.get());
        if (component != null) return component.deathMessage();
        //?} else {
        /*CompoundTag tag = itemStack.getTag();
        if (tag != null && tag.contains("data"))
            return Component.Serializer.fromJson(tag.getCompound("data").getString("deathMessage"));
        *///?}
        return null;
    }

    private long getEntityDeathTime(ItemStack itemStack) {
        //? if >=1.20.5 {
        ComponentRegistry.BrokenNametagComponent component = itemStack.get(ComponentRegistry.BROKEN_NAMETAG_DATA.get());
        if (component != null) return component.timeOfDeath();
        //?} else {
        /*CompoundTag tag = itemStack.getTag();
        if (tag != null && tag.contains("data"))
            return tag.getCompound("data").getLong("deathTime");
        *///?}
        return 0;
    }
}
