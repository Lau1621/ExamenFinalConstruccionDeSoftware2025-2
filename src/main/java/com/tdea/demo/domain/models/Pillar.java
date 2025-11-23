package com.tdea.demo.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Pillar {
    private long id;
    private String name;
    private int posX;
    private int posY;
    private String state;

    public Pillar(long id, String name, int posX, int posY, String state) {
        this.id = id;
        this.name = name;
        this.posX = posX;
        this.posY = posY;
        this.state = state;
    }
}
