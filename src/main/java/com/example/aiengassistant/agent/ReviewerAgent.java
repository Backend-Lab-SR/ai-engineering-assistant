package com.example.aiengassistant.agent;

import org.springframework.stereotype.Component;

@Component
public class ReviewerAgent {

    public String review(String analysisReport) {
        boolean hasDeployment = analysisReport.contains("deployment");
        boolean hasLogs = analysisReport.contains("ERROR") || analysisReport.contains("database timeout");
        boolean hasMetrics = analysisReport.contains("Latency");
        boolean hasRootCause = analysisReport.contains("suspected") || analysisReport.contains("undetermined");
        boolean hasRecommendations = analysisReport.contains("1.");

        if (hasDeployment && hasLogs && hasMetrics && hasRootCause && hasRecommendations) {
            return "Confidence: High";
        }

        if (hasRootCause && hasRecommendations) {
            return "Confidence: Medium";
        }

        return "Confidence: Low";
    }
}
