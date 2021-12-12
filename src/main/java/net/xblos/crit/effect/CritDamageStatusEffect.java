package net.xblos.crit.effect;

import net.xblos.crit.Crit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;
import net.xblos.crit.CritStats;
import org.jetbrains.annotations.Nullable;

public final class CritDamageStatusEffect extends StatusEffect {

    public CritDamageStatusEffect() {
        super(StatusEffectCategory.BENEFICIAL, 0xe06666);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) { }

    @Override
    public void applyInstantEffect(@Nullable Entity source, @Nullable Entity attacker, LivingEntity target, int amplifier, double proximity) { }

    public int getMultiplier(PlayerEntity player) {
        return player.getActiveStatusEffects().get(Crit.STATUS_EFFECTS.getCritDamageEffect()) != null ?
            CritStats.EFFECT_DAMAGE : 0;
    }
}
