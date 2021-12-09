package net.xblos.crit;

import dev.emi.trinkets.api.TrinketsApi;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.xblos.crit.component.CritComponent;
import net.xblos.crit.component.CritComponents;
import net.xblos.crit.integration.EntityParticlesIntegration;
import net.xblos.crit.registry.Enchantments;
import net.xblos.crit.registry.Items;
import net.xblos.crit.registry.Potions;
import net.xblos.crit.registry.StatusEffects;
import net.xblos.crit.util.Debug;
import net.xblos.crit.item.CritRing;

public class Crit implements ModInitializer {

    public static final String MODID = "crit";
    public static final String CHANNEL = "net.xblos." + MODID;

    public static final Enchantments ENCHANTMENTS = new Enchantments();
    public static final StatusEffects STATUS_EFFECTS = new StatusEffects();
    public static final Potions POTIONS = new Potions();
    public static final Items ITEMS = new Items();

    private static CritConfig config;

    @Override
    public void onInitialize() {
        AutoConfig.register(CritConfig.class, GsonConfigSerializer::new);
        config = AutoConfig.getConfigHolder(CritConfig.class).getConfig();
        ENCHANTMENTS.register();
        STATUS_EFFECTS.register();
        POTIONS.register();
        ITEMS.register();
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

        TrinketsApi.getTrinketComponent(player).ifPresent(component -> {
            CritRing ring = ITEMS.getCritRing();

            if (component.isEquipped(ring)) {
                CritComponent ringComponent = CritComponents.CRIT.get(ring);
                Debug.msg(player, ringComponent.getChance());
                Debug.msg(player, ringComponent.getDamage());
            }
        });

        if (player.getRandom().nextInt(100) < critChance) {
            EntityParticlesIntegration.sendRequest(player, target);
            return baseDamage * (1f + ((float) critDamage * .01f));
        }

        return baseDamage;
    }
}
