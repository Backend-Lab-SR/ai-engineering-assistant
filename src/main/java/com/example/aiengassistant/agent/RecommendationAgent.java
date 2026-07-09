package com.example.aiengassistant.agent;

import org.springframework.stereotype.Component;

@Component
public class RecommendationAgent {

    public String generateRecommendations(String rootCause) {
        if (rootCause.contains("Database connection pool issue suspected")) {
            return "1. Verify connection pool settings. "
                    + "2. Rollback deployment. "
                    + "3. Monitor latency.";
        }

        return "1. Review recent changes. "
                + "2. Check service health. "
                + "3. Continue monitoring.";
    }
}
