package com.marcsello.metricspusher;



import com.google.api.client.http.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.http.json.JsonHttpContent;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.bukkit.Bukkit;

import java.io.IOException;
import java.util.Map;

public class HTTPWorker {


    private static final JsonFactory JSON_FACTORY = new JacksonFactory();

    private final GenericUrl targetUrl;
    private final HttpRequestFactory requestFactory;
    private final boolean logFailure;
    private final String authHeader;


    public HTTPWorker(String targetUrl, String authToken, boolean logFailure) {
        this.targetUrl = new GenericUrl(targetUrl);
        requestFactory = (new NetHttpTransport()).createRequestFactory();

        this.authHeader = authToken != null ? "Bearer " + authToken : null;
        this.logFailure = logFailure;
    }

    public void sendMetrics(Map<String, Map<String, Object>> metrics) {

        HttpContent content = new JsonHttpContent(JSON_FACTORY, metrics);

        try {
            HttpRequest request = requestFactory.buildPostRequest(targetUrl, content);
            request.getHeaders().setAuthorization(authHeader);
            request.execute();
        } catch (IOException e) { // This catches all error types, including connection failure and bad reponse codes.
            if (logFailure) {
                Bukkit.getLogger().warning("Failed to push metrics: " + e.getMessage());
            }
            // Otherwise fail silently
        }

    }

}
