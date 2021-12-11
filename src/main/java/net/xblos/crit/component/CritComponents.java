package net.xblos.crit.component;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistryV3;
import dev.onyxstudios.cca.api.v3.item.ItemComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.item.ItemComponentInitializer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.xblos.crit.Crit;

public class CritComponents implements ItemComponentInitializer {

    private static final ComponentKey<CritComponent> CRIT = ComponentRegistryV3.INSTANCE
        .getOrCreate(CritComponent.ID, CritComponent.class);

    public static CritComponent get(ItemStack itemStack) {
        return CRIT.get(itemStack);
    }

    @Override
    public void registerItemComponentFactories(ItemComponentFactoryRegistry registry) {
        for (Item item : Crit.ITEMS.toArray())
            registry.register(item, CRIT, itemStack -> new CritComponent(itemStack, CRIT));
    }
}
