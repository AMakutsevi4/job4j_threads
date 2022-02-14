package ru.job4j.concurrent;

import java.io.*;
import java.util.function.Predicate;

public class ParseFile {

    private final File file;

    public ParseFile(File file) {
        this.file = file;
    }

    public String getContent() throws IOException {

        return content((c) -> c > 0);
    }

    public String getContentWithoutUnicode() throws IOException {

        return content((c) -> c < 0x80);
    }

    public String content(Predicate<Character> filter) throws IOException {
        StringBuilder output = new StringBuilder();
        try (InputStream i = new BufferedInputStream(new FileInputStream(file))) {
            int data;
            while ((data = i.read()) > 0) {
                output.append((char) data);
            }
            if (filter.test((char) data)) {
                output.append((char) data);
            }
        }
        return output.toString();
    }
}