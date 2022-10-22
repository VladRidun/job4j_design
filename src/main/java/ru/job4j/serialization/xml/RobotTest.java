package ru.job4j.serialization.xml;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class RobotTest {
    public static void main(String[] args) {
        final Robot robot = new Robot("Striker Eureka", "Russia", true,  1.850f, 76.2f, 10, new Armor("full"), new String[]{"gun", "laser"});

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(robot));

        final String robotJson =
                "{"
                        + "\"modelName\":StrikerEureka,"
                        + "\"origin\":Russia,"
                        + "\"prepared\":true,"
                        + "\"weight\":1.850f,"
                        + "\"height\":76.2f,"
                        + "\"strength\":10,"
                        + "\"armor\":"
                        + "{"
                        + "\"armorType\":\"full\""
                        + "},"
                        + "\"weapon\":"
                        + "[\"gun\",\"laser\"]"
                        + "}";
        final Robot robotMod = gson.fromJson(robotJson, Robot.class);
        System.out.println(robot);
    }
}