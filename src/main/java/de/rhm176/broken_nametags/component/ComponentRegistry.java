package de.rhm176.broken_nametags.component;

//? if >=1.20.5 {
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentSerialization;
import de.rhm176.broken_nametags.BrokenNametagsCommon;

import java.util.function.Supplier;
//?}


public class ComponentRegistry {
    //? if >=1.20.5 {
    public static Supplier<DataComponentType<BrokenNametagComponent>> BROKEN_NAMETAG_DATA = BrokenNametagsCommon.impl.registerComponent(
            "broken_nametag_data",
            DataComponentType.<BrokenNametagComponent>builder().persistent(BrokenNametagComponent.CODEC)
    );

    public record BrokenNametagComponent(Component name, Component deathMessage, long timeOfDeath) {
        public static final Codec<BrokenNametagComponent> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                ComponentSerialization.CODEC.fieldOf("name").forGetter(BrokenNametagComponent::name),
                ComponentSerialization.CODEC.fieldOf("deathMessage").forGetter(BrokenNametagComponent::deathMessage),
                Codec.LONG.fieldOf("timeOfDeath").forGetter(BrokenNametagComponent::timeOfDeath)
        ).apply(instance, BrokenNametagComponent::new));
    }
    //?}

    public static void init() { }
}
