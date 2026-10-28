package com.narkomodz;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public class NarkoModzClient implements ClientModInitializer {

    private static KeyBinding toggleKey;
    private static boolean isEnabled = true;

    @Override
    public void onInitializeClient() {
        // 'R' tuşu ile NarkoModz menüsünü/ESP'sini açıp kapatıyoruz
        toggleKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.narkomodz.toggle",
                InputUtil.Type.KEY_SYM,
                GLFW.GLFW_KEY_R,
                "category.narkomodz.main"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (toggleKey.wasPressed()) {
                isEnabled = !isEnabled;
                if (client.player != null) {
                    client.player.sendMessage(
                            Text.literal("§4[NarkoModz] §bDurum: " + (isEnabled ? "§aAÇIK (ESP AKTİF)" : "§cKAPALI")),
                            true
                    );
                }
            }
        });

        WorldRenderEvents.LAST.register(context -> {
            if (!isEnabled) return;

            MinecraftClient client = MinecraftClient.getInstance();
            if (client.world == null || client.player == null) return;

            for (Entity entity : client.world.getEntities()) {
                if (entity == client.player) continue;

                double distance = client.player.distanceTo(entity);
                if (distance > 32.0) continue; // 32 blok tarama mesafesi

                if (entity instanceof ZombieEntity) {
                    // Zombi Tespiti (Kırmızı Kutu mantığı)
                } else if (entity instanceof AnimalEntity) {
                    // Hayvan Tespiti (Yeşil Kutu mantığı)
                } else if (entity instanceof PlayerEntity) {
                    // Oyuncu Tespiti (Mavi Kutu mantığı)
                }
            }
        });
    }
}
