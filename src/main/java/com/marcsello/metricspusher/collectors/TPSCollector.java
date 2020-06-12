package com.marcsello.metricspusher.collectors;

import org.bukkit.Bukkit;
import org.bukkit.Server;

import java.util.HashMap;
import java.util.Map;

public class TPSCollector implements IMetricsCollector {

    private final Server server = Bukkit.getServer(); // Keep a reference for faster access

    @Override
    public Map<String, Object> collect() {
        // Get the TPS metrics
        double[] tps = server.getTPS();

        // Compose the Map
        Map<String, Object> r = new HashMap<>();
        r.put("1m", tps[0]);
        r.put("5m", tps[1]);
        r.put("15m", tps[2]);

        return r;
    }

    @Override
    public String getKey() {
        return "tps";
    }
}
