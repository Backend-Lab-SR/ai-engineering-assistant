# AI Engineering Assistant

**A Multi-Agent AI System for Production Incident Investigation and Root Cause Analysis**

## Project Overview

This project is a proof-of-concept demonstrating a multi-agent AI system for engineering incident investigation. Specialized agents collaborate to analyze production incidents, logs, deployment information, and metrics to identify likely root causes and recommend solutions.

## Features

- **Multi-Agent Workflow** — Coordinated agents work together to investigate incidents end to end
- **Incident Investigation** — Structured analysis of production incidents and their context
- **Log Analysis** — Automated review of application and system logs
- **Metric Analysis** — Evaluation of performance and health metrics during incidents
- **Root Cause Analysis** — Identification of probable underlying causes
- **AI-Powered Recommendations** — Actionable suggestions for remediation and prevention
- **Retrieval-Augmented Generation** *(planned)* — Context-aware responses grounded in operational knowledge
- **REST API** — Programmatic access to investigation workflows and results

## High-Level Architecture

```
User Question
        |
        V
Orchestrator
        |
--------------------------------------------
|      |       |        |        |         |
Retrieval Log  Metric   RCA   Recommendation Reviewer
 Agent   Agent Agent    Agent      Agent       Agent
        |
        V
Final Report
```

The orchestrator receives a user question and delegates work to specialized agents. Each agent contributes its analysis, and the workflow culminates in a consolidated final report.

## Technology Stack

- Java 21
- Spring Boot 3
- Maven
- Spring AI *(planned)*
- OpenAI *(planned)*
- REST API

## Project Status

This project is being developed incrementally. Each Git commit introduces one new capability, building toward a complete multi-agent incident investigation system.
