package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        validate(argsName);
        List<String> strings = new ArrayList<String>();
        String filters = argsName.get("filter");
        String delimiter = argsName.get("delimiter");
        Path path = Paths.get(argsName.get("path"));
        StringJoiner stringJoiner = new StringJoiner(delimiter);
        Integer[] indexArr = getIndexArray(path, delimiter, filters);
        try {
            var scanner = new Scanner(path);
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(argsName.get("delimiter"));
                for (Integer i : indexArr) {
                    stringJoiner.add(line[i]);
                }
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static Integer[] getIndexArray(Path path, String delimiter, String filters) {
        String[] filterArr = filters.split(",");
        Integer[] indexArr = new Integer[filterArr.length];
        String line = null;
        try {
            FileReader fr = new FileReader((path).toString());
            BufferedReader reader = new BufferedReader(fr);
            line = reader.readLine();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        String[] inputLine = line.split(delimiter);
        for (String f : filterArr) {
            for (int i = 0; i < inputLine.length; i++) {
                if (f.equals(inputLine[i])) {
                    for (int j = 0; 0 < indexArr.length; j++) {
                        indexArr[j] = i;
                    }
                }
            }
        }
        return indexArr;
    }

    public static void validate(ArgsName args) {
        var argArr = new ArrayList<String>();
        argArr.add(args.get("path"));
        argArr.add(args.get("delimiter"));
        argArr.add(args.get("out"));
        argArr.add(args.get("filter"));
        if (argArr.size() != 4) {
            throw new IllegalArgumentException("Use 4 arguments: file, delimiter, out, filter ");
        }
        Path path = Path.of(args.get("path"));
        if (!Files.exists(path) && !Files.isDirectory(path)) {
            throw new IllegalArgumentException("Root folder is null. Usage ROOT_FOLDER");
        }
        if (!args.get("delimiter").equals(";")) {
            throw new IllegalArgumentException("Use delimiter: ;");
        }
        if (args.get("out").isEmpty()) {
            throw new IllegalArgumentException("output not specified");
        }
        if (args.get("filter").isEmpty()) {
            throw new IllegalArgumentException("filter not specified");
        }
    }
}
