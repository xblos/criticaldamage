package net.xblos.crit;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.xblos.crit.integration.EntityParticlesIntegration;
import net.xblos.crit.registry.Enchantments;
import net.xblos.crit.registry.Potions;
import net.xblos.crit.registry.StatusEffects;

public class Crit implements ModInitializer {

    public static final String MODID = "crit";
    public static final String CHANNEL = "net.xblos." + MODID;

    public static final Enchantments ENCHANTMENTS = new Enchantments();
    public static final StatusEffects STATUS_EFFECTS = new StatusEffects();
    public static final Potions POTIONS = new Potions();

    private static CritConfig config;

    @Override
    public void onInitialize() {
        AutoConfig.register(CritConfig.class, GsonConfigSerializer::new);
        config = AutoConfig.getConfigHolder(CritConfig.class).getConfig();
        ENCHANTMENTS.register();
        STATUS_EFFECTS.register();
        POTIONS.register();
    }

    public static CritConfig getConfig() {
        return config;
    }

    public static float apply(PlayerEntity player, Entity target, float baseDamage) {
        int critDamage = config.getBaseCritDamage();
        int critChance = config.getBaseCritChance();

        for (ItemStack armorPiece : player.getArmorItems()) {
            critChance += ENCHANTMENTS.getCritChanceEnchantment().getChance(armorPiece);
            critDamage += ENCHANTMENTS.getCritDamageEnchantment().getMultiplier(armorPiece);
        }

        critChance += STATUS_EFFECTS.getCritChanceEffect().getChance(player);
        critDamage += STATUS_EFFECTS.getCritDamageEffect().getMultiplier(player);

        if (player.getRandom().nextInt(100) < critChance) {
            EntityParticlesIntegration.sendRequest(player, target);
            return baseDamage * (1f + ((float) critDamage * .01f));
        }

        return baseDamage;
    }
}
