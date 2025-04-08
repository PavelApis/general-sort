package org.example.sort;

import org.example.types.Client;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClientSortTest {
    private static final int RANDOM_TEST_SIZE = 1_000_000;

    @Test
    public void testSortByName() {
        List<Client> list = getSimpleTestCase();
        Sorter<Client> sorter = new SorterImpl<>();
        sorter.sort(list, Comparator.comparing(Client::name));
        assertEquals(List.of(
                new Client("Alice", 30, 1000.0, "alice@example.com"),
                new Client("Bob", 25, 1500.0, "bob@example.com"),
                new Client("Charlie", 35, 1200.0, "charlie@example.com")
        ), list);
    }

    @Test
    public void testSortByAge() {
        List<Client> list = getSimpleTestCase();
        Sorter<Client> sorter = new SorterImpl<>();
        sorter.sort(list, Comparator.comparingInt(Client::age));
        assertEquals(List.of(
                new Client("Bob", 25, 1500.0, "bob@example.com"),
                new Client("Alice", 30, 1000.0, "alice@example.com"),
                new Client("Charlie", 35, 1200.0, "charlie@example.com")
        ), list);
    }

    @Test
    public void testSortByBalance() {
        List<Client> list = getSimpleTestCase();
        Sorter<Client> sorter = new SorterImpl<>();
        sorter.sort(list, Comparator.comparingDouble(Client::balance));
        assertEquals(List.of(
                new Client("Alice", 30, 1000.0, "alice@example.com"),
                new Client("Charlie", 35, 1200.0, "charlie@example.com"),
                new Client("Bob", 25, 1500.0, "bob@example.com")
        ), list);
    }

    @Test
    public void testSortByEmail() {
        List<Client> list = getSimpleTestCase();
        Sorter<Client> sorter = new SorterImpl<>();
        sorter.sort(list, Comparator.comparing(Client::email));
        assertEquals(List.of(
                new Client("Alice", 30, 1000.0, "alice@example.com"),
                new Client("Bob", 25, 1500.0, "bob@example.com"),
                new Client("Charlie", 35, 1200.0, "charlie@example.com")
        ), list);
    }

    private List<Client> getSimpleTestCase() {
        return new ArrayList<>(List.of(
                new Client("Alice", 30, 1000.0, "alice@example.com"),
                new Client("Bob", 25, 1500.0, "bob@example.com"),
                new Client("Charlie", 35, 1200.0, "charlie@example.com")
        ));
    }

    @Test
    public void randomTestSortWithRandomComparators() {
        List<Client> list = generateRandomClientList();
        List<Client> expectedList = new ArrayList<>(list);

        List<Comparator<Client>> comparators = new ArrayList<>(List.of(
                Comparator.comparing(Client::name),
                Comparator.comparingInt(Client::age),
                Comparator.comparingDouble(Client::balance),
                Comparator.comparing(Client::email)
        ));

        Collections.shuffle(comparators);

        for (Comparator<Client> comparator : comparators) {
            expectedList.sort(comparator);
            Sorter<Client> sorter = new SorterImpl<>();
            sorter.sort(list, comparator);
            assertEquals(expectedList, list);
        }
    }

    private List<Client> generateRandomClientList() {
        List<Client> list = new ArrayList<>(RANDOM_TEST_SIZE);
        Random random = new Random();
        for (int i = 0; i < RANDOM_TEST_SIZE; i++) {
            list.add(new Client(
                    generateRandomString(random),
                    random.nextInt(100),
                    random.nextDouble() * 10000,
                    generateRandomString(random) + "@example.com"
            ));
        }
        return list;
    }

    private String generateRandomString(Random random) {
        StringBuilder sb = new StringBuilder(10);
        for (int i = 0; i < 10; i++) {
            char c = (char) ('a' + random.nextInt(26));
            sb.append(c);
        }
        return sb.toString();
    }
}
