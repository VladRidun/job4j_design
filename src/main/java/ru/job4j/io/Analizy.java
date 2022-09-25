package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class Analizy {
    public void unavailable(String source, String target) {
        List<String> filterList = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            String contentLine;
            String addLine = null;
            boolean check = true;
            while ((contentLine = in.readLine()) != null) {
                if (check && (contentLine.startsWith("400") || contentLine.startsWith("500"))) {
                    addLine = contentLine.substring(3);
                    check = false;
                } else if (!check && (contentLine.startsWith("200") || contentLine.startsWith("300"))) {
                    addLine = addLine + ";" + contentLine.substring(3) + ";";
                    check = true;
                    filterList.add(addLine);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(target)
                ))) {
            filterList.forEach(out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy analizy = new Analizy();
        String pathS = "./sourceLog.txt";
        String pathT = "./unavailable.txt";
        analizy.unavailable(pathS, pathT);
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}