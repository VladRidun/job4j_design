package ru.job4j.io.controlwork;

import java.io.IOException;

import static ru.job4j.io.controlwork.SearchCW.search;
import static ru.job4j.io.controlwork.SearchCW.validate;

public class MainCW {

    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            throw new IllegalArgumentException("Use 3 arguments: root folder, file extension, folder for logfile");
        }
        ArgsNameCW argsNameCW = ArgsNameCW.of(args);
        validate(argsNameCW);
        search(argsNameCW);
    }
}