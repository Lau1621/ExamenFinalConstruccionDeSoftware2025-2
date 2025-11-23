package com.tdea.demo.adapters.messages;

import com.tdea.demo.adapters.messages.entity.MessageEntity;
import com.tdea.demo.adapters.messages.repository.MessageRepository;
import com.tdea.demo.adapters.pillars.entity.PillarEntity;
import com.tdea.demo.adapters.pillars.repository.PillarRepository;
import com.tdea.demo.domain.models.Message;
import com.tdea.demo.domain.models.Pillar;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class MessageAdapter {

    private final MessageRepository messageRepository;
    private final PillarRepository pillarRepository;

    public MessageAdapter(MessageRepository messageRepository, PillarRepository pillarRepository) {
        this.messageRepository = messageRepository;
        this.pillarRepository = pillarRepository;
    }

    public Optional<Message> findById(Long id) {
        return messageRepository.findById(id).map(this::toDomain);
    }

    public List<Message> findAll() {
        return messageRepository.findAll()
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    public Message save(Message message) {
        MessageEntity entity = toEntity(message);
        MessageEntity savedEntity = messageRepository.save(entity);
        return toDomain(savedEntity);
    }

    public void deleteById(Long id) {
        messageRepository.deleteById(id);
    }

    private Message toDomain(MessageEntity entity) {
        Pillar pillar = new Pillar(
                entity.getPillar().getId(),
                entity.getPillar().getName(),
                entity.getPillar().getPosX(),
                entity.getPillar().getPosY(),
                entity.getPillar().getState()
        );

        return new Message(
                entity.getId(),
                pillar,
                entity.getContentFragmented(),
                entity.getContentRebuilt(),
                entity.getTimestamp()
        );
    }

    private MessageEntity toEntity(Message message) {
        MessageEntity entity = new MessageEntity();
        entity.setId(message.getId());
        
        PillarEntity pillarEntity = pillarRepository.findById(message.getPillar().getId())
                .orElseThrow(() -> new RuntimeException("Pillar not found"));
        
        entity.setPillar(pillarEntity);
        entity.setContentFragmented(message.getContentFragmented());
        entity.setContentRebuilt(message.getContentRebuilt());
        entity.setTimestamp(message.getTimestamp());
        return entity;
    }
}
