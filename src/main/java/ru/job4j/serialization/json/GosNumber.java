package ru.job4j.serialization.json;

public class GosNumber {
    private final String gosNumber;

    public GosNumber(String gosNumber) {
        this.gosNumber = gosNumber;
    }

    public String getGosNumber() {
        return gosNumber;
    }

    @Override
    public String toString() {
        return "GosNumber{"
                + "gosNumber='"
                + gosNumber + '\''
                + '}';
    }
}
