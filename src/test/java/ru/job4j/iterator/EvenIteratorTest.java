package ru.job4j.iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class EvenIteratorTest {

    private Iterator<Integer> it;
    @BeforeEach
    public void setUp() {
        it = new EvenNumbersIterator(new int[]{1, 2, 3, 4, 5, 6, 7});
    }

    @Test
    public void shouldReturnEvenNumbersSequentially() {
        assertThrows(NoSuchElementException.class,
                () -> {
                    assertThat(it.hasNext()).isTrue();
                    assertThat(it.next()).isEqualTo(2);
                    assertThat(it.hasNext()).isTrue();
                    assertThat(it.next()).isEqualTo(4);
                    assertThat(it.hasNext()).isTrue();
                    assertThat(it.next()).isEqualTo(6);
                    assertThat(it.hasNext()).isFalse();
                    it.next();
                });
    }

    @Test
    public void sequentialHasNextInvocationDoesntAffectRetrievalOrder() {
        assertThat(it.hasNext()).isTrue();
        assertThat(it.hasNext()).isTrue();
        assertThat(it.next()).isEqualTo(2);
        assertThat(it.next()).isEqualTo(4);
        assertThat(it.next()).isEqualTo(6);
    }

    @Test
    public void shouldReturnFalseIfNoAnyEvenNumbers() {
        it = new EvenNumbersIterator(new int[]{1});
        assertThat(it.hasNext()).isFalse();
    }

    @Test
    public void allNumbersAreEven() {
        it = new EvenNumbersIterator(new int[]{2, 4, 6});
        assertThat(it.hasNext()).isTrue();
        assertThat(it.next()).isEqualTo(2);
        assertThat(it.hasNext()).isTrue();
        assertThat(it.next()).isEqualTo(4);
        assertThat(it.hasNext()).isTrue();
        assertThat(it.next()).isEqualTo(6);
        assertThat(it.hasNext()).isFalse();
    }
}