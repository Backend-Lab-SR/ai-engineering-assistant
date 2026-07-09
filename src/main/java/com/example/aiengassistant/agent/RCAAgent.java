package com.example.aiengassistant.agent;

import org.springframework.stereotype.Component;

@Component
public class RCAAgent {

    public String analyzeRootCause(String deploymentSummary, String logSummary, String metricSummary) {
        boolean latencyIncreased = metricSummary.contains("Latency increased");
        boolean databaseTimeouts = logSummary.contains("database timeout");

        if (latencyIncreased && databaseTimeouts) {
            return "Database connection pool issue suspected.";
        }

        return "Root cause undetermined.";
    }
}
