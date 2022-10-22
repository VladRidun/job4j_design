package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        final Car car = new Car("lada", "vesta", "gasoline", new GosNumber("T869YR163"), 2017, new String[]{"red", "universal", "MT", "Comfort"}, true);

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(car));

        final String carJson =
                "{"
                        + "\"producer\":lada,"
                        + "\"model\":vesta,"
                        + "\"engineType\":gasoline,"
                        + "\"gosNumber\":"
                        + "{"
                        + "\"gosNumber\":\"T869YR163\""
                        + "},"
                        + "\"releaseYear\":2017,"
                        + "\"specs\":"
                        + "[\"red\",\"universal\",\"MT\",\"comfort\"],"
                        + "\"enable\":true"
                        + "}";
        final Car carMod = gson.fromJson(carJson, Car.class);
        System.out.println(carMod);
    }
}
