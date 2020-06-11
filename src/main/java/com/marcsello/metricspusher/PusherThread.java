package com.marcsello.metricspusher;

public class PusherThread extends Thread {

    private final PusherThreadConfig config;


    public PusherThread(PusherThreadConfig config) {
        this.config = config;
        this.setPriority(Thread.MIN_PRIORITY);
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


            // TODO: collect and send metrics

        }


    }


}
