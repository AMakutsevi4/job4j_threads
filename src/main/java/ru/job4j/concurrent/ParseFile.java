package ru.job4j.concurrent;

import java.io.*;
import java.util.function.Predicate;

public final class ParseFile {

    private final File file;

    public ParseFile(File file) {
        this.file = file;
    }

    public synchronized String getContent() {

        return content((c) -> true);
    }

    public synchronized String getContentWithoutUnicode() {

        return content((c) -> c < 0x80);
    }

    public synchronized String content(Predicate<Character> filter) {
        StringBuilder output = new StringBuilder();
        try (InputStream i = new BufferedInputStream(new FileInputStream(file))) {
            int data;
            while ((data = i.read()) != -1) {
                output.append((char) data);
                if (filter.test((char) data)) {
                    output.append((char) data);
                }
            }
        }  catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }
}