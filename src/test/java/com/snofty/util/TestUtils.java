package com.snofty.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class TestUtils {
    /**
     * Read file to string.
     *
     * @param fixtureFileUrl - string representation of url of file to read from.
     * @return - content of file as string.
     */
    public static String getTestFixtureAsString(final String fixtureFileUrl) {
        try {
            return String.join("\n", Files.readAllLines(Paths.get(fixtureFileUrl)));
        } catch (IOException ex) {
            throw new RuntimeException("Can't read file from url " + fixtureFileUrl + ".", ex);
        }
    }

    public static <T> T getTestFixtureAsObject(final String fixtureFileUrl, final Class<T> clazz) {
        try {
            return getObjectMapper().readValue(Files.newInputStream(Paths.get(fixtureFileUrl)), clazz);
        } catch (IOException ex) {
            throw new RuntimeException("Can't create class " + clazz.getName() + " from url " + fixtureFileUrl + " .", ex);
        }
    }


    public static <T> List<T> getTestFixtureAsList(final String fixtureFileUrl, final Class<T> clazz) {
        try {
            ObjectMapper mapper = getObjectMapper();
            TypeFactory factory = mapper.getTypeFactory();
            return mapper.readValue(Files.newInputStream(Paths.get(fixtureFileUrl)),
                    factory.constructCollectionType(List.class, clazz));
        } catch (IOException ex) {
            throw new RuntimeException("Can't create class " + clazz.getName() + " from url " + fixtureFileUrl + " .", ex);
        }
    }


    private static ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }

}
