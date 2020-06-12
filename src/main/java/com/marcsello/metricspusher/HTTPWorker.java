package com.marcsello.metricspusher;



import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.http.json.JsonHttpContent;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.bukkit.Bukkit;

import java.io.IOException;
import java.util.Map;

public class HTTPWorker {


    private static final JsonFactory JSON_FACTORY = new JacksonFactory();

    private final GenericUrl target_url;
    private final HttpRequestFactory requestFactory;
    private final boolean logFailure;


    public HTTPWorker(String target_url, boolean logFailure) {
        this.target_url = new GenericUrl(target_url);
        requestFactory = (new NetHttpTransport()).createRequestFactory();

        this.logFailure = logFailure;
    }

    public void sendMetrics(Map<String, Map<String, Object>> metrics) {

        HttpContent content = new JsonHttpContent(JSON_FACTORY, metrics);

        try {
            requestFactory.buildPostRequest(target_url, content).execute();
        } catch (IOException e) {
            if (logFailure) {
                Bukkit.getLogger().warning("Failed to push metrics: " + e.getMessage());
            }
            // Otherwise fail silently
        }

    }

}
