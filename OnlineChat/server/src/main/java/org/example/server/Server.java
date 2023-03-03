package org.example.server;

import org.example.handler.Handler;
import java.io.IOException;
import java.net.ServerSocket;

public class Server {
    private final ServerSocket serverSocket;

    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    public void start() {
        try {
            System.out.println("Server has been started!");
            while (!serverSocket.isClosed()) {
                new Thread(new Handler(serverSocket.accept())).start();
            }
        } catch (IOException ignored) {
            System.err.println("Error occurred during user connection to the server!");
            close();
        }
    }

    public void close() {
        if (serverSocket != null) {
            try {
                serverSocket.close();
            } catch (IOException ignored) {
                System.err.println("Error occurred while closing server!");
            }
        }
    }
}