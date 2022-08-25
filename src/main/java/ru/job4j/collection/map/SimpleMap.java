package ru.job4j.collection.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;
    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean insert = false;
        if (count >= capacity * LOAD_FACTOR) {
            expand();
        }
        MapEntry<K, V> newEntry = new MapEntry<>(key, value);
        int index = indexFor(hash(key));
        if (table[index] == null) {
            table[index] = newEntry;
            modCount++;
            count++;
            insert = true;
        }
        return insert;
    }

    private int hash(K key) {
        return (key == null) ? 0 : key.hashCode() ^ (key.hashCode() >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
        capacity *= 2;
        MapEntry<K, V>[] newTable = new MapEntry[capacity];
        for (MapEntry<K, V> el : table) {
            if (el != null) {
                newTable[indexFor(hash(el.key))] = el;
            }
        }
        table = newTable;
    }

    public boolean checkKey(K key) {
        int index = indexFor(hash(key));
        K elKey = table[index] != null ? table[index].key : null;
        return (hash(key) == hash(elKey) && (key == elKey) || key.equals(elKey));
    }

    @Override
    public V get(K key) {
        int index = indexFor(hash(key));
        if (checkKey(key)) {
            return table[index].value;
        }
        return null;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        int index = indexFor(hash(key));
        if (checkKey(key)) {
            table[index] = null;
            count--;
            modCount++;
            rsl = true;
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private final MapEntry<K, V>[] data = table;
            int expectedModCount = modCount;
            int index = 0;


            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (index < table.length && table[index] == null) {
                    index++;
                }
                return index < table.length;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return data[index++].key;
            }
        };
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