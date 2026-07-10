package com.vinojinip.ai_knowledge_assistant.chat.controller;

import com.vinojinip.ai_knowledge_assistant.chat.dto.ConversationResponse;
import com.vinojinip.ai_knowledge_assistant.chat.dto.CreateConversationRequest;
import com.vinojinip.ai_knowledge_assistant.chat.dto.UpdateConversationRequest;
import com.vinojinip.ai_knowledge_assistant.chat.entity.Conversation;
import com.vinojinip.ai_knowledge_assistant.chat.mapper.ConversationMapper;
import com.vinojinip.ai_knowledge_assistant.chat.service.ConversationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/conversations")
public class ConversationController {

    private final ConversationMapper conversationMapper;
    private final ConversationService conversationService;

    @PostMapping
    public ResponseEntity<ConversationResponse> create(
            @Valid @RequestBody CreateConversationRequest request
    ) {
        Conversation conversation = conversationMapper.toEntity(request);

        Conversation savedConversation =
                conversationService.create(conversation);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(conversationMapper.toResponse(savedConversation));
    }

    @GetMapping
    public ResponseEntity<List<ConversationResponse>> getMyConversations() {
        List<ConversationResponse> response =
                conversationService.getMyConversations()
                        .stream()
                        .map(conversationMapper::toResponse)
                        .toList();

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ConversationResponse> rename(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateConversationRequest request
    ) {
        Conversation updatedConversation =
                conversationService.rename(id, request.title());

        return ResponseEntity.ok(
                conversationMapper.toResponse(updatedConversation)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConversationResponse> getById(
            @PathVariable UUID id
    ) {
        Conversation conversation = conversationService.getById(id);

        return ResponseEntity.ok(
                conversationMapper.toResponse(conversation)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable UUID id
    ) {
        conversationService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
