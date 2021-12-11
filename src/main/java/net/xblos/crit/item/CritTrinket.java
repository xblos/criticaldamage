package net.xblos.crit.item;

import dev.emi.trinkets.api.TrinketItem;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;
import net.xblos.crit.component.CritComponent;
import net.xblos.crit.component.CritComponents;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class CritTrinket extends TrinketItem {

    public CritTrinket() {
        super(new Item.Settings().maxDamage(200).rarity(Rarity.UNCOMMON));
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        CritComponent critComponent = CritComponents.get(stack);
        int chance = critComponent.getChance();
        int damage = critComponent.getDamage();
        if (chance > 0) tooltip.add(new TranslatableText("tooltip.crit.chance", chance).formatted(Formatting.DARK_GRAY));
        if (damage > 0) tooltip.add(new TranslatableText("tooltip.crit.damage", damage).formatted(Formatting.DARK_GRAY));
    }
}
