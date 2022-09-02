package ru.job4j.collection.question;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Analize {
    public static Info diff(Set<User> previous, Set<User> current) {
        int ch = 0;
        int del = 0;
        Map<Integer, String> cur = current.stream().collect(Collectors.toMap(User::getId, User::getName));
        Info info = new Info(0, 0, 0);
        for (User prev : previous) {
            if (!cur.containsKey(prev.getId())) {
                info.setDeleted(++del);
            } else if (cur.containsKey(prev.getId()) && !cur.containsValue(prev.getName())) {
                info.setChanged(++ch);
            }
            info.setAdded(current.size() + del - previous.size());
        }
        return info;
    }
}