package ru.job4j.collection.map;

import java.util.Arrays;
import java.util.Iterator;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean insert = true;
        for (int i = 0; i < count; i++) {
            if (table[i].key.equals(key)) {
                table[i].value = value;
                insert = false;
            }
        }
        if (insert) {
            expand();
            table[indexFor(hash(key.hashCode()))] = new MapEntry<K, V>(key, value);
            modCount++;
            count++;
        }
        return insert;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (count - 1);
    }

    private void expand() {
        if (count >= table.length * LOAD_FACTOR) {
            int newSize = table.length * 2;
            table = Arrays.copyOf(table, newSize);
        }
    }

    @Override
    public V get(K key) {
        for (int i = 0; i < count; i++) {
            if (table[i] != null && table[i].key.equals(key)) {
                return table[i].value;
            }
        }
        return null;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        for (int i = 0; i < count; i++) {
            if (table[i].key.equals(key)) {
                table[i] = null;
                count--;
                modCount++;
                rsl = true;
            }
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return null;
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }

}