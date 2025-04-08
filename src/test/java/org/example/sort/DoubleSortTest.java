package org.example.sort;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DoubleSortTest {
    private static final int RANDOM_TEST_SIZE = 1_000_000;

    @Test
    public void testSort() {
        List<Double> list = new ArrayList<>(List.of(5.5, 3.3, 1.1, 2.2, 4.4));
        Sorter<Double> sorter = new SorterImpl<>();
        sorter.sort(list);
        assertEquals(List.of(1.1, 2.2, 3.3, 4.4, 5.5), list);
    }

    @Test
    public void testSortWithComparator() {
        List<Double> list = new ArrayList<>(List.of(5.5, 3.3, 1.1, 2.2, 4.4));
        Sorter<Double> sorter = new SorterImpl<>();
        sorter.sort(list, Comparator.reverseOrder());
        assertEquals(List.of(5.5, 4.4, 3.3, 2.2, 1.1), list);
    }

    @Test
    public void randomTestSort() {
        List<Double> list = generateRandomList();
        List<Double> expectedList = new ArrayList<>(list);
        Collections.sort(expectedList);

        Sorter<Double> sorter = new SorterImpl<>();
        sorter.sort(list);

        assertEquals(expectedList, list);
    }

    @Test
    public void randomTestSortWithComparator() {
        List<Double> list = generateRandomList();
        List<Double> expectedList = new ArrayList<>(list);
        expectedList.sort(Comparator.reverseOrder());

        Sorter<Double> sorter = new SorterImpl<>();
        sorter.sort(list, Comparator.reverseOrder());

        assertEquals(expectedList, list);
    }

    private List<Double> generateRandomList() {
        List<Double> list = new ArrayList<>(RANDOM_TEST_SIZE);
        Random random = new Random();
        for (int i = 0; i < RANDOM_TEST_SIZE; i++) {
            list.add(random.nextDouble() * 1000); // Generate random doubles between 0 and 1000
        }
        return list;
    }
}
