package net.xblos.crit;

public class CritStats {

    public static final int EFFECT_CHANCE = 10;
    public static final int EFFECT_DAMAGE = 20;

    public static final int POTION_CHANCE_DURATION = 90 * 20;
    public static final int POTION_DAMAGE_DURATION = 90 * 20;

    public static int enchantmentChance(int level) {
        return level > 0 ? 5 + (2 * level) : 0;
    }

    public static int enchantmentDamage(int level) {
        return level > 0 ? 6 * level : 0;
    }

    public static int trinketChance(int tier) {
        return 3 * tier + 1;
    }

    public static int trinketDamage(int tier) {
        return 6 * tier + 2;
    }
}