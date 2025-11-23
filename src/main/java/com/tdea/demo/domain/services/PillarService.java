package com.tdea.demo.domain.services;

import com.tdea.demo.adapters.pillars.PillarAdapter;
import com.tdea.demo.adapters.utils.NotFoundException;
import com.tdea.demo.domain.models.Pillar;
import com.tdea.demo.ports.PillarPort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PillarService implements PillarPort {

    private final PillarAdapter pillarAdapter;

    public PillarService(PillarAdapter pillarAdapter) {
        this.pillarAdapter = pillarAdapter;
    }

    @Override
    public Optional<Pillar> findById(Long id) {
        return pillarAdapter.findById(id);
    }

    @Override
    public List<Pillar> findAll() {
        return pillarAdapter.findAll();
    }

    @Override
    public Pillar save(Pillar pillar) {
        return pillarAdapter.save(pillar);
    }

    @Override
    public void deleteById(Long id) {
        pillarAdapter.deleteById(id);
    }

    @Override
    public Pillar updatePillarPosition(Long pilarId, int posX, int posY, String estado) {
        Pillar pillar = pillarAdapter.findById(pilarId)
                .orElseThrow(() -> new NotFoundException("No existe un Pilar con ID: " + pilarId));
        
        pillar.setPosX(posX);
        pillar.setPosY(posY);
        pillar.setState(estado);
        
        return pillarAdapter.save(pillar);
    }
}
