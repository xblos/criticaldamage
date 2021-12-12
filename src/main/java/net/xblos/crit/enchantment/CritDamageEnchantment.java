package net.xblos.crit.enchantment;

import net.xblos.crit.Crit;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.xblos.crit.CritStats;

public final class CritDamageEnchantment extends Enchantment {

    public CritDamageEnchantment() {
        super(Rarity.UNCOMMON, EnchantmentTarget.ARMOR, new EquipmentSlot[] {
            EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET
        });
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public int getMinPower(int level) {
        return 1 + (level - 1) * 11;
    }

    @Override
    public int getMaxPower(int level) {
        return getMinPower(level) + 20;
    }

    @Override
    public boolean isAvailableForEnchantedBookOffer() {
        return true;
    }

    public int getMultiplier(int level) {
        return CritStats.enchantmentDamage(level);
    }

    public int getMultiplier(ItemStack armorPiece) {
        return getMultiplier(EnchantmentHelper.getLevel(Crit.ENCHANTMENTS.getCritDamageEnchantment(), armorPiece));
    }
}
