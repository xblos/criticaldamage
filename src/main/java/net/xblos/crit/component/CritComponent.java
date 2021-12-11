package net.xblos.crit.component;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.item.ItemComponent;
import net.fabricmc.fabric.api.util.NbtType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.xblos.crit.Crit;
import net.xblos.crit.CritConfig;

import java.util.Random;

public class CritComponent extends ItemComponent {

    public static final Identifier ID = new Identifier(Crit.MODID, "component");
    private static final Random rand = new Random();

    private int chance = -1;
    private int damage = -1;

    public CritComponent(ItemStack stack, ComponentKey<?> key) {
        super(stack, key);
    }

    @Override
    public void onTagInvalidated() {
        super.onTagInvalidated();
        chance = damage = -1;
    }

    public int getChance() {
        return chance >= 0 ? chance : getOrGenerate("chance");
    }

    public int getDamage() {
        return damage >= 0 ? damage : getOrGenerate("damage");
    }

    private int randomChance() {
        CritConfig config = Crit.getConfig();
        return randomNumber(config.getTrinketMinCritChance(), config.getTrinketMaxCritChance());
    }

    private int randomDamage() {
        CritConfig config = Crit.getConfig();
        return randomNumber(config.getTrinketMinCritDamage(), config.getTrinketMaxCritDamage());
    }

    // TODO normal distribution
    private int randomNumber(int min, int max) {
        return rand.nextInt(Math.abs(max - min)) + min;
    }

    private int getOrGenerate(String key) {
        if (!hasTag("chance", NbtType.INT) && !hasTag("damage", NbtType.INT))
            generateRandomValues();
        return getInt(key);
    }

    private void generateRandomValues() {
        int randType = rand.nextInt(3);
        chance = randType <= 1 ? randomChance() : 0;
        damage = randType >= 1 ? randomDamage() : 0;
        putInt("chance", chance);
        putInt("damage", damage);
    }
}
