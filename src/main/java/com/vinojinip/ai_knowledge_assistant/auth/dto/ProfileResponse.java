package com.vinojinip.ai_knowledge_assistant.auth.dto;

import java.time.Instant;
import java.util.UUID;

public record ProfileResponse(
        UUID id,
        String email,
        String firstName,
        String lastName,
        Instant createdAt
) {
}