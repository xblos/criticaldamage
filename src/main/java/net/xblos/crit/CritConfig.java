package net.xblos.crit;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry.BoundedDiscrete;
import me.shedaniel.autoconfig.annotation.ConfigEntry.ColorPicker;

import static me.shedaniel.autoconfig.annotation.ConfigEntry.Gui.*;

@SuppressWarnings("FieldMayBeFinal")
@Config(name = "crit")
public class CritConfig implements ConfigData {

    @CollapsibleObject(startExpanded = true)
    private final Base base = new Base();

    @CollapsibleObject(startExpanded = true)
    private final Potion potion = new Potion();

    @CollapsibleObject(startExpanded = true)
    private final Effect effect = new Effect();

    @CollapsibleObject(startExpanded = true)
    private final Enchantment enchantment = new Enchantment();

    @CollapsibleObject(startExpanded = true)
    private final EntityParticles entityParticles = new EntityParticles();

    private static class Base {
        @BoundedDiscrete(max = 100L)
        private int critChance = 0;
        private int critDamage = 10;
    }

    private static class Potion {
        private int critChanceDuration = 60;
        private int critDamageDuration = 60;
    }

    private static class Effect {
        @BoundedDiscrete(max = 100L)
        private int critChance = 20;
        private int critDamage = 40;
    }

    private static class Enchantment {
        @BoundedDiscrete(max = 100L)
        private int critChance = 4;
        private int critDamage = 5;
    }

    private static class EntityParticles {
        private boolean particleEnabled = true;
        @ColorPicker
        private int particleColor = 0xf44336;
        private String particleText = "CRITICAL!";
        @BoundedDiscrete(min = 30L, max = 100L)
        private int particleSize = 70;
        private int particleDuration = 25;
    }

    public int getBaseCritDamage() {
        return base.critDamage;
    }

    public int getBaseCritChance() {
        return base.critChance;
    }

    public int getPotionCritChanceDuration() {
        return potion.critChanceDuration;
    }

    public int getPotionCritDamageDuration() {
        return potion.critDamageDuration;
    }

    public int getEffectCritChance() {
        return effect.critChance;
    }

    public int getEffectCritDamage() {
        return effect.critDamage;
    }

    public int getEnchantmentCritChance() {
        return enchantment.critChance;
    }

    public int getEnchantmentCritDamage() {
        return enchantment.critDamage;
    }

    public boolean isParticleEnabled() {
        return entityParticles.particleEnabled;
    }

    public int getParticleColor() {
        return entityParticles.particleColor;
    }

    public String getParticleText() {
        return entityParticles.particleText;
    }

    public int getParticleSize() {
        return entityParticles.particleSize;
    }

    public int getParticleDuration() {
        return entityParticles.particleDuration;
    }
}

