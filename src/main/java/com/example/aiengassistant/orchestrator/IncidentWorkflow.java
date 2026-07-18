package com.example.aiengassistant.orchestrator;

import com.example.aiengassistant.agent.LogAnalysisAgent;
import com.example.aiengassistant.agent.MetricAnalysisAgent;
import com.example.aiengassistant.agent.RCAAgent;
import com.example.aiengassistant.agent.RecommendationAgent;
import com.example.aiengassistant.agent.RetrievalAgent;
import com.example.aiengassistant.agent.ReviewerAgent;
import com.example.aiengassistant.model.AnalysisReport;
import org.springframework.stereotype.Service;

@Service
public class IncidentWorkflow {

    private final RetrievalAgent retrievalAgent;
    private final LogAnalysisAgent logAnalysisAgent;
    private final MetricAnalysisAgent metricAnalysisAgent;
    private final RCAAgent rcaAgent;
    private final RecommendationAgent recommendationAgent;
    private final ReviewerAgent reviewerAgent;

    public IncidentWorkflow(
            RetrievalAgent retrievalAgent,
            LogAnalysisAgent logAnalysisAgent,
            MetricAnalysisAgent metricAnalysisAgent,
            RCAAgent rcaAgent,
            RecommendationAgent recommendationAgent,
            ReviewerAgent reviewerAgent) {
        this.retrievalAgent = retrievalAgent;
        this.logAnalysisAgent = logAnalysisAgent;
        this.metricAnalysisAgent = metricAnalysisAgent;
        this.rcaAgent = rcaAgent;
        this.recommendationAgent = recommendationAgent;
        this.reviewerAgent = reviewerAgent;
    }

    public AnalysisReport analyze(String question) {
        String deploymentSummary = retrievalAgent.retrieveContext(question);
        String logSummary = logAnalysisAgent.analyzeLogs("checkout-service");
        String metricSummary = metricAnalysisAgent.analyzeMetrics("checkout-service");
        String rootCause = rcaAgent.analyzeRootCause(deploymentSummary, logSummary, metricSummary);
        String recommendations = recommendationAgent.generateRecommendations(rootCause);

        String reportText = deploymentSummary + " " + logSummary + " " + metricSummary
                + " " + rootCause + " " + recommendations;
        String confidence = reviewerAgent.review(reportText);

        AnalysisReport report = new AnalysisReport();
        report.setQuestion(question);
        report.setDeploymentSummary(deploymentSummary);
        report.setLogSummary(logSummary);
        report.setMetricSummary(metricSummary);
        report.setRootCause(rootCause);
        report.setRecommendations(recommendations);
        report.setConfidence(confidence);
        return report;
    }
}
