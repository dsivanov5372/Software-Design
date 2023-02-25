package org.example;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    private final static Scanner scanner = new Scanner(System.in);
    private final static ExecutorService service = Executors.newFixedThreadPool(10);

    private static void printInfo() {
        System.out.println("/help - справка по существующим командам;");
        System.out.println("/exit - выход из программы;");
        System.out.println("/dest PATH - указать путь до директории, где будут находится скаченные файлы,");
        System.out.println("при этом путь должен указывать на существующую директорию;");
        System.out.println("/load URL1 URL2 ... - скачивание файлов, находящихся по адресу URL1, URL2 и т.д.,");
        System.out.println("при этом хотя бы один путь должен быть указан;");
    }

    private static File getDir(File dir) {
        String path = scanner.next();
        if (path == null || path.isBlank()) {
            System.out.println("Вы не указали путь!");
            return dir;
        }
        File newDir = new File(path);
        if (!newDir.exists() || !newDir.isDirectory()) {
            System.out.println("Указанный путь не существует или не является директорией!");
            return dir;
        }
        return newDir;
    }

    private static void downloadFiles(File dir) {
        String[] urls = scanner.nextLine().split(" ");

        if (urls.length == 0) {
            System.out.println("Вы не указали url до файла!");
        } else if (dir == null) {
            System.out.println("Для скачивания файлов нужно указать путь до директории!");
        } else {
            String curPath = dir.getPath() + (dir.getPath().endsWith("/") ? "" : "/");
            for (String url : urls) {
                if (!url.isBlank()) {
                    File newDir = new File(curPath + url.substring(url.lastIndexOf("/")));
                    service.execute(() -> {
                        try {
                            FileUtils.copyURLToFile(new URL(url), newDir, 10000, 10000);
                        } catch (IOException e) {
                            System.out.println("File with url " + url + " not found!");
                        }
                    });
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        File dir = null;
        String command = scanner.next();
        while (!command.equals("/exit")) {
            switch (command) {
                case "/dest" -> dir = getDir(dir);
                case "/load" -> downloadFiles(dir);
                default -> printInfo();
            }
            command = scanner.next();
        }

        service.shutdown();
        while (!service.awaitTermination(1, TimeUnit.SECONDS)) {
        }
    }
}