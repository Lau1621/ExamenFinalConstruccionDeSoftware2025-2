package com.tdea.demo.adapters.pillars.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "pillars")
public class PillarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "pos_x")
    private int posX;
    @Column(name = "pos_y")
    private int posY;
    @Column(name = "state")
    private String state;
}
