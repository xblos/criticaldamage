package net.xblos.crit.mixin;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.xblos.crit.component.CritComponent;
import net.xblos.crit.component.CritComponents;
import net.xblos.crit.item.CritTrinket;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.List;
import java.util.Random;

import static net.minecraft.util.Formatting.DARK_GRAY;

@Mixin(ItemStack.class)
public abstract class TooltipMixin {

    @Shadow public abstract Item getItem();

    @Shadow public abstract boolean damage(int amount, Random random, @Nullable ServerPlayerEntity player);

    @Inject(method = "getTooltip", at = @At("RETURN"), locals = LocalCapture.CAPTURE_FAILHARD)
    public void injectCritComponentTips(@Nullable PlayerEntity player, TooltipContext ctx, CallbackInfoReturnable<List<Text>> cir, List<Text> list) {
        if (!(this.getItem() instanceof CritTrinket)) return;
        if (ctx.isAdvanced()) return;
        if (player == null) return;
        if (!player.getInventory().contains((ItemStack) (Object) this)) return;

        addCritComponentTips(list);
    }

    private void addCritComponentTips(List<Text> tips) {
        CritComponent critComponent = CritComponents.get((ItemStack) (Object) this);
        String chanceTip = new TranslatableText("tooltip.crit.chance").getString();
        String damageTip = new TranslatableText("tooltip.crit.damage").getString();
        tips.add(new LiteralText(chanceTip + ": " + critComponent.getChance() + "%").formatted(DARK_GRAY));
        tips.add(new LiteralText(damageTip + ": " + critComponent.getDamage() + "%").formatted(DARK_GRAY));
    }
}
