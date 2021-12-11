package net.xblos.crit.registry;

import net.xblos.crit.Crit;
import net.xblos.crit.enchantment.CritChanceEnchantment;
import net.xblos.crit.enchantment.CritDamageEnchantment;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class CritEnchantments implements CritRegistry {

    private CritChanceEnchantment critChance;
    private CritDamageEnchantment critDamage;

    @Override
    public void register() {
        critChance = Registry.register(
            Registry.ENCHANTMENT,
            new Identifier(Crit.MODID, "chance"),
            new CritChanceEnchantment()
        );

        critDamage = Registry.register(
            Registry.ENCHANTMENT,
            new Identifier(Crit.MODID, "damage"),
            new CritDamageEnchantment()
        );
    }

    public CritChanceEnchantment getCritChanceEnchantment() {
        return critChance;
    }

    public CritDamageEnchantment getCritDamageEnchantment() {
        return critDamage;
    }
}
