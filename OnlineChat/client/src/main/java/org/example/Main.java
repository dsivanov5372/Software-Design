package org.example;

import org.example.service.MessageService;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main( String[] args ) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please, enter your name:");
        String userName = scanner.next();
        System.out.println("Enter your age:");
        String age = scanner.next();

        MessageService service = new MessageService("127.0.0.1", 8080, userName, age);
        service.start();
    }
}