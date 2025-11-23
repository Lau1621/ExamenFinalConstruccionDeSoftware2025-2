package com.tdea.demo.adapters.rest;

import com.tdea.demo.adapters.rest.request.MessageRequest;
import com.tdea.demo.adapters.rest.request.ReconstructRequest;
import com.tdea.demo.adapters.rest.response.MessageResponse;
import com.tdea.demo.adapters.utils.MessageValidator;
import com.tdea.demo.adapters.utils.ValidationException;
import com.tdea.demo.domain.models.Message;
import com.tdea.demo.ports.MessagePort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/mensajes")
public class MessageController {

    private final MessagePort messagePort;
    private final MessageValidator messageValidator;

    public MessageController(MessagePort messagePort, MessageValidator messageValidator) {
        this.messagePort = messagePort;
        this.messageValidator = messageValidator;
    }

    @PostMapping
    public ResponseEntity<MessageResponse> createMessage(@RequestBody MessageRequest request) 
            throws ValidationException {
        
        messageValidator.pillarIdValidator(request.getPilarId());
        messageValidator.contentFragmentedValidator(request.getContenidoFragmentado());

        Message message = messagePort.createMessage(
                request.getPilarId(),
                request.getContenidoFragmentado()
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(MessageResponse.fromDomain(message));
    }

    @PutMapping("/{id}/reconstruir")
    public ResponseEntity<MessageResponse> reconstructMessage(
            @PathVariable Long id, 
            @RequestBody ReconstructRequest request) throws ValidationException {
        
        messageValidator.messageIdValidator(id);
        messageValidator.contentRebuiltValidator(request.getContenidoReconstruido());

        Optional<Message> message = messagePort.reconstructMessage(id, request.getContenidoReconstruido());

        return ResponseEntity.ok(MessageResponse.fromDomain(message.get()));
    }
}
