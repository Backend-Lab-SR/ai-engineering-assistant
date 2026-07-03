package com.example.aiengassistant.orchestrator;

import org.springframework.stereotype.Service;

@Service
public class IncidentWorkflow {

    public String analyze(String question) {
        return "Workflow executed";
    }
}
