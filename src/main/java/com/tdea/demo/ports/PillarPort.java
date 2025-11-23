package com.tdea.demo.ports;

import com.tdea.demo.domain.models.Pillar;

import java.util.List;
import java.util.Optional;

public interface PillarPort {
    Optional<Pillar> findById(Long id);
    List<Pillar> findAll();
    Pillar save(Pillar pillar);
    void deleteById(Long id);
    Pillar updatePillarPosition(Long pilarId, int posX, int posY, String estado);
}

