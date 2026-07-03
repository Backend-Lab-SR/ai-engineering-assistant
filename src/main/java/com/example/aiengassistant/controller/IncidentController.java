package com.example.aiengassistant.controller;

import com.example.aiengassistant.model.IncidentRequest;
import com.example.aiengassistant.orchestrator.IncidentWorkflow;
import java.util.Map;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/incidents")
public class IncidentController {

    private final IncidentWorkflow incidentWorkflow;

    public IncidentController(IncidentWorkflow incidentWorkflow) {
        this.incidentWorkflow = incidentWorkflow;
    }

    @PostMapping("/analyze")
    public Map<String, String> analyzeIncident(@RequestBody IncidentRequest request) {
        return Map.of("message", incidentWorkflow.analyze(request.getQuestion()));
    }
}
