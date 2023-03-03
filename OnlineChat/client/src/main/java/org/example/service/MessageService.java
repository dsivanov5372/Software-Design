package org.example.service;

import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class MessageService {
    private final Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;

    public MessageService(String host, int port, String userName, String age) throws IOException {
        this.socket = new Socket(host, port);
        this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        writer.write(userName);
        writer.newLine();
        writer.write(age);
        writer.newLine();
        writer.flush();
    }

    public void start() {
        final Scanner scanner = new Scanner(System.in);
        Thread messager = new Thread(() -> {
            while (socket.isConnected()) {
                try {
                    writer.write(scanner.nextLine());
                    writer.newLine();
                    writer.flush();
                } catch (IOException ignored) {
                    close();
                    break;
                }
            }
        });

        Thread listener = new Thread(() -> {
            System.out.println("You are connected to the chat!");
            while (socket.isConnected()) {
                try {
                    System.out.println(reader.readLine());
                } catch (IOException e) {
                    close();
                    break;
                }
            }
        });

        messager.start();
        listener.start();
    }

    private void close() {
        List<Closeable> list = List.of(socket, writer, reader);
        list.forEach(closeable -> {
            try {
                if (closeable != null) {
                    closeable.close();
                }
            } catch (IOException ignored) {
                System.err.println("Error while closing connection with user!");
            }
        });
    }
}
