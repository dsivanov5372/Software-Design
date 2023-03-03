package org.example.handler;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Handler implements Runnable {
    private static final List<Handler> youngHandlers = new ArrayList<>();
    private static final List<Handler> adultHandlers = new ArrayList<>();
    private static final Set<String> userNames = new HashSet<>();
    private BufferedWriter writer;
    private BufferedReader reader;
    private Socket userSocket;
    private String userName;
    boolean isAdult = false;

    public Handler(Socket userSocket) {
        try {
            this.userSocket = userSocket;
            this.writer = new BufferedWriter(new OutputStreamWriter(userSocket.getOutputStream()));
            this.reader = new BufferedReader(new InputStreamReader(userSocket.getInputStream()));
            this.userName = reader.readLine();

            if (userNames.contains(userName)) {
                writer.write("User with this name already connected!");
                closeSocket();
            }
            userNames.add(this.userName);

            int age = Integer.parseInt(reader.readLine());
            if (age >= 18) {
                isAdult = true;
                adultHandlers.add(this);
            } else {
                youngHandlers.add(this);
            }

            String message = "User with name " + userName + " is connected to the server!";
            sendMessage("TO ALL: " + message);
            System.out.println(message);
        } catch (IOException ignored) {
            System.err.println("Error occurred during establishing connection with user!");
            closeSocket();
        }
    }

    @Override
    public void run() {
        while (userSocket.isConnected()) {
            try {
                String message = reader.readLine();
                sendMessage(this.userName + ": " + message);
            } catch (IOException ignored) {
                closeSocket();
            }
        }
    }

    private void sendMessage(String message) {
        List<Handler> list = isAdult ? adultHandlers : youngHandlers;
        list.forEach(handler -> {
            if (handler != this) {
                try {
                    handler.writer.write(message);
                    handler.writer.newLine();
                    handler.writer.flush();
                } catch (IOException ignored) {
                    closeSocket();
                }
            }
        });
    }

    private void closeSocket() {
        youngHandlers.remove(this);
        adultHandlers.remove(this);
        List<Closeable> list = List.of(userSocket, writer, reader);
        list.forEach(closeable -> {
            try {
                if (closeable != null) {
                    closeable.close();
                }
            } catch (IOException ignored) {
                System.err.println("Error while closing connection with user!");
            }
        });
        sendMessage(userName + " left the chat.");
    }
}
