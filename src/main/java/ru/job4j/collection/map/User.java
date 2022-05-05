package ru.job4j.collection.map;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public static void main(String[] args) {

        User user1 = new User("Vlad", 1, new GregorianCalendar(1989, Calendar.OCTOBER, 05));
        User user2 = new User("Marina", 1, new GregorianCalendar(1990, Calendar.JULY, 17));
        Map<User, Object> map1 = new HashMap<>();
        map1.put(user1, new Object());
        map1.put(user2, new Object());

        BiConsumer<User, Object> consumer = (o1, o2) ->
                System.out.println("Key :" + o1 + ", Value : " + o2);
        map1.forEach(consumer);
    }
}
