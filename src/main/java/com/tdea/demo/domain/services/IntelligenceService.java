package com.tdea.demo.domain.services;

import com.tdea.demo.adapters.pillars.PillarAdapter;
import com.tdea.demo.domain.models.Pillar;
import com.tdea.demo.ports.IntelligencePort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IntelligenceService implements IntelligencePort {

    private final PillarAdapter pillarAdapter;

    public IntelligenceService(PillarAdapter pillarAdapter) {
        this.pillarAdapter = pillarAdapter;
    }

    @Override
    public Map<String, Object> calculateTriangulation() {
        List<Pillar> pillars = pillarAdapter.findAll();

        if (pillars.isEmpty()) {
            return createTriangulationResponse(0, 0, 0.0, "No hay datos de Pilares disponibles.");
        }

        double avgX = pillars.stream().mapToInt(Pillar::getPosX).average().orElse(0);
        double avgY = pillars.stream().mapToInt(Pillar::getPosY).average().orElse(0);

        int activePillars = (int) pillars.stream()
                .filter(p -> "Combatiendo".equalsIgnoreCase(p.getState()) || 
                            "Explorando".equalsIgnoreCase(p.getState()))
                .count();

        double confidence = Math.min(0.95, (activePillars / (double) pillars.size()) * 0.8 + 0.2);

        String description = confidence > 0.7 
                ? "Probabilidad alta de presencia demoníaca en las coordenadas dadas."
                : "Probabilidad moderada. Se requieren más datos para precisión.";

        return createTriangulationResponse((int) avgX, (int) avgY, confidence, description);
    }

    private Map<String, Object> createTriangulationResponse(int x, int y, double confidence, String description) {
        Map<String, Object> position = new HashMap<>();
        position.put("x", x);
        position.put("y", y);

        Map<String, Object> response = new HashMap<>();
        response.put("posiblePosicionMuzan", position);
        response.put("nivelConfianza", Math.round(confidence * 100) / 100.0);
        response.put("descripcion", description);

        return response;
    }
}
