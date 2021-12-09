package net.xblos.crit.registry;

import net.minecraft.util.registry.Registry;
import net.xblos.crit.item.CritRing;

public class Items implements CritRegistry {

    private CritRing critRing;

    @Override
    public void register() {
        critRing = Registry.register(Registry.ITEM, CritRing.ID, new CritRing());
    }

    public CritRing getCritRing() {
        return critRing;
    }
}
