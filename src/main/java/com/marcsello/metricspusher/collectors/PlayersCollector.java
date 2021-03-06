package com.marcsello.metricspusher.collectors;

import org.bukkit.Bukkit;
import org.bukkit.World;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayersCollector implements IMetricsCollector {
    private final List<World> worlds = Bukkit.getWorlds();

    @Override
    public Map<String, Object> collect() {

        Map<String, Object> r = new HashMap<>();

        for (World w: worlds) {
            r.put(w.getName(), w.getPlayerCount());
        }

        return r;
    }

    @Override
    public String getKey() {
        return "players";
    }
}
