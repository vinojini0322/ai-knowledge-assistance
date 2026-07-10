package com.vinojinip.ai_knowledge_assistant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AiKnowledgeAssistantApplication {

	public static void main(String[] args) {
		System.out.println("PROFILE = " + System.getenv("SPRING_PROFILES_ACTIVE"));
		System.out.println("JWT = " + System.getenv("JWT_SECRET"));
		SpringApplication.run(AiKnowledgeAssistantApplication.class, args);
	}
}
