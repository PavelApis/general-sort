package org.example.sort;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IntegerSortTest {
    private static final int RANDOM_TEST_SIZE = 1_000_000;

    @Test
    public void testSort() {
        List<Integer> list = new ArrayList<>(List.of(5, 3, 1, 2, 4));
        Sorter<Integer> sorter = new SorterImpl<>();
        sorter.sort(list);
        assertEquals(List.of(1, 2, 3, 4, 5), list);
    }

    @Test
    public void testSortWithComparator() {
        List<Integer> list = new ArrayList<>(List.of(5, 3, 1, 2, 4));
        Sorter<Integer> sorter = new SorterImpl<>();
        sorter.sort(list, Comparator.reverseOrder());
        assertEquals(List.of(5, 4, 3, 2, 1), list);
    }

    @Test
    public void randomTestSort() {
        List<Integer> list = generateRandomList();
        List<Integer> expectedList = new ArrayList<>(list);
        Collections.sort(expectedList);

        Sorter<Integer> sorter = new SorterImpl<>();
        sorter.sort(list);

        assertEquals(expectedList, list);
    }

    @Test
    public void randomTestSortWithComparator() {
        List<Integer> list = generateRandomList();
        List<Integer> expectedList = new ArrayList<>(list);
        expectedList.sort(Comparator.reverseOrder());

        Sorter<Integer> sorter = new SorterImpl<>();
        sorter.sort(list, Comparator.reverseOrder());

        assertEquals(expectedList, list);
    }

    private List<Integer> generateRandomList() {
        List<Integer> list = new ArrayList<>(IntegerSortTest.RANDOM_TEST_SIZE);
        Random random = new Random();
        for (int i = 0; i < IntegerSortTest.RANDOM_TEST_SIZE; i++) {
            list.add(random.nextInt());
        }
        return list;
    }
}
