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
    private Random random;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
        this.random = new Random();
    }

    public void run() {
        var input = new Scanner(System.in);
        List<String> log = new ArrayList<>();
        var randomAnswers = readPhrases();
        System.out.println("Чат открыт");
        String message = input.nextLine();
        log.add(message);
        while (!OUT.equalsIgnoreCase(message)) {
            message = randomAnswers.get(random.nextInt(randomAnswers.size()));
            log.add(message);
            System.out.println(message);
            message = input.nextLine();
            log.add(message);
            if (STOP.equals(message)) {
                do {
                    message = input.nextLine();
                    log.add(message);
                } while (!CONTINUE.equalsIgnoreCase(message));
            }
        }
        saveLog(log);
        input.close();
    }

    private List<String> readPhrases() {
        List<String> phrases = new ArrayList<>();
        try (var br = new BufferedReader(new FileReader(botAnswers))) {
            br.lines().forEach(phrases::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return phrases;
    }

    private void saveLog(List<String> log) {
        try (var pw = new PrintWriter(new FileWriter(path, Charset.forName("WINDOWS-1251"), true))) {
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