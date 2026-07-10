package com.vinojinip.ai_knowledge_assistant.chat.mapper;

import com.vinojinip.ai_knowledge_assistant.chat.dto.ConversationResponse;
import com.vinojinip.ai_knowledge_assistant.chat.dto.CreateConversationRequest;
import com.vinojinip.ai_knowledge_assistant.chat.entity.Conversation;
import com.vinojinip.ai_knowledge_assistant.common.config.MapperConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfiguration.class)
public interface ConversationMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "messages", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Conversation toEntity(CreateConversationRequest request);

    ConversationResponse toResponse(Conversation conversation);
}
