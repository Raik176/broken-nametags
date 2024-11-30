package org.rhm.broken_nametags.item;

import org.rhm.broken_nametags.BrokenNametagsCommon;

import java.util.function.Supplier;

public class ItemRegistry {
    public static final Supplier<BrokenNametagItem> BROKEN_NAMETAG = BrokenNametagsCommon.impl.registerItem(
            "broken_nametag",
            (settings) -> new BrokenNametagItem(settings.stacksTo(1))
    );

    public static void init() { }
}
