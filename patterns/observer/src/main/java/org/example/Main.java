package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AlertService service = new AlertService();
        for (int i = 1; i <= 5; ++i) {
            service.addPatrol(new TrafficPatrol(i));
        }

        String message = scanner.next();
        while (!message.equals("/stop")) {
            service.sendAlert(message);
            message = scanner.next();
        }
    }
}