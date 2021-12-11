package net.xblos.crit.mixin;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
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
//        if (!(this.getItem() instanceof CritTrinket)) return;
//        if (ctx.isAdvanced()) return;
//        addCritComponentTips(list);
    }

    private void addCritComponentTips(List<Text> tips) {
        CritComponent critComponent = CritComponents.get((ItemStack) (Object) this);
        int chance = critComponent.getChance();
        int damage = critComponent.getDamage();
        if (chance > 0) tips.add(new TranslatableText("tooltip.crit.chance", chance).formatted(DARK_GRAY));
        if (damage > 0) tips.add(new TranslatableText("tooltip.crit.damage", damage).formatted(DARK_GRAY));
    }
}
