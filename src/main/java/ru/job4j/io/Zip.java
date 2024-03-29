package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static ru.job4j.io.Search.search;

public class Zip {

    public static void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path path : sources) {
                zip.putNextEntry(new ZipEntry(path.toString()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(path.toString()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void validate(ArgsName args) {
        Path path = Path.of(args.get("d"));
        if (!Files.exists(path) && !Files.isDirectory(path)) {
            throw new IllegalArgumentException("Root folder is null. Usage ROOT_FOLDER");
        }
        if (!args.get("o").endsWith(".zip")) {
            throw new IllegalArgumentException("Specify the file extension");
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            throw new IllegalArgumentException("Use 3 arguments: root folder, file extension");
        }
        ArgsName argsName = ArgsName.of(args);
        validate(argsName);
        List<Path> sources = search(Path.of(argsName.get("d")), p -> !p.toFile().getName().endsWith(argsName.get("e")));
        packFiles(sources, new File(argsName.get("o")));
    }
}