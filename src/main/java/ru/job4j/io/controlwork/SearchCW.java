package ru.job4j.io.controlwork;

import ru.job4j.io.SearchFiles;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class SearchCW {

    public static void search(ArgsNameCW args) throws IOException {
        var root = Path.of(args.get("d"));
        var out = args.get("o");
        Predicate<Path> condition = null;
        var typeSearch = args.get("t");
        var nameFile = args.get("n");
        if ("mask".equals(args.get("t"))) {
            var string = args.get("n");
            string = string.replace(".", "[.]");
            string = string.replace("*", ".+");
            string = string.replace("?", ".");
            var finalString = string;
            condition = p -> p.toFile().getName().matches(finalString);
        } else if ("name".equals(typeSearch)) {
            condition = p -> p.toFile().getName().equals(nameFile);
        } else if ("regex".equals(typeSearch)) {
            condition = p -> p.toFile().getName().matches(nameFile);
        }
        var searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        List<Path> pathList = searcher.getPaths();
        out(out, pathList);
    }

    public static void validate(ArgsNameCW args) {
        var path = Path.of(args.get("d"));
        if (!Files.exists(path) && !Files.isDirectory(path)) {
            throw new IllegalArgumentException("Root folder is null. Usage ROOT_FOLDER");
        }
        if (!"mask".equals(args.get("t")) && !"name".equals(args.get("t")) && !"regex".equals(args.get("t"))) {
            throw new IllegalArgumentException("Use mask or name or regex");
        }
        if (!"stdout".equals(args.get("o")) && !args.get("o").endsWith(".txt")) {
            throw new IllegalArgumentException("output not specified as .txt or stdout");
        }
    }

    public static void out(String out, List<Path> outArr) {
        if ("stdout".equals(out)) {
            for (Path str : outArr) {
                System.out.println(str.toString());
            }
        } else {
            Path path = Paths.get(out);
            try (var pr = new PrintWriter(new BufferedOutputStream(new FileOutputStream(path.toString())))) {
                outArr.forEach(pr::println);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Result write in " + out);
        }
    }
}