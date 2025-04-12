package de.rhm176.broken_nametags.fabric;

import net.fabricmc.api.ModInitializer;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import de.rhm176.broken_nametags.BrokenNametagsBase;
import de.rhm176.broken_nametags.BrokenNametagsCommon;

import java.util.function.Function;
import java.util.function.Supplier;

//? if >=1.20.5
import net.minecraft.core.component.DataComponentType;
//? if >=1.20 {
import net.minecraft.core.registries.BuiltInRegistries;
//?}

public class BrokenNametagsFabric implements ModInitializer {
	@Override
	public void onInitialize() {
		BrokenNametagsCommon.impl = new Impl();
		BrokenNametagsCommon.init();
	}

	public static class Impl implements BrokenNametagsBase {
		@Override
		public <T extends Item> Supplier<T> registerItem(String path, Function<Item.Properties, T> factory) {
			ResourceKey<Item> key = BrokenNametagsCommon.itemKey(path);
			T item = Registry.register(
					//? if >=1.20 {
					BuiltInRegistries.ITEM,
					//?} else
					/*Registry.ITEM,*/
					key,
					factory.apply(newProperties(key))
			);
			return () -> item;
		}

		//? if >=1.20.5 {
		@Override
		public <T> Supplier<DataComponentType<T>> registerComponent(String path, DataComponentType.Builder<T> builder) {
			DataComponentType<T> component = Registry.register(
					BuiltInRegistries.DATA_COMPONENT_TYPE,
					BrokenNametagsCommon.id(path),
					builder.build()
			);
			return () -> component;
		}
		//?}
	}
}
