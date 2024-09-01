package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    private ObjectMapper objectMapper;
    private static final String VALID_JSON_PATH = "src/test/resources/city.json";
    private static final String INVALID_JSON_PATH = "src/test/resources/city-error.json";
    private static final String OUTPUT_XML_PATH = "src/test/resources/city.xml";

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void testValidJsonToXmlConversion() throws Exception {
        City city = objectMapper.readValue(new File(VALID_JSON_PATH), City.class);
        assertNotNull(city);
        assertEquals("spb", city.getSlug());
        assertEquals(59.939095, city.getCoords().getLat());
        assertEquals(30.315868, city.getCoords().getLon());

        String xmlOutput = App.toXML(city);
        assertNotNull(xmlOutput);
        assertTrue(xmlOutput.contains("<slug>spb</slug>"));
        assertTrue(xmlOutput.contains("<lat>59.939095</lat>"));
        assertTrue(xmlOutput.contains("<lon>30.315868</lon>"));

        Files.writeString(Path.of(OUTPUT_XML_PATH), xmlOutput);
        assertTrue(Files.exists(Path.of(OUTPUT_XML_PATH)));
    }

    @Test
    void testInvalidJsonThrowsException() {
        assertThrows(Exception.class, () -> {
            objectMapper.readValue(new File(INVALID_JSON_PATH), City.class);
        });
    }

    @Test
    void testMissingFileThrowsException() {
        assertThrows(Exception.class, () -> {
            objectMapper.readValue(new File("non_existent_file.json"), City.class);
        });
    }
}
