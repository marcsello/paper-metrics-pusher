package com.marcsello.metricspusher;

import com.marcsello.metricspusher.collectors.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class PusherThread extends Thread {

    private final PusherThreadConfig config;
    private final ArrayList<IMetricsCollector> collectors = new ArrayList<>();

    public PusherThread(PusherThreadConfig config) {
        this.config = config;
        this.setPriority(Thread.MIN_PRIORITY);

        // Using a MAP of some sort would be better, but it's a pain to do in Java
        if (config.isCollectTPS()) {
            collectors.add(new TPSCollector());
        }

        if (config.isCollectRAM()) {
            collectors.add(new RAMCollector());
        }

        if (config.isCollectPlayers()) {
            collectors.add(new PlayersCollector());
        }

        if (config.isCollectEntities()) {
            collectors.add(new EntitiesCollector());
        }

    }

    @SuppressWarnings("BusyWait") // This is scheduling not busy waiting
    public void run() {


        while (!Thread.interrupted()) {

            // Go to sleep until we need to push the metrics
            try {
                Thread.sleep(config.getPushInterval());
            } catch (InterruptedException e) {
                break; // The interrupted flag won't be set, so we have to break the loop
            }

            // Collect metrics
            HashMap<String, Map<String, Object>> metrics = new HashMap<>();
            for (IMetricsCollector c : collectors) {
                metrics.put(c.getKey(), c.collect());
            }

            // TODO: Send


        }


    }


}
