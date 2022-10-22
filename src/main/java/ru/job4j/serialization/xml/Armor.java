package ru.job4j.serialization.xml;

public class Armor {
    private final String armorType;

    public Armor(String armorType) {
        this.armorType = armorType;
    }

    @Override
    public String toString() {
        return "Armor{"
                + "armorType='" + armorType + '\''
                + '}';
    }
}
