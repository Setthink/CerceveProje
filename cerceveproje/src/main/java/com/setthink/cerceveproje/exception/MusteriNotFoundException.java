package com.setthink.cerceveproje.exception;

public class MusteriNotFoundException extends RuntimeException{
    public MusteriNotFoundException(Long id) {
        super("Musteri not found with id: " + id + ".");
    }
}
