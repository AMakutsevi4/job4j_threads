package ru.job4j.concurrent;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

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
        System.out.println("Speed: " + speed + " B/s.");
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(nameFile)) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            long bytesWritten = 0;
            long start = System.currentTimeMillis();
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                bytesWritten += bytesRead;
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                if (bytesWritten >= speed) {
                    long time = System.currentTimeMillis() - start;
                    bytesWritten = 0;
                    if (time < 1000) {
                        Thread.sleep(1000 - time);
                    }
                }
                start = System.currentTimeMillis();
            }
        } catch (IOException | InterruptedException e) {
            Thread.currentThread().isInterrupted();
        }
        System.out.print("End.");
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
