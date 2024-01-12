package com.setthink.cerceveproje.exception.notFound;

public class CerceveNotFoundException extends EntityNotFoundException {
    public CerceveNotFoundException(Long id) {
        super("Cerceve", id);
    }

    public CerceveNotFoundException(String cerceveKodu) {
        super("Cerceve", cerceveKodu);
    }
}
