package com.setthink.cerceveproje.exception.notFound;

public class PaspartuNotFoundException extends EntityNotFoundException {

    public PaspartuNotFoundException(Long id) {
        super("Paspartu", id);
    }

    public PaspartuNotFoundException(String paspartuKodu) {
        super("Paspartu", paspartuKodu);
    }

}
