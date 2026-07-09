package com.vinojinip.ai_knowledge_assistant.auth.dto;

import java.util.UUID;

public record UserResponse(UUID id,
                           String email,
                           String firstName,
                           String lastName) {
}
