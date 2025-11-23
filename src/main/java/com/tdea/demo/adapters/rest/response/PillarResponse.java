package com.tdea.demo.adapters.rest.response;

import com.tdea.demo.domain.models.Pillar;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PillarResponse {
    private long id;
    private String nombre;
    private int posX;
    private int posY;
    private String estado;

    public static PillarResponse fromDomain(Pillar pillar) {
        PillarResponse response = new PillarResponse();
        response.setId(pillar.getId());
        response.setNombre(pillar.getName());
        response.setPosX(pillar.getPosX());
        response.setPosY(pillar.getPosY());
        response.setEstado(pillar.getState());
        return response;
    }
}

