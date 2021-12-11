package net.xblos.crit.registry;

import net.xblos.crit.Crit;
import net.xblos.crit.mixin.BrewingRecipeRegistryInvoker;
import net.xblos.crit.potion.CritChancePotion;
import net.xblos.crit.potion.CritDamagePotion;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class CritPotions implements CritRegistry {

    private CritChancePotion critChance;
    private CritDamagePotion critDamage;

    @Override
    public void register() {
        critChance = Registry.register(
            Registry.POTION,
            new Identifier(Crit.MODID, "chance"),
            new CritChancePotion()
        );

        critDamage = Registry.register(
            Registry.POTION,
            new Identifier(Crit.MODID, "damage"),
            new CritDamagePotion()
        );

        BrewingRecipeRegistryInvoker.registerPotionRecipe(
            net.minecraft.potion.Potions.STRONG_STRENGTH,
            Items.GHAST_TEAR,
            critDamage
        );

        BrewingRecipeRegistryInvoker.registerPotionRecipe(
            net.minecraft.potion.Potions.STRONG_STRENGTH,
            Items.PHANTOM_MEMBRANE,
            critChance
        );
    }

    public CritChancePotion getCritChancePotion() {
        return critChance;
    }

    public CritDamagePotion getCritDamagePotion() {
        return critDamage;
    }
}
