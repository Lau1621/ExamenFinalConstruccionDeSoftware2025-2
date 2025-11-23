package com.tdea.demo.adapters.rest.response;

import com.tdea.demo.domain.models.Message;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class MessageResponse {
    private long id;
    private Long pilarId;
    private String contenidoFragmentado;
    private String contenidoReconstruido;
    private LocalDateTime timestamp;

    public static MessageResponse fromDomain(Message message) {
        MessageResponse response = new MessageResponse();
        response.setId(message.getId());
        response.setPilarId(message.getPillar().getId());
        response.setContenidoFragmentado(message.getContentFragmented());
        response.setContenidoReconstruido(message.getContentRebuilt());
        response.setTimestamp(message.getTimestamp());
        return response;
    }
}

