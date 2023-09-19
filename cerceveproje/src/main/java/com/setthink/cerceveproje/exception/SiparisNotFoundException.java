package com.setthink.cerceveproje.exception;

import java.util.List;

public class SiparisNotFoundException extends RuntimeException{
    public SiparisNotFoundException(Long id) {
        super("Siparis not found with id: " + id + ".");
    }
    public SiparisNotFoundException(List<String> siparisKodu) {
        super("Siparis not found with siparisKodu: " + siparisKodu + ".");
    }
}
