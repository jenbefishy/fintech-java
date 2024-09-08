package org.example;

import lombok.Getter;

import java.util.List;
import java.util.stream.Stream;

public class CustomLinkedList<T> {
    @Getter
    private int size;
    private Node<T> head;
    private Node<T> tail;

    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    public CustomLinkedList() {
        this.size = 0;
        this.head = null;
        this.tail = null;
    }

    public CustomLinkedList(T data) {
        this.head = new Node<T>(data);
        this.tail = this.head;
        this.size = 1;
    }

    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds");
        }
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds");
        }
        Node<T> current = head;
        if (index == 0) {
            head = head.next;
            if (head == null) {
                tail = null;
            }
        } else {
            Node<T> previous = null;
            for (int i = 0; i < index; i++) {
                previous = current;
                current = current.next;
            }
            previous.next = current.next;
            if (previous.next == null) {
                tail = previous;
            }
        }
        size--;
        return current.data;
    }

    public boolean contains(T data) {
        Node<T> current = head;
        for (int i = 0; i < size; i++) {
            if (current.data.equals(data)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public void addAll(List<T> list) {
        for (T data : list) {
            add(data);
        }
    }

    public List<T> toList() {
        List<T> list = new java.util.ArrayList<>();
        Node<T> current = head;
        while (current != null) {
            list.add(current.data);
            current = current.next;
        }
        return list;
    }

    public static <T> CustomLinkedList<T> fromStream(Stream<T> stream) {
        return stream.reduce(new CustomLinkedList<T>(), (list, item) -> {
            list.add(item);
            return list;
        }, (list1, list2) -> {
            list1.addAll(list2.toList());
            return list1;
        });
    }

}