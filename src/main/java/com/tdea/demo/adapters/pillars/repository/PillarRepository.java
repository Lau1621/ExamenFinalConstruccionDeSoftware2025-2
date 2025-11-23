package com.tdea.demo.adapters.pillars.repository;

import com.tdea.demo.adapters.pillars.entity.PillarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PillarRepository extends JpaRepository<PillarEntity, Long> {
}

