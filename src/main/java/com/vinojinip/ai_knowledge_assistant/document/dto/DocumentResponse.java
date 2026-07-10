package com.vinojinip.ai_knowledge_assistant.document.dto;

import java.time.Instant;
import java.util.UUID;

public record DocumentResponse(UUID id,
                               String fileName,
                               String fileType,
                               Instant createdAt) {
}
