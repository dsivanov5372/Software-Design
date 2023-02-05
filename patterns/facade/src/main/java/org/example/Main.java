package org.example;

import org.example.firmware.OS;
import org.example.firmware.UEFI;
import org.example.hardware.CentralProcessor;
import org.example.hardware.HDD;
import org.example.hardware.RAM;
import org.example.hardware.VideoCard;

public class Main {
    public static void main(String[] args) {
        Computer computer = new Computer(new OS(), new UEFI(), new VideoCard(),
                                         new CentralProcessor(), new RAM(), new HDD());
        try {
            computer.turnOn();
            computer.executeProgram("Поставьте +");
            computer.turnOf();
        } catch (Exception ignored) {
        } finally {
            System.out.println("Я съел кота");
        }
    }
}