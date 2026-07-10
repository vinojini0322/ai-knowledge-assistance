package com.vinojinip.ai_knowledge_assistant.chat.service;

import com.vinojinip.ai_knowledge_assistant.auth.entity.User;
import com.vinojinip.ai_knowledge_assistant.chat.entity.Conversation;
import com.vinojinip.ai_knowledge_assistant.chat.repository.ConversationRepository;
import com.vinojinip.ai_knowledge_assistant.common.exception.ConversationNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ConversationService {

    private final ConversationRepository conversationRepository;
    private final CurrentUserService currentUserService;

    @Transactional
    public Conversation create(Conversation conversation) {
        User currentUser = currentUserService.getCurrentUser();

        if (conversation.getTitle() != null) {
            conversation.setTitle(conversation.getTitle().trim());

            if (conversation.getTitle().isEmpty()) {
                conversation.setTitle(null);
            }
        }

        conversation.setUser(currentUser);

        return conversationRepository.save(conversation);
    }

    public List<Conversation> getMyConversations() {
        UUID userId = currentUserService.getCurrentUser().getId();
        return conversationRepository
                .findByUser_IdOrderByUpdatedAtDesc(userId);
    }

    public Conversation getById(UUID conversationId) {
        UUID userId = currentUserService.getCurrentUser().getId();
        return conversationRepository
                .findByIdAndUser_Id(conversationId, userId)
                .orElseThrow(
                        () -> new ConversationNotFoundException(conversationId)
                );
    }

    @Transactional
    public Conversation rename(UUID conversationId, String title) {
        Conversation conversation = getById(conversationId);
        conversation.setTitle(title);
        return conversation;
    }

    @Transactional
    public void delete(UUID conversationId) {
        Conversation conversation = getById(conversationId);
        conversationRepository.delete(conversation);
    }
}