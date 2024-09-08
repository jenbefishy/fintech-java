package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class App {
    private static final Logger logger = LoggerFactory.getLogger(App.class);
    private static final String inputFilePath = "src/main/resources/city.json";
    private static final String outputFilePath = "src/main/resources/city.xml";

    public static void main(String[] args) {
        Parser.parse(inputFilePath, outputFilePath);


    }

}
