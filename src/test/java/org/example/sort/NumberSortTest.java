package org.example.sort;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NumberSortTest {
    private static final int RANDOM_TEST_SIZE = 1_000_000;

    @Test
    public void randomMixedNumberSortWithComparator() {
        List<Number> list = generateRandomMixedNumberList();
        List<Number> expectedList = new ArrayList<>(list);
        expectedList.sort(Comparator.comparingDouble(Number::doubleValue).reversed());

        Sorter<Number> sorter = new SorterImpl<>();
        sorter.sort(list, Comparator.comparingDouble(Number::doubleValue).reversed());

        assertEquals(expectedList, list);
    }

    private List<Number> generateRandomMixedNumberList() {
        List<Number> list = new ArrayList<>(RANDOM_TEST_SIZE);
        Random random = new Random();
        for (int i = 0; i < RANDOM_TEST_SIZE; i++) {
            list.add(generateRandomNumber(random));
        }
        return list;
    }

    private Number generateRandomNumber(Random random) {
        int type = random.nextInt(4);
        switch (type) {
            case 0:
                return random.nextInt();
            case 1:
                return random.nextDouble() * 1000;
            case 2:
                return random.nextFloat() * 1000;
            case 3:
                return random.nextLong();
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
    }
}
