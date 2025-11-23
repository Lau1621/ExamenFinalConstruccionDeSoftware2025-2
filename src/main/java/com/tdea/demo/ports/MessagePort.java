package com.tdea.demo.ports;

import com.tdea.demo.domain.models.Message;

import java.util.List;
import java.util.Optional;

public interface MessagePort {
    Optional<Message> findById(Long id);
    List<Message> findAll();
    Message save(Message message);
    void deleteById(Long id);
    Message createMessage(Long pilarId, String contenidoFragmentado);
    Optional<Message> reconstructMessage(Long messageId, String contenidoReconstruido);
}

