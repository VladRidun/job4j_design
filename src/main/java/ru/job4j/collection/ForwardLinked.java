package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> first;

    public void add(T value) {
        Node<T> node = new Node<T>(value, null);
        if (first == null) {
            first = node;
            return;
        }
        Node<T> tail = first;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    public void addFirst(T value) {
        first = new Node<T>(value, first);

    }

    public T deleteFirst() {
        if (first == null) {
            throw new NoSuchElementException();
        }
        T value = first.value;
        Node<T> next = first.next;
        first.next = null;
        first.value = null;
        first = next;
        return value;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> node = first;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}