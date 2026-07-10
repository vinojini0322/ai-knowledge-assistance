package com.vinojinip.ai_knowledge_assistant.document.mapper;

import com.vinojinip.ai_knowledge_assistant.common.config.MapperConfiguration;
import com.vinojinip.ai_knowledge_assistant.document.dto.DocumentResponse;
import com.vinojinip.ai_knowledge_assistant.document.entity.Document;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfiguration.class)
public interface DocumentMapper {

    DocumentResponse toResponse(Document document);
}