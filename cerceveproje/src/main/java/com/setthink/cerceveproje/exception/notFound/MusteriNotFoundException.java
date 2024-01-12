package com.setthink.cerceveproje.exception.notFound;

public class MusteriNotFoundException extends EntityNotFoundException {

    public MusteriNotFoundException(Long id) {
        super("Musteri", id);
    }

}
