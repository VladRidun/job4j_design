package ru.job4j.iterator;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).isEqualTo(Arrays.asList(1, 2, 3));
    }

    @Test
    public void whenAddBeforeWithInvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class,
                () -> {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
                });
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertThat(input).isEqualTo(Arrays.asList(0, 1, 2, 3));
    }

    @Test
    public void whenRemoveIf() {
        List<Integer> input = new ArrayList<>(Arrays.asList(2, 3, 2, 4));
        ListUtils.removeIf(input, i -> i > 2);
        assertThat(input).isEqualTo(Arrays.asList(2, 2));
    }

    @Test
    public void whenReplaceIf() {
        List<Integer> input = new ArrayList<>(Arrays.asList(2, 3, 2, 4));
        ListUtils.replaceIf(input, i -> i > 2, 1);
        assertThat(input).isEqualTo(Arrays.asList(2, 1, 2, 1));
    }

    @Test
    public void whenRemoveAll() {
        List<Integer> input = new ArrayList<>(Arrays.asList(2, 5, 3, 2, 8, 6, 4));
        List<Integer> elements = new ArrayList<>(Arrays.asList(2, 4));
        ListUtils.removeAll(input, elements);
        assertThat(input).isEqualTo(Arrays.asList(5, 3, 8, 6));
    }

}