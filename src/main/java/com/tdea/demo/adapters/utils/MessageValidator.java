package com.tdea.demo.adapters.utils;

import org.springframework.stereotype.Component;

@Component
public class MessageValidator extends SimpleValidator {

    public Long messageIdValidator(Long value) throws ValidationException {
        return longValidator(value, "ID del mensaje");
    }

    public Long pillarIdValidator(Long value) throws ValidationException {
        return longValidator(value, "ID del pilar");
    }

    public String contentFragmentedValidator(String value) throws ValidationException {
        return stringValidator(value, "contenido fragmentado");
    }

    public String contentRebuiltValidator(String value) throws ValidationException {
        return stringValidator(value, "contenido reconstruido");
    }
}

