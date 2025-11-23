package com.tdea.demo.adapters.utils;

import org.springframework.stereotype.Component;

@Component
public class SimpleValidator {

    public String stringValidator(String value, String element) throws ValidationException {
        if (value == null || value.trim().isEmpty()) {
            throw new ValidationException(element + " no tiene un valor válido");
        }
        return value.trim();
    }

    public Long longValidator(Long value, String element) throws ValidationException {
        if (value == null) {
            throw new ValidationException(element + " no tiene un valor válido");
        }
        if (value < 0) {
            throw new ValidationException(element + " debe ser un número positivo");
        }
        return value;
    }

    public Integer intValidator(Integer value, String element) throws ValidationException {
        if (value == null) {
            throw new ValidationException(element + " no tiene un valor válido");
        }
        return value;
    }

    public Float floatValidator(Float value, String element) throws ValidationException {
        if (value == null) {
            throw new ValidationException(element + " no tiene un valor válido");
        }
        if (value < 0) {
            throw new ValidationException(element + " debe ser un número positivo");
        }
        return value;
    }

    public Double doubleValidator(Double value, String element) throws ValidationException {
        if (value == null) {
            throw new ValidationException(element + " no tiene un valor válido");
        }
        if (value < 0) {
            throw new ValidationException(element + " debe ser un número positivo");
        }
        return value;
    }

    public <T> T objectValidator(T value, String element) throws ValidationException {
        if (value == null) {
            throw new ValidationException(element + " no tiene un valor válido");
        }
        return value;
    }
}

