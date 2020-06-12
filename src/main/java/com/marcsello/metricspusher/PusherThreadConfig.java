package com.marcsello.metricspusher;

import org.bukkit.configuration.file.FileConfiguration;

public class PusherThreadConfig {

    private final boolean collectTPS;
    private final boolean collectPlayers;
    private final boolean collectRAM;
    private final boolean collectEntities;

    private final long pushInterval;
    private final String pushTarget;
    private final String authToken;
    private final boolean logFailure;

    public static PusherThreadConfig fromConfig(FileConfiguration config) {

        return new PusherThreadConfig(
                config.getBoolean("metrics.tps"),
                config.getBoolean("metrics.players"),
                config.getBoolean("metrics.ram"),
                config.getBoolean("metrics.entities"),

                config.getInt("push-interval"),
                config.getString("push-target"),
                config.getString("auth-token"),
                config.getBoolean("log-failed-attempts")
        );
    }

    public PusherThreadConfig(boolean collectTPS, boolean collectPlayers, boolean collectRAM, boolean collectEntities, long pushInterval, String pushTarget, String authToken, boolean logFailure) {
        this.collectTPS = collectTPS;
        this.collectPlayers = collectPlayers;
        this.collectRAM = collectRAM;
        this.collectEntities = collectEntities;

        this.pushInterval = pushInterval;
        this.pushTarget = pushTarget;
        this.authToken = authToken;
        this.logFailure = logFailure;
    }

    // enabled metrics stuff

    public boolean isCollectTPS() {
        return collectTPS;
    }

    public boolean isCollectPlayers() {
        return collectPlayers;
    }

    public boolean isCollectRAM() {
        return collectRAM;
    }

    public boolean isCollectEntities() {
        return collectEntities;
    }

    // Generic stuff

    public long getPushInterval() {
        return pushInterval;
    }

    public String getPushTarget() {
        return pushTarget;
    }

    public String getAuthToken() {
        return authToken;
    }

    public boolean isLogFailure() {
        return logFailure;
    }
}
