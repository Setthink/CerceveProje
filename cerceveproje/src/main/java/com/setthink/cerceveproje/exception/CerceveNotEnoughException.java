package com.setthink.cerceveproje.exception;

public class CerceveNotEnoughException extends RuntimeException{

    public CerceveNotEnoughException(String cerceveKodu) {
        super("Kodu " + cerceveKodu + " olan çerçeve stokta yeterli değil.");
    }

}
