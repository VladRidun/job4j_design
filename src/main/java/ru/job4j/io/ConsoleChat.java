package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        Scanner input = new Scanner(System.in);
        List<String> log = new ArrayList<>();
        var randomAnswers = readPhrases();
        System.out.println("Чат открыт");
        String message = input.nextLine();
        log.add(message);
        while (!message.equalsIgnoreCase(OUT)) {
            message = randomAnswers.get(new Random().nextInt(randomAnswers.size()));
            log.add(message);
            System.out.println(message);
            message = input.nextLine();
            log.add(message);
            if (message.equals(STOP)) {
                do {
                    message = input.nextLine();
                    log.add(message);
                } while (!message.equalsIgnoreCase(CONTINUE));
            }
        }
        saveLog(log);
        input.close();
    }

    private List<String> readPhrases() {
        List<String> phrases = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(botAnswers))) {
            br.lines().forEach(phrases::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return phrases;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path, Charset.forName("WINDOWS-1251"), true))) {
            log.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./chat/log.txt", "./chat/bot.txt");
        cc.run();
    }
}