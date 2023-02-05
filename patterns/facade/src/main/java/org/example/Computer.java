package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.firmware.OS;
import org.example.firmware.UEFI;
import org.example.hardware.CentralProcessor;
import org.example.hardware.HDD;
import org.example.hardware.RAM;
import org.example.hardware.VideoCard;

@Data
@AllArgsConstructor
public class Computer {
    OS os;
    UEFI uefi;
    VideoCard card;
    CentralProcessor centralProcessor;
    RAM ram;
    HDD hdd;

    public void turnOn() throws NoSuchMethodException {
        centralProcessor.initialize();
        ram.initialize();
        hdd.initialize();
        hdd.initialize();
        uefi.getSMRAMDrivers();
        os.initialize();
    }

    public void executeProgram(String name) throws NoSuchMethodException {
        System.out.println("Запущена программа: " + name);
        card.drawUI();
        ram.getData();
        centralProcessor.executeCommands();
        hdd.workWithDB();
    }

    public void turnOf() throws NoSuchMethodException {
        os.turnOfAllPrograms();
        hdd.shutDown();
        card.shutDown();
        uefi.saveSettings();
        ram.shutDown();
        centralProcessor.shutDown();
    }
}