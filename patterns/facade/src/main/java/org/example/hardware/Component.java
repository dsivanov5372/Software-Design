package org.example.hardware;

public interface Component {
    void initialize() throws NoSuchMethodException;

    void shutDown() throws NoSuchMethodException;
}
