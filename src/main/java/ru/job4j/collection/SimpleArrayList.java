package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements List<T> {

    private T[] container;
    private int size;
    private int modCount;
    private int capacity = 10;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (size == container.length) {
            container = Arrays.copyOf(container, container.length * 2);
        }
        modCount++;
        container[size++] = value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T set(int index, T newValue) {
        Objects.checkIndex(index, container.length);
        T oldElement = container[index];
        container[index] = newValue;
        return oldElement;
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, container.length);
        modCount++;
        T oldElement = container[index];
        System.arraycopy(container, index + 1, container, index, size - 1 - index);
        container[--size] = null;
        return oldElement;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, container.length);
        return container[index];
    }


    @Override
    public Iterator<T> iterator() {

        return new Iterator<T>() {

            int expectedModCount = modCount;
            int point = 0;

            @Override
            public boolean hasNext() {
                return point < size;
            }

            @Override
            public T next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[point++];
            }

        };
    }
}