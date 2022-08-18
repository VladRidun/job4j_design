package ru.job4j.collection;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SimpleQueueTest {

    @Test
    public void whenPushPoll() {
        SimpleQueue<Integer> queue = new SimpleQueue<Integer>();
        queue.push(1);
        int rsl = queue.poll();
        assertThat(rsl).isEqualTo(1);
    }

    @Test
    public void when2PushPoll() {
        SimpleQueue<Integer> queue = new SimpleQueue<Integer>();
        queue.push(1);
        queue.push(2);
        int rsl = queue.poll();
        assertThat(rsl).isEqualTo(1);
    }

    @Test
    public void when2PushPollPushPoll() {
        SimpleQueue<Integer> queue = new SimpleQueue<>();
        queue.push(1);
        queue.poll();
        queue.push(2);
        int rsl = queue.poll();
        assertThat(rsl).isEqualTo(2);
    }

    @Test
    public void whenEmptyPoll() {
        assertThrows(NoSuchElementException.class,
                () -> {
        SimpleQueue<Integer> queue = new SimpleQueue<>();
        queue.poll();
                });
    }

    @Test
    public void whenPushPushPollAndPush() {
        SimpleQueue<Integer> queue = new SimpleQueue<>();
        queue.push(1);
        queue.push(2);
        queue.poll();
        queue.push(3);
        assertThat(queue.poll()).isEqualTo(2);
    }
}