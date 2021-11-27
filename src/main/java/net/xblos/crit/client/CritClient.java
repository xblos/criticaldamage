package net.xblos.crit.client;

import net.fabricmc.api.ClientModInitializer;
import net.xblos.crit.integration.EntityParticlesIntegration;

public class CritClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityParticlesIntegration.register();
    }
}
