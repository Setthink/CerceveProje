package com.setthink.cerceveproje.exception;

public class CamNotFoundException extends RuntimeException{
    public CamNotFoundException(Long id) {
        super("Cam not found with id: " + id + ".");
    }

    public CamNotFoundException(String camKodu) {
        super("Cam not found with camKodu: " + camKodu + ".");
    }
}
