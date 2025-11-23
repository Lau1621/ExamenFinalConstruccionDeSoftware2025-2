package com.tdea.demo.domain.services;

import com.tdea.demo.adapters.messages.MessageAdapter;
import com.tdea.demo.adapters.pillars.PillarAdapter;
import com.tdea.demo.adapters.utils.NotFoundException;
import com.tdea.demo.domain.models.Message;
import com.tdea.demo.domain.models.Pillar;
import com.tdea.demo.ports.MessagePort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService implements MessagePort {

    private final MessageAdapter messageAdapter;
    private final PillarAdapter pillarAdapter;

    public MessageService(MessageAdapter messageAdapter, PillarAdapter pillarAdapter) {
        this.messageAdapter = messageAdapter;
        this.pillarAdapter = pillarAdapter;
    }

    @Override
    public Optional<Message> findById(Long id) {
        return messageAdapter.findById(id);
    }

    @Override
    public List<Message> findAll() {
        return messageAdapter.findAll();
    }

    @Override
    public Message save(Message message) {
        return messageAdapter.save(message);
    }

    @Override
    public void deleteById(Long id) {
        messageAdapter.deleteById(id);
    }

    @Override
    public Message createMessage(Long pilarId, String contenidoFragmentado) {
        Pillar pillar = pillarAdapter.findById(pilarId)
                .orElseThrow(() -> new NotFoundException("No existe un Pilar con ID: " + pilarId));

        Message message = new Message();
        message.setPillar(pillar);
        message.setContentFragmented(contenidoFragmentado);
        message.setContentRebuilt(null);
        message.setTimestamp(LocalDateTime.now());

        return messageAdapter.save(message);
    }

    @Override
    public Optional<Message> reconstructMessage(Long messageId, String contenidoReconstruido) {
        Message message = messageAdapter.findById(messageId)
                .orElseThrow(() -> new NotFoundException("No existe un mensaje con ID: " + messageId));

        message.setContentRebuilt(contenidoReconstruido);
        
        Message updatedMessage = messageAdapter.save(message);
        return Optional.of(updatedMessage);
    }
}
