package net.xblos.crit.component;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistryV3;
import dev.onyxstudios.cca.api.v3.item.ItemComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.item.ItemComponentInitializer;
import net.xblos.crit.Crit;

public class CritComponents implements ItemComponentInitializer {

    public static final ComponentKey<CritComponent> CRIT = ComponentRegistryV3.INSTANCE
        .getOrCreate(CritComponent.ID, CritComponent.class);

    @Override
    public void registerItemComponentFactories(ItemComponentFactoryRegistry registry) {
        registry.register(Crit.ITEMS.getCritRing(), CRIT, CritComponent::new);
    }
}
