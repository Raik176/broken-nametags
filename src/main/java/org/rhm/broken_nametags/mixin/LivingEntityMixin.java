package org.rhm.broken_nametags.mixin;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.rhm.broken_nametags.component.ComponentRegistry;
import org.rhm.broken_nametags.item.ItemRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    public LivingEntityMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "die", at = @At("TAIL"))
    private void die(DamageSource damageSource, CallbackInfo ci) {
        if (!this.isRemoved() &&
                //? if >= 1.20 {
                !this.level()
                //?} else
                /*this.level*/
                        .isClientSide) {
            if (this.hasCustomName() || (this instanceof OwnableEntity ownable && ownable.getOwnerUUID() != null)) {
                this.spawnAtLocation(
                        //? if >1.20.6
                        /*(ServerLevel)level(),*/
                        broken_nametags$newNametag(
                                getName(),
                                damageSource.getLocalizedDeathMessage((LivingEntity)(Object)this)
                        )
                );
            }
        }
    }

    @NotNull @Unique
    private ItemStack broken_nametags$newNametag(@NotNull Component name, @Nullable Component deathMsg) {
        ItemStack stack = new ItemStack(ItemRegistry.BROKEN_NAMETAG.get(), 1);
        //? if >=1.20.5 {
        stack.set(ComponentRegistry.BROKEN_NAMETAG_DATA.get(), new ComponentRegistry.BrokenNametagComponent(
                name,
                deathMsg,
                System.currentTimeMillis()
        ));
        //?} else {
        /*CompoundTag tag = new CompoundTag();

        tag.putString("name", Component.Serializer.toJson(name));
        tag.putString("deathMessage", Component.Serializer.toJson(deathMsg));
        tag.putLong("deathTime", System.currentTimeMillis());

        stack.addTagElement("data",tag);
        *///?}
        return stack;
    }
}