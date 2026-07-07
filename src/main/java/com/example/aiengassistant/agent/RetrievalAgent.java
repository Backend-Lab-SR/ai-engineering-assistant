package com.example.aiengassistant.agent;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class RetrievalAgent {

    private static final Path DEPLOYMENTS_FILE = Path.of("sample-data", "deployments.json");

    private final ObjectMapper objectMapper;

    public RetrievalAgent(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public String retrieveContext(String question) {
        try {
            List<Map<String, String>> deployments = objectMapper.readValue(
                    DEPLOYMENTS_FILE.toFile(),
                    new TypeReference<>() {}
            );

            return deployments.stream()
                    .map(deployment -> "Recent deployment detected for "
                            + deployment.get("service")
                            + " version "
                            + deployment.get("version")
                            + ".")
                    .collect(Collectors.joining(" "));
        } catch (IOException e) {
            throw new RuntimeException("Failed to read deployment data", e);
        }
    }
}
