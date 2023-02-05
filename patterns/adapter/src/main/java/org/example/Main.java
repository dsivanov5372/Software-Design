package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("На какой отметке большая стрелка? (1-12)");
        int hours = Integer.parseInt(scanner.next());
        System.out.println("На какой отметке маленькая стрелка? (0-59)");
        int minutes = Integer.parseInt(scanner.next());
        System.out.println("За окном сейчас темно/расветает? (да - нет)");
        boolean isDay = !scanner.next().equals("да");
        DigitalWatch watch = ClockAdapter.getTime(new Clocks(hours, minutes, isDay));
        System.out.println(watch.getCurrTime());
    }
}