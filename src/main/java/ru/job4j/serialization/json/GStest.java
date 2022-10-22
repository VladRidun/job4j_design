package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GStest {
    public static void main(String[] args) {

        /* JSONObject из json-строки строки */
        JSONObject jsonGosNumber = new JSONObject("{\"gosNumber\":\"T869YR163\"}");

        /* JSONArray из ArrayList */
        List<String> list = new ArrayList<>();
        list.add("red");
        list.add("universal");
        list.add("MT");
        list.add("comfort");
        JSONArray jsonSpecs = new JSONArray(list);

        /* JSONObject напрямую методом put */
        final Car car = new Car("lada", "vesta", "gasoline", new GosNumber("T869YR163"), 2017, new String[]{"red", "universal", "MT", "Comfort"}, true);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("producer", car.getProducer());
        jsonObject.put("model", car.getModel());
        jsonObject.put("engineType", car.getEngineType());
        jsonObject.put("gosNumber", jsonGosNumber);
        jsonObject.put("releaseYear", car.getReleaseYear());
        jsonObject.put("specs", jsonSpecs);
        jsonObject.put("enable", car.isEnable());

        /* Выведем результат в консоль */
        System.out.println(jsonObject.toString());

        /* Преобразуем объект car в json-строку */
        System.out.println(new JSONObject(car).toString());
    }
}
