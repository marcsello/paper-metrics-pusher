package com.marcsello.metricspusher.collectors;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.util.HashMap;
import java.util.Map;

public class RAMCollector implements IMetricsCollector {

    private final MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();

    @Override
    public Map<String, Object> collect() {

        Map<String, Object> r = new HashMap<String, Object>();

        r.put("heap_used", memoryMXBean.getHeapMemoryUsage().getUsed());
        r.put("non_heap_used", memoryMXBean.getNonHeapMemoryUsage().getUsed());

        return r;

    }

    @Override
    public String getKey() {
        return "ram";
    }
}
