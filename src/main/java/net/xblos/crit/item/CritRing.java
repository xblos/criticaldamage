package net.xblos.crit.item;

import dev.emi.trinkets.api.TrinketItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.xblos.crit.Crit;

public class CritRing extends TrinketItem {
    public static final Identifier ID = new Identifier(Crit.MODID, "ring");
    public CritRing() {
        super(new Item.Settings().maxDamage(200).rarity(Rarity.RARE));
    }
}