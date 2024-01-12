package com.setthink.cerceveproje.exception.notFound;

public class CerceveNotEnoughException extends RuntimeException {

    public CerceveNotEnoughException(String cerceveKodu) {
        super("Kodu " + "'" + cerceveKodu + "'" + " olan çerçeve stokta yeterli değil.");
    }

}
