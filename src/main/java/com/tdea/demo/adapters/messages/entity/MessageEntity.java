package com.tdea.demo.adapters.messages.entity;

import com.tdea.demo.adapters.pillars.entity.PillarEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "messages")
public class MessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @JoinColumn(name = "pillar_id")
    @ManyToOne
    private PillarEntity pillar;
    @Column(name = "content_fragmented")
    private String contentFragmented;
    @Column(name = "content_rebuilt")
    private String contentRebuilt;
    @Column(name = "timestamp")
    private LocalDateTime timestamp;
}
