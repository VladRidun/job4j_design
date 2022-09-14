package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            for (String num : text.toString().split(System.lineSeparator())) {
                System.out.println(num + " - " + (Integer.parseInt(num) % 2 == 0));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}