package com.tdea.demo.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
public class Message {
    private long id;
    private Pillar pillar;
    private String contentFragmented;
    private String contentRebuilt;
    private LocalDateTime timestamp;

    public Message(long id, Pillar pillar, String contentFragmented, String contentRebuilt, LocalDateTime timestamp) {
        this.id = id;
        this.pillar = pillar;
        this.contentFragmented = contentFragmented;
        this.contentRebuilt = contentRebuilt;
        this.timestamp = timestamp;
    }
}
