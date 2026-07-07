package com.example.aiengassistant.orchestrator;

import com.example.aiengassistant.agent.RetrievalAgent;
import org.springframework.stereotype.Service;

@Service
public class IncidentWorkflow {

    private final RetrievalAgent retrievalAgent;

    public IncidentWorkflow(RetrievalAgent retrievalAgent) {
        this.retrievalAgent = retrievalAgent;
    }

    public String analyze(String question) {
        return retrievalAgent.retrieveContext(question);
    }
}
