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
import net.xblos.crit.CritStats;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class CritTrinket extends TrinketItem {

    public static final int MAX_TIER = 3;

    protected final int tier;
    protected final int chance;
    protected final int damage;

    public CritTrinket(int tier) {
        super(new Item.Settings().maxDamage(200).rarity(Rarity.UNCOMMON));
        this.tier = tier;
        chance = CritStats.trinketChance(tier);
        damage = CritStats.trinketDamage(tier);
    }

    public int getChance() {
        return chance;
    }

    public int getDamage() {
        return damage;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(new TranslatableText("item.crit.trinket.desc_" + tier).formatted(Formatting.DARK_GRAY));
    }
}
