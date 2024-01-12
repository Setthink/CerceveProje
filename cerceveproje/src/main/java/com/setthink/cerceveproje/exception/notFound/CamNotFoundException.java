package com.setthink.cerceveproje.exception.notFound;

public class CamNotFoundException extends EntityNotFoundException {

    public CamNotFoundException(Long id) {
        super("Cam", id);
    }

    public CamNotFoundException(String camKodu) {
        super("Cam", camKodu);
    }
}
