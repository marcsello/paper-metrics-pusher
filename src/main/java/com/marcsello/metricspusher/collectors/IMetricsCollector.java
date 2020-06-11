package com.marcsello.metricspusher.collectors;

import java.util.Map;

public interface IMetricsCollector {

    Map<String, Object> collect();

    String getKey();

}
