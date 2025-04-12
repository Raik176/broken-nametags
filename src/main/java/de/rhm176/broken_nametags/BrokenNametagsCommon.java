package de.rhm176.broken_nametags;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import de.rhm176.broken_nametags.component.ComponentRegistry;
import de.rhm176.broken_nametags.item.ItemRegistry;


public class BrokenNametagsCommon {
	public static final String MOD_ID = "broken_nametags";
	public static BrokenNametagsBase impl;

	public static void init() {
		ComponentRegistry.init();
		ItemRegistry.init();
	}

	public static ResourceLocation id(String path) {
		return ResourceLocation.tryBuild(MOD_ID, path);
	}

	public static ResourceKey<Item> itemKey(String path) {
		return resourceKey(
				//? if >=1.20 {
				Registries.ITEM,
				//?} else
				/*Registry.ITEM_REGISTRY,*/
				path);
	}
	public static <T> ResourceKey<T> resourceKey(ResourceKey<? extends Registry<T>> registry, String path) {
		return ResourceKey.create(registry, id(path));
	}
	public static <T> ResourceKey<T> resourceKey(ResourceKey<? extends Registry<T>> registry, ResourceLocation id) {
		return ResourceKey.create(registry, id);
	}
}
