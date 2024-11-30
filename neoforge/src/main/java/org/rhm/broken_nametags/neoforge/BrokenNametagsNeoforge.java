package org.rhm.broken_nametags.neoforge;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.rhm.broken_nametags.BrokenNametagsBase;
import org.rhm.broken_nametags.BrokenNametagsCommon;
import net.neoforged.fml.common.Mod;

import java.util.function.Function;
import java.util.function.Supplier;

//? if >=1.20.5
import net.minecraft.core.component.DataComponentType;

@Mod(BrokenNametagsCommon.MOD_ID)
public class BrokenNametagsNeoforge {
	public BrokenNametagsNeoforge(IEventBus eventBus, ModContainer container) {
		BrokenNametagsCommon.impl = new Impl();
		BrokenNametagsCommon.init();

		Impl.register(eventBus);
	}

	public static class Impl implements BrokenNametagsBase {
		private static final DeferredRegister.Items ITEM_REGISTRY = DeferredRegister.createItems(BrokenNametagsCommon.MOD_ID);
		//? if >=1.20.5 {
		private static final DeferredRegister.DataComponents COMPONENT_REGISTRY = DeferredRegister.createDataComponents(
				//? if >=1.21.3
				/*Registries.DATA_COMPONENT_TYPE,*/
				BrokenNametagsCommon.MOD_ID
		);
		//?}


		@Override
		public <T extends Item> Supplier<T> registerItem(String path, Function<Item.Properties, T> factory) {
			return ITEM_REGISTRY.registerItem(path, factory);
		}

		//? if >=1.20.5 {
		@Override
		public <T> Supplier<DataComponentType<T>> registerComponent(String path, DataComponentType.Builder<T> builder) {
			return COMPONENT_REGISTRY.registerComponentType(path,b -> builder);
		}
		//?}

		public static void register(IEventBus eventBus) {
			//? if >=1.20.5
			COMPONENT_REGISTRY.register(eventBus);
			ITEM_REGISTRY.register(eventBus);
		}
	};
}
