package com.vinojinip.ai_knowledge_assistant.document.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record DocumentChatRequest(

        @NotNull(message = "Document id is required")
        UUID documentId,

        @NotBlank(message = "Question is required")
        @Size(max = 5000, message = "Question must not exceed 5000 characters")
        String question
) {
}