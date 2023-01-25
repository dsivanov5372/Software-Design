package org.example.exception;

public class ClassNotSerializableException extends RuntimeException {
    public ClassNotSerializableException(String message) {
        super(message);
    }
}
