package net.xblos.crit.registry;

import net.minecraft.item.Item;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.registry.Registry;
import net.xblos.crit.Crit;
import net.xblos.crit.item.CritNecklace;
import net.xblos.crit.item.CritRing;

public class Items implements CritRegistry {

    private CritRing critRing;
    private CritNecklace critNecklace;

    private Item[] items;

    @Override
    public void register() {
        critRing = Registry.register(Registry.ITEM, CritRing.ID, new CritRing());
        critNecklace = Registry.register(Registry.ITEM, CritNecklace.ID, new CritNecklace());

        items = new Item[] { critRing, critNecklace };

        UniformLootNumberProvider provider = UniformLootNumberProvider.create(0f, 1f);
        Crit.LOOT_TABLE_HELPER.addToAllChests(critRing, provider);
        Crit.LOOT_TABLE_HELPER.addToAllChests(critNecklace, provider);
    }

    public CritRing getCritRing() {
        return critRing;
    }

    public CritNecklace getCritNecklace() {
        return critNecklace;
    }

    public Item[] toArray() {
        return items;
    }
}
