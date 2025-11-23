package com.tdea.demo.adapters.rest;

import com.tdea.demo.ports.IntelligencePort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/inteligencia")
public class IntelligenceController {

    private final IntelligencePort intelligencePort;

    public IntelligenceController(IntelligencePort intelligencePort) {
        this.intelligencePort = intelligencePort;
    }

    @GetMapping("/triangulacion")
    public ResponseEntity<Map<String, Object>> getTriangulation() {
        Map<String, Object> triangulation = intelligencePort.calculateTriangulation();
        return ResponseEntity.ok(triangulation);
    }
}
