package com.example.aiengassistant.orchestrator;

import com.example.aiengassistant.agent.LogAnalysisAgent;
import com.example.aiengassistant.agent.RetrievalAgent;
import org.springframework.stereotype.Service;

@Service
public class IncidentWorkflow {

    private final RetrievalAgent retrievalAgent;
    private final LogAnalysisAgent logAnalysisAgent;

    public IncidentWorkflow(RetrievalAgent retrievalAgent, LogAnalysisAgent logAnalysisAgent) {
        this.retrievalAgent = retrievalAgent;
        this.logAnalysisAgent = logAnalysisAgent;
    }

    public String analyze(String question) {
        String context = retrievalAgent.retrieveContext(question);
        String logSummary = logAnalysisAgent.analyzeLogs("checkout-service");
        return context + " " + logSummary;
    }
}
