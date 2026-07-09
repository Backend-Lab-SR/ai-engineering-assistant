package com.example.aiengassistant.agent;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class MetricAnalysisAgent {

    private final ObjectMapper objectMapper;

    public MetricAnalysisAgent(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public String analyzeMetrics(String serviceName) {
        Path metricsFile = Path.of("sample-data", "metrics", serviceName + ".json");

        try {
            JsonNode metrics = objectMapper.readTree(metricsFile.toFile());
            List<String> summaries = new ArrayList<>();

            summaries.add(buildSummary("Latency", metrics.get("latency")));
            summaries.add(buildSummary("Error rate", metrics.get("errorRate")));
            summaries.add(buildSummary("CPU", metrics.get("cpu")));

            return String.join(" ", summaries);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read metrics for " + serviceName, e);
        }
    }

    private String buildSummary(String metricName, JsonNode metric) {
        double baseline = metric.get("baseline").asDouble();
        double current = metric.get("current").asDouble();
        String unit = metric.get("unit").asText();

        String direction = current > baseline ? "increased" : "decreased";
        return metricName + " " + direction + " from "
                + formatValue(baseline, unit) + " to "
                + formatValue(current, unit) + ".";
    }

    private String formatValue(double value, String unit) {
        if (value == (long) value) {
            return (long) value + unit;
        }
        return value + unit;
    }
}
