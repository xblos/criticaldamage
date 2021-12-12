package net.xblos.crit.registry;

import net.minecraft.item.Item;
import net.minecraft.loot.provider.number.BinomialLootNumberProvider;
import net.minecraft.util.registry.Registry;
import net.xblos.crit.Crit;
import net.xblos.crit.integration.TrinketsIntegration;
import net.xblos.crit.item.CritNecklace;
import net.xblos.crit.item.CritRing;
import net.xblos.crit.item.CritTrinket;

public class CritItems implements CritRegistry {

    private CritTrinket[] trinkets;

    @Override
    public void register() {
        registerTrinkets();
    }

    private void registerTrinkets() {
        if (!TrinketsIntegration.isModLoaded()) return;

        int trinketCount = 2 * CritTrinket.MAX_TIER;
        trinkets = new CritTrinket[trinketCount];
        BinomialLootNumberProvider rng = BinomialLootNumberProvider.create(trinketCount, .01f);

        for (int i = 0, tier = 1; i < trinketCount; i += 2) {
            CritRing ring = Registry.register(Registry.ITEM, CritRing.ID + "_" + tier, new CritRing(tier));
            CritNecklace necklace = Registry.register(Registry.ITEM, CritNecklace.ID + "_" + tier, new CritNecklace(tier));
            Crit.LOOT_TABLE_HELPER.addToAllChests(ring, rng);
            Crit.LOOT_TABLE_HELPER.addToAllChests(necklace, rng);
            trinkets[i] = ring;
            trinkets[i + 1] = necklace;
            tier += 1;
        }
    }

    public Item[] getTrinkets() {
        return trinkets;
    }
}
