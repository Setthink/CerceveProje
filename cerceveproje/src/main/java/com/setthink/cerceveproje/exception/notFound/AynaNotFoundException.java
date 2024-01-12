package com.setthink.cerceveproje.exception.notFound;

public class AynaNotFoundException extends EntityNotFoundException {

    public AynaNotFoundException(Long id) {
        super("Ayna", id);
    }

    public AynaNotFoundException(String aynaKodu) {
        super("Ayna", aynaKodu);
    }

}
