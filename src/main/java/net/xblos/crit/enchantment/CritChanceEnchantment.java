package net.xblos.crit.enchantment;

import net.xblos.crit.Crit;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.xblos.crit.CritStats;

public final class CritChanceEnchantment extends Enchantment {

    public CritChanceEnchantment() {
        super(Rarity.RARE, EnchantmentTarget.ARMOR, new EquipmentSlot[] {
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

    @Override
    public boolean isTreasure() {
        return Crit.getConfig().isEnchantmentTreasure();
    }

    public int getChance(int level) {
        return CritStats.enchantmentChance(level);
    }

    public int getChance(ItemStack armorPiece) {
        return getChance(EnchantmentHelper.getLevel(Crit.ENCHANTMENTS.getCritChanceEnchantment(), armorPiece));
    }
}
