package net.xblos.crit.integration;

public class TrinketsIntegration {
    private static final boolean MOD_LOADED = Integration.isModLoaded("dev.emi.trinkets.TrinketsMain");

    public static boolean isModLoaded() {
        return MOD_LOADED;
    }
}
