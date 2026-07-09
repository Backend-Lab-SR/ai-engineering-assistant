package com.example.aiengassistant.orchestrator;

import com.example.aiengassistant.agent.LogAnalysisAgent;
import com.example.aiengassistant.agent.MetricAnalysisAgent;
import com.example.aiengassistant.agent.RetrievalAgent;
import org.springframework.stereotype.Service;

@Service
public class IncidentWorkflow {

    private final RetrievalAgent retrievalAgent;
    private final LogAnalysisAgent logAnalysisAgent;
    private final MetricAnalysisAgent metricAnalysisAgent;

    public IncidentWorkflow(
            RetrievalAgent retrievalAgent,
            LogAnalysisAgent logAnalysisAgent,
            MetricAnalysisAgent metricAnalysisAgent) {
        this.retrievalAgent = retrievalAgent;
        this.logAnalysisAgent = logAnalysisAgent;
        this.metricAnalysisAgent = metricAnalysisAgent;
    }

    public String analyze(String question) {
        String context = retrievalAgent.retrieveContext(question);
        String logSummary = logAnalysisAgent.analyzeLogs("checkout-service");
        String metricSummary = metricAnalysisAgent.analyzeMetrics("checkout-service");
        return context + " " + logSummary + " " + metricSummary;
    }
}
