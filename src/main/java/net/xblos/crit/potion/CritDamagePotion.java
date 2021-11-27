package net.xblos.crit.potion;

import net.xblos.crit.Crit;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.potion.Potion;

public final class CritDamagePotion extends Potion {

    public CritDamagePotion() {
        super(
            "critdamage",
            new StatusEffectInstance(
                Crit.STATUS_EFFECTS.getCritDamageEffect(),
                Crit.getConfig().getPotionCritDamageDuration() * 20
            )
        );
    }
}
