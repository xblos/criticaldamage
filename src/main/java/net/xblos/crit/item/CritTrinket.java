package net.xblos.crit.item;

import dev.emi.trinkets.api.TrinketItem;
import net.minecraft.item.Item;
import net.minecraft.util.Rarity;

public abstract class CritTrinket extends TrinketItem {

    public CritTrinket() {
        super(new Item.Settings().maxDamage(200).rarity(Rarity.UNCOMMON));
    }
}
