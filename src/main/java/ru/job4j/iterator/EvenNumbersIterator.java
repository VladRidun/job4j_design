package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {

    private int[] data;
    private int index;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }


    @Override
    public boolean hasNext() {
        boolean rsl = false;
        if (data.length > index) {
            if (data[index] % 2 == 0) {
                rsl = true;
            }
        }
        return rsl;

    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[index++];
    }
}
