package com.tdea.demo.adapters.rest.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PillarRequest {
    private Long pilarId;
    private Integer posX;
    private Integer posY;
    private String estado;
}
