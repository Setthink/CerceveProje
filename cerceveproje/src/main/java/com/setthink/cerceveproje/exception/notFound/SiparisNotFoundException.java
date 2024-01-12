package com.setthink.cerceveproje.exception.notFound;

public class SiparisNotFoundException extends EntityNotFoundException {

    public SiparisNotFoundException(Long id) {
        super("Siparis", id);
    }

}
