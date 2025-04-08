package org.example.sort;

import org.example.types.ComplexNumber;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComplexSortTest {
    private static final int RANDOM_TEST_SIZE = 1_000_000;

    @Test
    public void testSort() {
        List<ComplexNumber> list = new ArrayList<>(List.of(
                new ComplexNumber(3, 4), // Magnitude = 5
                new ComplexNumber(1, 1), // Magnitude = sqrt(2)
                new ComplexNumber(0, 2), // Magnitude = 2
                new ComplexNumber(2, 0), // Magnitude = 2
                new ComplexNumber(0, 0)  // Magnitude = 0
        ));
        Sorter<ComplexNumber> sorter = new SorterImpl<>();
        sorter.sort(list);
        assertEquals(List.of(
                new ComplexNumber(0, 0),
                new ComplexNumber(1, 1),
                new ComplexNumber(0, 2),
                new ComplexNumber(2, 0),
                new ComplexNumber(3, 4)
        ), list);
    }

    @Test
    public void testSortWithComparator() {
        List<ComplexNumber> list = new ArrayList<>(List.of(
                new ComplexNumber(3, 4), // Magnitude = 5
                new ComplexNumber(1, 1), // Magnitude = sqrt(2)
                new ComplexNumber(0, 2), // Magnitude = 2
                new ComplexNumber(2, 0), // Magnitude = 2
                new ComplexNumber(0, 0)  // Magnitude = 0
        ));
        Sorter<ComplexNumber> sorter = new SorterImpl<>();
        sorter.sort(list, Comparator.comparingDouble(ComplexNumber::real).thenComparingDouble(ComplexNumber::imaginary));
        assertEquals(List.of(
                new ComplexNumber(0, 0),
                new ComplexNumber(0, 2),
                new ComplexNumber(1, 1),
                new ComplexNumber(2, 0),
                new ComplexNumber(3, 4)
        ), list);
    }

    @Test
    public void randomTestSort() {
        List<ComplexNumber> list = generateRandomComplexNumberList();
        List<ComplexNumber> expectedList = new ArrayList<>(list);
        Collections.sort(expectedList);

        Sorter<ComplexNumber> sorter = new SorterImpl<>();
        sorter.sort(list);

        assertEquals(expectedList, list);
    }

    @Test
    public void randomTestSortWithComparator() {
        List<ComplexNumber> list = generateRandomComplexNumberList();
        List<ComplexNumber> expectedList = new ArrayList<>(list);
        expectedList.sort(Comparator.comparingDouble(ComplexNumber::real).thenComparingDouble(ComplexNumber::imaginary));

        Sorter<ComplexNumber> sorter = new SorterImpl<>();
        sorter.sort(list, Comparator.comparingDouble(ComplexNumber::real).thenComparingDouble(ComplexNumber::imaginary));

        assertEquals(expectedList, list);
    }

    private List<ComplexNumber> generateRandomComplexNumberList() {
        List<ComplexNumber> list = new ArrayList<>(RANDOM_TEST_SIZE);
        Random random = new Random();
        for (int i = 0; i < RANDOM_TEST_SIZE; i++) {
            list.add(new ComplexNumber(random.nextDouble() * 1000, random.nextDouble() * 1000));
        }
        return list;
    }
}
