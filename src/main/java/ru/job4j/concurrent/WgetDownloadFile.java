package ru.job4j.concurrent;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;

public class WgetDownloadFile implements Runnable {
    private final String url;
    private final int speed;
    private final String nameFile;

    public WgetDownloadFile(String url, String nameFile, int speed) {
        this.url = url;
        this.nameFile = nameFile;
        this.speed = speed;
    }

    @Override
    public void run() {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(nameFile)) {
            byte[] dataBuffer = new byte[speed];
            int bytesRead;
            long start = System.currentTimeMillis();
            while ((bytesRead = in.read(dataBuffer, 0, speed)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                long finish = System.currentTimeMillis();
                long rsl = finish - start;
                if (speed > rsl) {
                    Thread.sleep(speed - rsl);
                }
                start = System.currentTimeMillis();
            }
        } catch (IOException | InterruptedException e) {
            Thread.currentThread().isInterrupted();
        }
    }

    private static void validateArgs(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("Не установленные все параметры, проверьте скачиваемый файл, имя файла и скорость ограничения");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        validateArgs(args);
        String url = args[0];
        String nameFile = args[1];
        int speed = Integer.parseInt(args[2]);
        Thread wget = new Thread(new WgetDownloadFile(url, nameFile, speed));
        wget.start();
        wget.join();
    }
}
