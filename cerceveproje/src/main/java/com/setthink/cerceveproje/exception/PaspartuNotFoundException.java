package com.setthink.cerceveproje.exception;

public class PaspartuNotFoundException extends RuntimeException{
    public PaspartuNotFoundException(Long id) {
        super("Paspartu not found with id: " + id + ".");
    }

    public PaspartuNotFoundException(String paspartuKodu) {
        super("Paspartu not found with paspartuKodu: " + paspartuKodu + ".");
    }
}
