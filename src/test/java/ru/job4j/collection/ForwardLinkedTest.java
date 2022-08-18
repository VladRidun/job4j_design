package ru.job4j.collection;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class ForwardLinkedTest {

    @Test
    public void whenDeleteFirst() {
        assertThrows(NoSuchElementException.class,
                () -> {
                    ForwardLinked<Integer> linked = new ForwardLinked<>();
                    linked.add(1);
                    linked.deleteFirst();
                    linked.iterator().next();
                });
    }

    @Test
    public void whenDeleteEmptyLinked() {
        assertThrows(NoSuchElementException.class,
                () -> {
                    ForwardLinked<Integer> linked = new ForwardLinked<>();
                    linked.deleteFirst();
                });
    }

    @Test
    public void whenMultiDelete() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(1);
        linked.add(2);
        assertThat(linked.deleteFirst()).isEqualTo(1);
        Iterator<Integer> it = linked.iterator();
        assertThat(it.next()).isEqualTo(2);
    }

    @Test
    public void whenAddThenIter() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(1);
        linked.add(2);
        Iterator<Integer> it = linked.iterator();
        assertThat(it.next()).isEqualTo(1);
        assertThat(it.next()).isEqualTo(2);
    }

    @Test
    public void whenAddAndRevertThenIter() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(1);
        linked.add(2);
        linked.revert();
        Iterator<Integer> it = linked.iterator();
        assertThat(it.next()).isEqualTo(2);
        assertThat(it.next()).isEqualTo(1);
    }

    @Test
    public void whenSize0ThenReturnFalse() {
        ForwardLinked<Integer> emptyList = new ForwardLinked<>();
        assertThat(emptyList.revert()).isFalse();
    }

    @Test
    public void whenSize1ThenReturnFalse() {
        ForwardLinked<Integer> singleList = new ForwardLinked<>();
        singleList.add(1);
        assertThat(singleList.revert()).isFalse();
    }

}