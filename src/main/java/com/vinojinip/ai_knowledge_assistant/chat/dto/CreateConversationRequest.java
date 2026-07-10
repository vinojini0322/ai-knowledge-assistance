package com.vinojinip.ai_knowledge_assistant.chat.dto;

import jakarta.validation.constraints.Size;

public record CreateConversationRequest(
        @Size(max = 255, message = "Title must not exceed 255 characters") String title
) {
}
