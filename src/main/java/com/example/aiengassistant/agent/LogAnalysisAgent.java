package com.example.aiengassistant.agent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class LogAnalysisAgent {

    public String analyzeLogs(String serviceName) {
        Path logFile = Path.of("sample-data", "logs", serviceName + ".log");

        try {
            List<String> lines = Files.readAllLines(logFile);

            long errorCount = lines.stream()
                    .filter(line -> line.contains("ERROR"))
                    .count();

            long exceptionCount = lines.stream()
                    .filter(line -> line.contains("Exception"))
                    .count();

            long databaseTimeoutErrors = lines.stream()
                    .filter(line -> line.contains("ERROR") && line.contains("database timeout"))
                    .count();

            return databaseTimeoutErrors + " database timeout errors detected. "
                    + errorCount + " ERROR lines and "
                    + exceptionCount + " exception lines found.";
        } catch (IOException e) {
            throw new RuntimeException("Failed to read log file for " + serviceName, e);
        }
    }
}
