package ru.job4j.collection.question;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Analize {
    public static Info diff(Set<User> previous, Set<User> current) {
        int changed = 0;
        int deleted = 0;
        Map<Integer, String> cur = current.stream().collect(Collectors.toMap(User::getId, User::getName));
        Info info = new Info(0, 0, 0);
        for (User prev : previous) {
            if (!cur.containsKey(prev.getId())) {
                info.setDeleted(++deleted);
            } else if (cur.containsKey(prev.getId()) && !cur.containsValue(prev.getName())) {
                info.setChanged(++changed);
            }
            info.setAdded(current.size() + deleted - previous.size());
        }
        return info;
    }
}