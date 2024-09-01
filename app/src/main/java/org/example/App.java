package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import com.fasterxml.jackson.core.JsonParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

public class App {
    private static final Logger logger = LoggerFactory.getLogger(App.class);
    private static final String inputFilePath = "src/main/resources/city-error.json";
    private static final String outputFilePath = "src/main/resources/city.xml";

    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            File inputFile = new File(inputFilePath);
            if (!inputFile.exists() || !inputFile.canRead()) {
                logger.error("Input file does not exist or cannot be read: {}", inputFilePath);
                return;
            }

            City city = objectMapper.readValue(inputFile, City.class);
            logger.info("Successfully loaded city data: {}", city);

            String xmlOutput = toXML(city);

            File outputFile = new File(outputFilePath);
            Files.writeString(outputFile.toPath(), xmlOutput);
            logger.info("Successfully saved XML to {}", outputFile.getAbsolutePath());

        } catch (JsonParseException e) {
            logger.error("Error parsing file", e);
        } catch (IOException e) {
            logger.error("I/O error", e);

        } catch (Exception e) {
            logger.error("Unexpected error", e);
        }
    }

    static String toXML(City city) {
        StringBuilder sb = new StringBuilder();
        sb.append("<City>\n");
        sb.append("  <slug>").append(city.getSlug()).append("</slug>\n");
        sb.append("  <coords>\n");
        sb.append("    <lat>").append(city.getCoords().getLat()).append("</lat>\n");
        sb.append("    <lon>").append(city.getCoords().getLon()).append("</lon>\n");
        sb.append("  </coords>\n");
        sb.append("</City>");
        return sb.toString();
    }
}
