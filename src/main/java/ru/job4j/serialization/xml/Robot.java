package ru.job4j.serialization.xml;

import java.util.Arrays;

public class Robot {
    private final String modelName;
    private final String origin;
    private final boolean prepared;
    private final float weight;
    private final float height;
    private final int strength;
    private final Armor armor;
    private String[] weapon;

    public Robot(String modelName, String origin, boolean prepared, float weight, float height, int strength, Armor armor, String[] weapon) {

        this.modelName = modelName;
        this.origin = origin;
        this.prepared = prepared;
        this.weight = weight;
        this.height = height;
        this.strength = strength;
        this.armor = armor;
        this.weapon = weapon;
    }

    @Override
    public String toString() {
        return "Robot{"
                + "modelName='" + modelName + '\''
                + ", origin='" + origin + '\''
                + ", prepared=" + prepared
                + ", weight=" + weight
                + ", height=" + height
                + ", strength=" + strength
                + ", armor=" + armor
                + ", weapon=" + Arrays.toString(weapon)
                + '}';
    }
}
