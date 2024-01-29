package com.setthink.cerceveproje.exception.notFound;


public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String entityType, Long id) {
        super(entityType + " not found with ID: " + id + ".");
    }

    public EntityNotFoundException(String entityType, String entityKodu) {
        super(entityType + " not found with " + entityType.toLowerCase() + "Kodu: " + entityKodu + ".");
    }

    public EntityNotFoundException(String entityType) {
        super(entityType + " can not be null.");
    }
}
