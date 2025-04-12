package de.rhm176.broken_nametags.forge;


import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import de.rhm176.broken_nametags.BrokenNametagsBase;
import de.rhm176.broken_nametags.BrokenNametagsCommon;

import java.util.function.Function;
import java.util.function.Supplier;

//? if >=1.20.5
import net.minecraft.core.component.DataComponentType;

@Mod(BrokenNametagsCommon.MOD_ID)
public class BrokenNametagsForge {
	public BrokenNametagsForge(FMLJavaModLoadingContext context) {
		IEventBus eventBus = context.getModEventBus();

		BrokenNametagsCommon.impl = new Impl();
		BrokenNametagsCommon.init();
		Impl.register(eventBus);
	}

	public static class Impl implements BrokenNametagsBase {
		private static final DeferredRegister<Item> ITEM_REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, BrokenNametagsCommon.MOD_ID);
		//? if >=1.20.5
		private static final DeferredRegister<DataComponentType<?>> COMPONENT_REGISTRY = DeferredRegister.create(Registries.DATA_COMPONENT_TYPE, BrokenNametagsCommon.MOD_ID);


		@Override
		public <T extends Item> Supplier<T> registerItem(String path, Function<Item.Properties, T> factory) {
			return ITEM_REGISTRY.register(path, () -> factory.apply(newProperties(BrokenNametagsCommon.itemKey(path))));
		}

		//? if >=1.20.5 {
		@Override
		public <T> Supplier<DataComponentType<T>> registerComponent(String path, DataComponentType.Builder<T> builder) {
			return COMPONENT_REGISTRY.register(path, builder::build);
		}
		//?}

		public static void register(IEventBus eventBus) {
			//? if >=1.20.5
			COMPONENT_REGISTRY.register(eventBus);
			ITEM_REGISTRY.register(eventBus);
		}
	};
}
