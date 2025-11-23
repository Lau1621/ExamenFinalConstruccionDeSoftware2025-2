package com.tdea.demo.adapters.rest;

import com.tdea.demo.adapters.rest.request.PillarRequest;
import com.tdea.demo.adapters.rest.response.PillarResponse;
import com.tdea.demo.adapters.rest.response.UpdatePositionResponse;
import com.tdea.demo.adapters.utils.PillarValidator;
import com.tdea.demo.adapters.utils.ValidationException;
import com.tdea.demo.domain.models.Pillar;
import com.tdea.demo.ports.PillarPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/pilares")
public class PillarController {

    private final PillarPort pillarPort;
    private final PillarValidator pillarValidator;

    public PillarController(PillarPort pillarPort, PillarValidator pillarValidator) {
        this.pillarPort = pillarPort;
        this.pillarValidator = pillarValidator;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PillarResponse> getPillarById(@PathVariable Long id) throws ValidationException {
        pillarValidator.pillarIdValidator(id);
        
        Optional<Pillar> pillar = pillarPort.findById(id);
        
        if (pillar.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        
        return ResponseEntity.ok(PillarResponse.fromDomain(pillar.get()));
    }

    @PostMapping("/actualizar-posicion")
    public ResponseEntity<UpdatePositionResponse> updatePillarPosition(@RequestBody PillarRequest request) 
            throws ValidationException {
        
        pillarValidator.pillarIdValidator(request.getPilarId());
        pillarValidator.posXValidator(request.getPosX());
        pillarValidator.posYValidator(request.getPosY());
        pillarValidator.stateValidator(request.getEstado());

        Pillar updatedPillar = pillarPort.updatePillarPosition(
                request.getPilarId(),
                request.getPosX(),
                request.getPosY(),
                request.getEstado()
        );

        UpdatePositionResponse response = new UpdatePositionResponse(
                "Posición actualizada exitosamente.",
                PillarResponse.fromDomain(updatedPillar)
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
