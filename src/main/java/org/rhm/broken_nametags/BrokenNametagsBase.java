package org.rhm.broken_nametags;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

import java.util.function.Function;
import java.util.function.Supplier;

//? if >=1.20.5
import net.minecraft.core.component.DataComponentType;

public interface BrokenNametagsBase {
    <T extends Item> Supplier<T> registerItem(String path, Function<Item.Properties, T> factory);
    //? if >=1.20.5
    <T> Supplier<DataComponentType<T>> registerComponent(String path, DataComponentType.Builder<T> builder);

    default Item.Properties newProperties(ResourceKey<Item> key) {
        return new Item.Properties()
        //? if >=1.21.2
                /*.setId(key)*/
        ;
    }
}
