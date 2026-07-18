package com.example.aiengassistant.model;

public class AnalysisReport {

    private String question;
    private String deploymentSummary;
    private String logSummary;
    private String metricSummary;
    private String rootCause;
    private String recommendations;
    private String confidence;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getDeploymentSummary() {
        return deploymentSummary;
    }

    public void setDeploymentSummary(String deploymentSummary) {
        this.deploymentSummary = deploymentSummary;
    }

    public String getLogSummary() {
        return logSummary;
    }

    public void setLogSummary(String logSummary) {
        this.logSummary = logSummary;
    }

    public String getMetricSummary() {
        return metricSummary;
    }

    public void setMetricSummary(String metricSummary) {
        this.metricSummary = metricSummary;
    }

    public String getRootCause() {
        return rootCause;
    }

    public void setRootCause(String rootCause) {
        this.rootCause = rootCause;
    }

    public String getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(String recommendations) {
        this.recommendations = recommendations;
    }

    public String getConfidence() {
        return confidence;
    }

    public void setConfidence(String confidence) {
        this.confidence = confidence;
    }
}
