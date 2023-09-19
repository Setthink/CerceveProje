package com.setthink.cerceveproje.exception;

public class CerceveNotFoundException extends RuntimeException{
    public CerceveNotFoundException(Long id) {
        super("Cerceve not found with id: " + id + ".");
    }

    public CerceveNotFoundException(String cerceveKodu) {
        super("Cerceve not found with cerceveKodu: " + cerceveKodu + ".");
    }
}
