package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Stream;


public class App {
    private static final Logger logger = LoggerFactory.getLogger(App.class);
    private static final String inputFilePath = "src/main/resources/city.json";
    private static final String outputFilePath = "src/main/resources/city.xml";

    public static void main(String[] args) {
        Parser.parse(inputFilePath, outputFilePath);
        CustomLinkedList<Integer> list = new CustomLinkedList<>();

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        System.out.println("List after adding elements: " + list.toList());

        System.out.println("Element at index 0: " + list.get(0));
        System.out.println("Element at index 3: " + list.get(3));

        System.out.println("Removed element at index 0: " + list.remove(0));
        System.out.println("List after removing element: " + list.toList());

        System.out.println("List contains 2: " + list.contains(2));
        System.out.println("List contains 6: " + list.contains(6));

        List<Integer> anotherList = List.of(6, 7, 8);
        list.addAll(anotherList);
        System.out.println("List after adding all elements with list: " + list.toList());

        Stream<Integer> stream = Stream.of(9, 10, 11);
        CustomLinkedList<Integer> newList = CustomLinkedList.fromStream(stream);
        System.out.println("New list from stream: " + newList.toList());
        System.out.println("The size of list : " + newList.getSize());


    }

}
