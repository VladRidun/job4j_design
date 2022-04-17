package ru.job4j.generics;

public class Tiger extends Predator {
    private String name;

    @Override
    public String toString() {
        return "Tiger{"
                + "name='"
                + name + '\''
                + '}';
    }
}
