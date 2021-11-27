package net.xblos.crit.potion;

import net.xblos.crit.Crit;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.potion.Potion;

public final class CritChancePotion extends Potion {

    public CritChancePotion() {
        super(
            "critchance",
            new StatusEffectInstance(
                Crit.STATUS_EFFECTS.getCritChanceEffect(),
                Crit.getConfig().getPotionCritChanceDuration() * 20
            )
        );
    }
}
