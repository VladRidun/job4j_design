package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class CSVReader {
    public static void handle(ArgsName argsName) {
        validate(argsName);
        List<String> strings = new ArrayList<String>();
        String filters = argsName.get("filter");
        String delimiter = argsName.get("delimiter");
        Path path = Paths.get(argsName.get("path"));
        String out = argsName.get("out");
        StringJoiner stringJoiner = new StringJoiner(delimiter);
        Integer[] indexArr = getIndexArray(path, delimiter, filters);

        try (var scanner = new Scanner(path)) {
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(argsName.get("delimiter"));
                for (Integer i : indexArr) {
                    stringJoiner.add(line[i]);
                }
                strings.add(stringJoiner.toString());
                stringJoiner = new StringJoiner(delimiter);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        out(out, strings);
    }

    public static void out(String out, List<String> outArr) {
        if ("stdout".equals(out)) {
            for (String str : outArr) {
                System.out.println(str);
            }
        } else {
            Path path = Paths.get(out);
            try (var pr = new PrintWriter(new BufferedOutputStream(new FileOutputStream(path.toString())))) {
                outArr.forEach(pr::println);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Integer[] getIndexArray(Path path, String delimiter, String filters) {
        String[] filterArr = filters.split(",");
        Integer[] indexArr = new Integer[filterArr.length];
        String line = null;
        try (var reader = new BufferedReader(new FileReader((path).toString()))) {
            line = reader.readLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        String[] inputLine = line.split(delimiter);
        int j = 0;
        for (String f : filterArr) {
            for (int i = 0; i < inputLine.length; i++) {
                if (f.equals(inputLine[i])) {
                    indexArr[j++] = i;
                }
            }
        }
        return indexArr;
    }

    public static void validate(ArgsName args) {
        Path path = Path.of(args.get("path"));
        if (!Files.exists(path) && !Files.isDirectory(path)) {
            throw new IllegalArgumentException("Root folder is null. Usage ROOT_FOLDER");
        }
        if (!";".equals(args.get("delimiter"))) {
            throw new IllegalArgumentException("Use delimiter: ;");
        }
        if (!"stdout".equals(args.get("out")) && !args.get("out").endsWith(".csv")) {
            throw new IllegalArgumentException("output not specified as .csv or stdout");
        }
        args.get("filter");
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 4) {
            throw new IllegalArgumentException("Use 4 arguments: file, delimiter, out, filter ");
        }
        ArgsName argsName = ArgsName.of(args);
        handle(argsName);
    }
}
