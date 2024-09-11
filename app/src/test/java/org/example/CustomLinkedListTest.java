package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CustomLinkedListTest {
    private CustomLinkedList<Integer> list;

    @BeforeEach
    void setUp() {
        list = new CustomLinkedList<>();
    }

    @Test
    void testAdd() {
        list.add(1);
        assertEquals(1, list.getSize());
        assertEquals(1, list.get(0));
    }

    @Test
    void testGet() {
        list.add(1);
        list.add(2);
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
    }

    @Test
    void testRemove() {
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(1, list.remove(0));
        assertEquals(2, list.getSize());
        assertEquals(2, list.get(0));
    }

    @Test
    void testContains() {
        list.add(1);
        list.add(2);
        assertTrue(list.contains(1));
        assertFalse(list.contains(3));
    }

    @Test
    void testAddAll() {
        List<Integer> anotherList = List.of(4, 5, 6);
        list.addAll(anotherList);
        assertEquals(3, list.getSize());
        assertEquals(4, list.get(0));
        assertEquals(5, list.get(1));
        assertEquals(6, list.get(2));
    }

    @Test
    void testFromStream() {
        Stream<Integer> stream = Stream.of(7, 8, 9);
        CustomLinkedList<Integer> newList = CustomLinkedList.fromStream(stream);
        assertEquals(3, newList.getSize());
        assertEquals(7, newList.get(0));
        assertEquals(8, newList.get(1));
        assertEquals(9, newList.get(2));
    }

    @Test
    void testToList() {
        list.add(1);
        list.add(2);
        list.add(3);
        List<Integer> expectedList = List.of(1, 2, 3);
        assertEquals(expectedList, list.toList());
    }

    @Test
    void testSize() {
        assertEquals(0, list.getSize());
        list.add(1);
        assertEquals(1, list.getSize());
    }

    @Test
    void testRemoveFromEmptyList() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(0));
    }

    @Test
    void testGetFromEmptyList() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
    }

    @Test
    void testAddNull() {
        list.add(null);
        assertEquals(1, list.getSize());
        assertNull(list.get(0));
    }
}
