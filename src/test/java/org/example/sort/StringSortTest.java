package org.example.sort;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringSortTest {
    private static final int RANDOM_TEST_SIZE = 1_000_000;
    private static final int STRING_LENGTH = 10;

    @Test
    public void testSort() {
        List<String> list = new ArrayList<>(List.of("banana", "apple", "cherry"));
        Sorter<String> sorter = new SorterImpl<>();
        sorter.sort(list);
        assertEquals(List.of("apple", "banana", "cherry"), list);
    }

    @Test
    public void testSortWithComparator() {
        List<String> list = new ArrayList<>(List.of("banana", "apple", "cherry"));
        Sorter<String> sorter = new SorterImpl<>();
        sorter.sort(list, Comparator.reverseOrder());
        assertEquals(List.of("cherry", "banana", "apple"), list);
    }

    @Test
    public void randomTestSort() {
        List<String> list = generateRandomStringList();
        List<String> expectedList = new ArrayList<>(list);
        Collections.sort(expectedList);

        Sorter<String> sorter = new SorterImpl<>();
        sorter.sort(list);

        assertEquals(expectedList, list);
    }

    @Test
    public void randomTestSortWithComparator() {
        List<String> list = generateRandomStringList();
        List<String> expectedList = new ArrayList<>(list);
        expectedList.sort(Comparator.reverseOrder());

        Sorter<String> sorter = new SorterImpl<>();
        sorter.sort(list, Comparator.reverseOrder());

        assertEquals(expectedList, list);
    }

    private List<String> generateRandomStringList() {
        List<String> list = new ArrayList<>(RANDOM_TEST_SIZE);
        Random random = new Random();
        for (int i = 0; i < RANDOM_TEST_SIZE; i++) {
            list.add(generateRandomString(random));
        }
        return list;
    }

    private String generateRandomString(Random random) {
        StringBuilder sb = new StringBuilder(STRING_LENGTH);
        for (int i = 0; i < STRING_LENGTH; i++) {
            char c = (char) ('a' + random.nextInt(26));
            sb.append(c);
        }
        return sb.toString();
    }
}
