package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException(
                    "no data available");
        }
        return values.get(key);
    }

    private void validate(String arg) {
        if (!arg.startsWith("-")) {
            throw new IllegalArgumentException(
                    "the argument does not start with the character \"-\"");
        }
        if (!arg.contains("=")) {
            throw new IllegalArgumentException(
                    "missing sign '=' ");
        }
        if (arg.startsWith("-=") || arg.indexOf("=") == arg.length() - 1) {
            throw new IllegalArgumentException(
                    "no key or value in the parameter");
        }
    }

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("No arguments entered");
        }
        values.putAll(Stream.of(args).filter(e -> {
                    validate(e);
                    return true;
                }).map(e -> e.substring(1))
                .map(e -> e.split("=", 2))
                .collect(Collectors.toMap(e -> e[0], e -> e[1])));
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}