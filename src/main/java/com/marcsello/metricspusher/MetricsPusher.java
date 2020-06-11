package com.marcsello.metricspusher;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

public final class MetricsPusher extends JavaPlugin {

    private PusherThread pusherThread;

    @Override
    public void onEnable() {

        saveDefaultConfig();

        pusherThread = new PusherThread(PusherThreadConfig.fromConfig(getConfig()));
        pusherThread.start();

    }

    @Override
    public void onDisable() {

        pusherThread.interrupt();

    }
}
