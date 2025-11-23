package com.tdea.demo.adapters.rest.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdatePositionResponse {
    private String mensaje;
    private PillarResponse pilar;

    public UpdatePositionResponse(String mensaje, PillarResponse pilar) {
        this.mensaje = mensaje;
        this.pilar = pilar;
    }
}

