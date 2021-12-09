package net.xblos.crit.enchantment;

import net.xblos.crit.Crit;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;

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
    public boolean isTreasure() {
        return true;
    }

    @Override
    public boolean isAvailableForEnchantedBookOffer() {
        return true;
    }

    public int getMultiplier(int level) {
        return level * Crit.getConfig().getEnchantmentCritDamage();
    }

    public int getMultiplier(ItemStack armorPiece) {
        return getMultiplier(EnchantmentHelper.getLevel(Crit.ENCHANTMENTS.getCritDamageEnchantment(), armorPiece));
    }
}
