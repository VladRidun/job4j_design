package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static ru.job4j.io.Search.search;
import static ru.job4j.io.Search.validate;

public class Zip {

    public static void packFiles(List<Path> sources, File target) {
        for (Path path : sources) {
            try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
                zip.putNextEntry(new ZipEntry(path.toAbsolutePath().toString()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(path.toAbsolutePath().toString()))) {
                    zip.write(out.readAllBytes());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args)  throws IOException {
        validate(args);
        ArgsName argsName = ArgsName.of(args);
        List<Path> sources = search(Path.of(argsName.get("d")), p -> !p.toFile().getName().endsWith(argsName.get("e")));
        packFiles(sources, new File(argsName.get("o")));
    }
}
