package com.example.aiengassistant.agent;

import org.springframework.stereotype.Component;

@Component
public class RetrievalAgent {

    public String retrieveContext(String question) {
        return "Recent deployment detected for checkout-service.";
    }
}
