package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;


public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final Map<FileProperty, List<Path>> files = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(file.toFile().length(), file.toFile().getName());
        if (files.containsKey(fileProperty)) {
            files.get(fileProperty).add(file);
        } else {
            files.put(fileProperty, new ArrayList<>(List.of(file)));
        }
        return super.visitFile(file, attrs);
    }

    public void getDublicates() {
        for (Map.Entry<FileProperty, List<Path>> entry : files.entrySet()) {
            if (entry.getValue().size() > 1) {
                System.out.println(entry.getValue());
            }
        }
    }
}