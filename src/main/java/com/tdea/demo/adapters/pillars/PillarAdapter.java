package com.tdea.demo.adapters.pillars;

import com.tdea.demo.adapters.pillars.entity.PillarEntity;
import com.tdea.demo.adapters.pillars.repository.PillarRepository;
import com.tdea.demo.domain.models.Pillar;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PillarAdapter {

    private final PillarRepository pillarRepository;

    public PillarAdapter(PillarRepository pillarRepository) {
        this.pillarRepository = pillarRepository;
    }

    public Optional<Pillar> findById(Long id) {
        return pillarRepository.findById(id).map(this::toDomain);
    }

    public List<Pillar> findAll() {
        return pillarRepository.findAll()
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    public Pillar save(Pillar pillar) {
        PillarEntity entity = toEntity(pillar);
        PillarEntity savedEntity = pillarRepository.save(entity);
        return toDomain(savedEntity);
    }

    public void deleteById(Long id) {
        pillarRepository.deleteById(id);
    }

    private Pillar toDomain(PillarEntity entity) {
        return new Pillar(
                entity.getId(),
                entity.getName(),
                entity.getPosX(),
                entity.getPosY(),
                entity.getState()
        );
    }

    private PillarEntity toEntity(Pillar pillar) {
        PillarEntity entity = new PillarEntity();
        entity.setId(pillar.getId());
        entity.setName(pillar.getName());
        entity.setPosX(pillar.getPosX());
        entity.setPosY(pillar.getPosY());
        entity.setState(pillar.getState());
        return entity;
    }
}
