package ru.job4j.collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SimpleArrayListTest {

    List<Integer> list;

    @BeforeEach
    public void initData() {
        list = new SimpleArrayList<>(3);
        list.add(1);
        list.add(2);
        list.add(3);
    }

    @Test
    public void whenAddThenSizeIncrease() {
        assertThat(list.size()).isEqualTo(3);
    }


    @Test
    public void whenAddAndGetByCorrectIndex() {
        assertThat(list.get(0)).isEqualTo(1);
    }

    @Test()
    public void whenAddAndGetByIncorrectIndexThenGetException() {
        assertThrows(IndexOutOfBoundsException.class,
                () -> {
                    list.get(5);
                });
    }

    @Test
    public void whenRemoveThenGetValueAndSizeDecrease() {
        assertThat(list.size()).isEqualTo(3);
        assertThat(list.remove(1)).isEqualTo(Integer.valueOf(2));
        assertThat(list.size()).isEqualTo(2);
    }

    @Test()
    public void whenRemoveByIncorrectIndexThenGetException() {
        assertThrows(IndexOutOfBoundsException.class,
                () -> {
                    list.remove(5);
                });
    }

    @Test
    public void whenRemoveThenMustNotBeEmpty() {
        list.remove(1);
        assertThat(list.get(0)).isEqualTo(Integer.valueOf(1));
        assertThat(list.get(1)).isEqualTo(Integer.valueOf(3));
    }

    @Test
    public void whenAddNullThenMustBeSameBehavior() {
        list = new SimpleArrayList<>(3);
        list.add(null);
        list.add(null);
        assertThat(list.size()).isEqualTo(2);
        assertThat(list.get(0)).isEqualTo(null);
        assertThat(list.get(1)).isEqualTo(null);
    }

    @Test
    public void whenSetThenGetOldValueAndSizeNotChanged() {
        assertThat(list.set(1, 22)).isEqualTo(2);
        assertThat(list.size()).isEqualTo(3);
    }

    @Test()
    public void whenSetByIncorrectIndexThenGetException() {
        assertThrows(IndexOutOfBoundsException.class,
                () -> {
                    list.set(5, 22);
                });
    }

    @Test
    public void whenGetIteratorFromEmptyListThenHasNextReturnFalse() {
        list = new SimpleArrayList<>(5);
        assertThat(list.iterator().hasNext()).isFalse();
    }

    @Test()
    public void whenGetIteratorFromEmptyListThenNextThrowException() {
        assertThrows(NoSuchElementException.class,
                () -> {
                    list = new SimpleArrayList<>(5);
                    list.iterator().next();
                });
    }

    @Test
    public void whenGetIteratorTwiceThenStartAlwaysFromBeginning() {
        assertThat(list.iterator().next()).isEqualTo(1);
        assertThat(list.iterator().next()).isEqualTo(1);
    }

    @Test
    public void whenCheckIterator() {
        Iterator<Integer> iterator = list.iterator();
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo(Integer.valueOf(1));
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo(Integer.valueOf(2));
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo(Integer.valueOf(3));
        assertThat(iterator.hasNext()).isFalse();
    }

    @Test
    public void whenNoPlaceThenMustIncreaseCapacity() {
        IntStream.range(3, 10).forEach(v -> list.add(v));
    }

    @Test()
    public void whenAddAfterGetIteratorThenMustBeException() {
        assertThrows(ConcurrentModificationException.class,
                () -> {
                    Iterator<Integer> iterator = list.iterator();
                    list.add(4);
                    iterator.next();
                });

    }

    @Test()
    public void whenRemoveAfterGetIteratorThenMustBeException() {
        assertThrows(ConcurrentModificationException.class,
                () -> {
                    Iterator<Integer> iterator = list.iterator();
                    list.add(0);
                    iterator.next();
                });
    }
}