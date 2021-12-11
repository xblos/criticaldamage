package net.xblos.crit.registry;

import net.xblos.crit.Crit;
import net.xblos.crit.effect.CritChanceStatusEffect;
import net.xblos.crit.effect.CritDamageStatusEffect;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class CritStatusEffects implements CritRegistry {

    private CritChanceStatusEffect critChance;
    private CritDamageStatusEffect critDamage;

    @Override
    public void register() {
        critChance = Registry.register(
            Registry.STATUS_EFFECT,
            new Identifier(Crit.MODID, "chance"),
            new CritChanceStatusEffect()
        );

        critDamage = Registry.register(
            Registry.STATUS_EFFECT,
            new Identifier(Crit.MODID, "damage"),
            new CritDamageStatusEffect()
        );
    }

    public CritChanceStatusEffect getCritChanceEffect() {
        return critChance;
    }

    public CritDamageStatusEffect getCritDamageEffect() {
        return critDamage;
    }
}
