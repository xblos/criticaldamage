package net.xblos.crit.item;

import net.minecraft.util.Identifier;
import net.xblos.crit.Crit;

public class CritNecklace extends CritTrinket {
    public static final Identifier ID = new Identifier(Crit.MODID, "necklace");

    public CritNecklace(int tier) {
        super(tier);
    }
}