package ru.job4j.serialization.json;

import java.util.Arrays;

public class Car {
    private final String producer;
    private final String model;
    private final String engineType;
    private final GosNumber gosNumber;
    private final int releaseYear;
    private final String[] specs;
    private final boolean enable;

    public Car(String producer, String model, String engineType, GosNumber gosNumber, int releaseYear, String[] specs, boolean enable) {
        this.producer = producer;
        this.model = model;
        this.engineType = engineType;
        this.gosNumber = gosNumber;
        this.releaseYear = releaseYear;
        this.specs = specs;
        this.enable = enable;
    }

    @Override
    public String toString() {
        return "Car{"
                + "producer='" + producer + '\''
                + ", model='" + model + '\''
                + ", engineType='" + engineType + '\''
                + ", gosNumber=" + gosNumber
                + ", releaseYear=" + releaseYear
                + ", specs=" + Arrays.toString(specs)
                + ", enable=" + enable
                + '}';
    }
}
