package net.xblos.crit.integration;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.xblos.crit.Crit;
import net.xblos.crit.CritConfig;
import net.xblos.entityparticles.particle.EntityTextParticle;
import net.xblos.entityparticles.particle.ParticleRenderer;

public class EntityParticlesIntegration {

    private static final boolean MOD_LOADED = Integration.isModLoaded("net.xblos.entityparticles.EntityParticles");

    private static final Identifier CHANNEL = new Identifier(Crit.CHANNEL + ".entityparticles");
    private static ParticleRenderer renderer;

    public static void register() {
        if (!MOD_LOADED) return;
        renderer = new ParticleRenderer();
        CritConfig config = Crit.getConfig();
        ClientPlayNetworking.registerGlobalReceiver(CHANNEL, (client, h, buf, s) -> {
            if (!config.isParticleEnabled() || client.world == null) return;
            int entityId = buf.readInt();
            Entity target = client.world.getEntityById(entityId);
            if (target != null) {
                client.execute(() ->
                    renderer.add(new EntityTextParticle(
                            target,
                            config.getParticleDuration(),
                            config.getParticleText(),
                            config.getParticleColor(),
                            (config.getParticleSize() * .01f) * .03f
                        )
                    ));
            }
        });
        ClientTickEvents.START_CLIENT_TICK.register(client -> renderer.tick());
        WorldRenderEvents.AFTER_ENTITIES.register(renderer::draw);
    }

    public static void sendRequest(PlayerEntity player, Entity target) {
        if (!MOD_LOADED) return;
        PacketByteBuf packet = PacketByteBufs.create();
        packet.writeInt(target.getId());
        ServerPlayNetworking.send((ServerPlayerEntity) player, CHANNEL, packet);
    }

    public static boolean isModLoaded() {
        return MOD_LOADED;
    }
}
