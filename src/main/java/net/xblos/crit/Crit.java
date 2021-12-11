package net.xblos.crit;

import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketComponent;
import dev.emi.trinkets.api.TrinketsApi;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Pair;
import net.xblos.crit.component.CritComponent;
import net.xblos.crit.component.CritComponents;
import net.xblos.crit.integration.EntityParticlesIntegration;
import net.xblos.crit.integration.TrinketsIntegration;
import net.xblos.crit.registry.CritEnchantments;
import net.xblos.crit.registry.CritItems;
import net.xblos.crit.registry.CritPotions;
import net.xblos.crit.registry.CritStatusEffects;
import net.xblos.crit.util.Debug;
import net.xblos.crit.util.LootTableHelper;

import java.util.Optional;

public class Crit implements ModInitializer {

    public static final String MODID = "crit";
    public static final String CHANNEL = "net.xblos." + MODID;

    public static final CritEnchantments ENCHANTMENTS = new CritEnchantments();
    public static final CritStatusEffects STATUS_EFFECTS = new CritStatusEffects();
    public static final CritPotions POTIONS = new CritPotions();
    public static final CritItems ITEMS = new CritItems();

    public static final LootTableHelper LOOT_TABLE_HELPER = new LootTableHelper();

    private static CritConfig config;

    @Override
    public void onInitialize() {
        AutoConfig.register(CritConfig.class, GsonConfigSerializer::new);
        config = AutoConfig.getConfigHolder(CritConfig.class).getConfig();

        ENCHANTMENTS.register();
        STATUS_EFFECTS.register();
        POTIONS.register();
        ITEMS.register();

        LOOT_TABLE_HELPER.registerLoot();
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

        if (TrinketsIntegration.isModLoaded()) {
            Optional<TrinketComponent> component = TrinketsApi.getTrinketComponent(player);
            if (component.isPresent()) {
                for (Item critItem : ITEMS.toArray()) {
                    for (Pair<SlotReference, ItemStack> equipped : component.get().getEquipped(critItem)) {
                        ItemStack ring = equipped.getRight();
                        CritComponent critComponent = CritComponents.get(ring);
                        int trinketChance = critComponent.getChance();
                        int trinketDamage = critComponent.getDamage();
                        Debug.msg(player, "Chance: " + trinketChance);
                        Debug.msg(player, "Damage: " + trinketDamage);
                        critChance += trinketChance;
                        critDamage += trinketDamage;
                    }
                }
            }
        }

        if (player.getRandom().nextInt(100) < critChance) {
            EntityParticlesIntegration.sendRequest(player, target);
            return baseDamage * (1f + ((float) critDamage * .01f));
        }

        return baseDamage;
    }
}
