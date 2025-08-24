package com.itc.insurancehelper.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


// OpenAPI configuration class
// that sets up the ChatClient bean
// using the API key from application properties
@Configuration
public class OpenApiConfig {
    @Bean
    public ChatClient chatClient(ChatClient.Builder chatClientBuilder) {
        return chatClientBuilder.build();

    }
  }

