package ru.baib;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class KitTest {

    @Test
    public void whenOk() {
        Kit kit = new Kit();
        String[] strings = {"1-5","7","9-11"};
        int[][] actual = kit.toIntArray(strings);
        int[][] expected = new int[][]{{1,2,3,4,5},{7},{9,10,11}};
        assertThat(expected, is(actual));
        List<List<Integer>> actualComb = kit.combine(actual);
        List<List<Integer>> expectedComb = List.of(
                List.of(1, 7, 9), List.of(1, 7, 10), List.of(1, 7, 11),
                List.of(2, 7, 9), List.of(2, 7, 10), List.of(2, 7, 11),
                List.of(3, 7, 9), List.of(3, 7, 10), List.of(3, 7, 11),
                List.of(4, 7, 9), List.of(4, 7, 10), List.of(4, 7, 11),
                List.of(5, 7, 9), List.of(5, 7, 10), List.of(5, 7, 11)
                );
        assertThat(expectedComb, is(actualComb));
    }

    @Test
    public void whenOkSecond() {
        Kit kit = new Kit();
        String[] strings = {"1","7","3", "123"};
        int[][] actual = kit.toIntArray(strings);
        assertThat(new int[][]{{1},{7},{3},{123}}, is(actual));
        List<List<Integer>> actualComb = kit.combine(actual);
        List<List<Integer>> expectedComb = List.of(
                List.of(1, 7, 3, 123)
        );
        assertThat(expectedComb, is(actualComb));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenException() {
        Kit kit = new Kit();
        String[] strings = {"1-5","7","aaa"};
        kit.toIntArray(strings);
    }

    @Test
    public void whenStringArrayIsEmpty() {
        Kit kit = new Kit();
        String[] strings = new String[0];
        int[][] actual = kit.toIntArray(strings);
        assertThat(0, is(actual.length));
        List<List<Integer>> actualComb = kit.combine(actual);
        List<List<Integer>> expectedComb = List.of(List.of());
        assertThat(expectedComb, is(actualComb));
    }
}