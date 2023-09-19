package com.setthink.cerceveproje.exception;

public class AynaNotFoundException extends RuntimeException{
    public AynaNotFoundException(Long id) {
        super("Ayna not found with id: " + id + ".");
    }

    public AynaNotFoundException(String aynaKodu) {
        super("Ayna not found with aynaKodu: " + aynaKodu + ".");
    }

}
