package net.xblos.crit;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry.BoundedDiscrete;
import me.shedaniel.autoconfig.annotation.ConfigEntry.ColorPicker;
import me.shedaniel.autoconfig.annotation.ConfigEntry.Gui.CollapsibleObject;

@SuppressWarnings("FieldMayBeFinal")
@Config(name = "crit")
public class CritConfig implements ConfigData {

    @CollapsibleObject(startExpanded = true)
    private final Base base = new Base();

    @CollapsibleObject(startExpanded = true)
    private final Enchantment enchantment = new Enchantment();

    @CollapsibleObject(startExpanded = true)
    private final EntityParticles entityParticles = new EntityParticles();

    private static class Base {
        @BoundedDiscrete(max = 100L)
        private int critChance = 0;
        private int critDamage = 10;
    }

    private static class Enchantment {
        private boolean isTreasure = true;
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

    public int getBaseCritChance() {
        return base.critChance;
    }

    public int getBaseCritDamage() {
        return base.critDamage;
    }

    public boolean isEnchantmentTreasure() {
        return enchantment.isTreasure;
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

