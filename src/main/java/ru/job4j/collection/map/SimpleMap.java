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
        boolean insert = true;
        expand();
        if (table[indexFor(hash(key.hashCode()))].key.equals(key)) {
            table[indexFor(hash(key.hashCode()))].value = value;
            insert = false;
        } else {
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
        return hash & (capacity - 1);
    }

    private void expand() {
        if (count >= capacity * LOAD_FACTOR) {
            capacity = capacity * 2;
            MapEntry<K, V>[] src = table;
            MapEntry<K, V>[] newTable = new MapEntry[capacity];
            for (MapEntry<K, V> el : src) {
                newTable[indexFor(hash(el.key.hashCode()))] = el;
            }
            table = newTable;
        }
    }

    @Override
    public V get(K key) {
        if (table[indexFor(hash(key.hashCode()))] != null && table[indexFor(hash(key.hashCode()))].key.equals(key)) {
            return table[indexFor(hash(key.hashCode()))].value;
        }
        return null;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        if (table[indexFor(hash(key.hashCode()))] != null && table[indexFor(hash(key.hashCode()))].key.equals(key)) {
            table[indexFor(hash(key.hashCode()))] = null;
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
            int index;
            int currentNodeNumber;


            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return index < count;
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