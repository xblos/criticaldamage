package net.xblos.crit.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Util;

public class Debug {

    public static void msg(PlayerEntity player, Object msg) {
        player.sendSystemMessage(new LiteralText(String.valueOf(msg)), Util.NIL_UUID);
    }

    public static void msg(Object msg) {
        try {
            PlayerEntity player = MinecraftClient.getInstance().player;
            if (player != null) Debug.msg(player, msg);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
