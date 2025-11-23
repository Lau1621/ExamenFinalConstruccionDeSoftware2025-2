package com.tdea.demo.adapters.utils;

import org.springframework.stereotype.Component;

@Component
public class PillarValidator extends SimpleValidator {

    public Long pillarIdValidator(Long value) throws ValidationException {
        return longValidator(value, "ID del pilar");
    }

    public String pillarNameValidator(String value) throws ValidationException {
        return stringValidator(value, "nombre del pilar");
    }

    public Integer posXValidator(Integer value) throws ValidationException {
        return intValidator(value, "posición X");
    }

    public Integer posYValidator(Integer value) throws ValidationException {
        return intValidator(value, "posición Y");
    }

    public String stateValidator(String value) throws ValidationException {
        String state = stringValidator(value, "estado del pilar");
        
        if (!state.equalsIgnoreCase("Combatiendo") && 
            !state.equalsIgnoreCase("Explorando") && 
            !state.equalsIgnoreCase("Herido") && 
            !state.equalsIgnoreCase("Descansando")) {
            throw new ValidationException("Estado debe ser: Combatiendo, Explorando, Herido o Descansando");
        }
        
        return state;
    }
}

