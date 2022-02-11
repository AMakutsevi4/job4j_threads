package ru.job4j.concurrent;

public class Wget {

    public static void main(String[] args) {
        Thread thread = new Thread(
                () -> {
                    try {
                        System.out.println("Начнется загрузка файла, подождите.");
                        for (int index = 0; index <= 100; index++) {
                            Thread.sleep(1000);
                            System.out.print("\rLoading : " + index + "%");
                        }
                        System.out.print(System.lineSeparator() + "Загрузка успешно завершена!");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );
        thread.start();
    }
}