package net.xblos.crit.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.xblos.crit.Crit;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin({LivingEntity.class, PlayerEntity.class})
public class PlayerEntityMixin {

    @ModifyVariable(method = "damage", at = @At("HEAD"), argsOnly = true)
    private float injectDamage(float baseDamage, DamageSource source) {
        if (source.getAttacker() instanceof PlayerEntity player && !player.getEntityWorld().isClient())
            return Crit.apply(player, (Entity) (Object) this, baseDamage);
        return baseDamage;
    }
}