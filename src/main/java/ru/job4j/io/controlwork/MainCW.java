package ru.job4j.io.controlwork;

import java.io.IOException;

import static ru.job4j.io.controlwork.SearchCW.search;
import static ru.job4j.io.controlwork.SearchCW.validate;

public class MainCW {

    public static void main(String[] args) throws IOException {
        if (args.length != 4) {
            throw new IllegalArgumentException("Use 4 arguments: root folder, file extension, folder for logfile");
        }
        var argsNameCW = ArgsNameCW.of(args);
        validate(argsNameCW);
        search(argsNameCW);
    }
}

