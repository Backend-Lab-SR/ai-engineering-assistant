package com.example.aiengassistant.orchestrator;

import com.example.aiengassistant.agent.LogAnalysisAgent;
import com.example.aiengassistant.agent.MetricAnalysisAgent;
import com.example.aiengassistant.agent.RCAAgent;
import com.example.aiengassistant.agent.RetrievalAgent;
import org.springframework.stereotype.Service;

@Service
public class IncidentWorkflow {

    private final RetrievalAgent retrievalAgent;
    private final LogAnalysisAgent logAnalysisAgent;
    private final MetricAnalysisAgent metricAnalysisAgent;
    private final RCAAgent rcaAgent;

    public IncidentWorkflow(
            RetrievalAgent retrievalAgent,
            LogAnalysisAgent logAnalysisAgent,
            MetricAnalysisAgent metricAnalysisAgent,
            RCAAgent rcaAgent) {
        this.retrievalAgent = retrievalAgent;
        this.logAnalysisAgent = logAnalysisAgent;
        this.metricAnalysisAgent = metricAnalysisAgent;
        this.rcaAgent = rcaAgent;
    }

    public String analyze(String question) {
        String deploymentSummary = retrievalAgent.retrieveContext(question);
        String logSummary = logAnalysisAgent.analyzeLogs("checkout-service");
        String metricSummary = metricAnalysisAgent.analyzeMetrics("checkout-service");
        String rootCause = rcaAgent.analyzeRootCause(deploymentSummary, logSummary, metricSummary);
        return deploymentSummary + " " + logSummary + " " + metricSummary + " " + rootCause;
    }
}
