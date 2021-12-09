package net.xblos.crit.component;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.item.ItemComponent;
import net.fabricmc.fabric.api.util.NbtType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.xblos.crit.Crit;

public class CritComponent extends ItemComponent {

    public static final Identifier ID = new Identifier(Crit.MODID, "component");

    public CritComponent(ItemStack stack) {
        super(stack);
    }

    public CritComponent(ItemStack stack, ComponentKey<?> key) {
        super(stack, key);
    }

    public int getChance() {
        if (!hasTag("chance", NbtType.INT))
            putInt("chance", randomChance());
        return getInt("chance");
    }

    public int getDamage() {
        if (!hasTag("damage", NbtType.INT))
            putInt("damage", randomDamage());
        return getInt("damage");
    }

    private int randomChance() {
        return 5;
    }

    private int randomDamage() {
        return 10;
    }
}
