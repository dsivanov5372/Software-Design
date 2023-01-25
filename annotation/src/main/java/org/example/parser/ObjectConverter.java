package org.example.parser;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.example.annotations.IgnoreNull;
import org.example.annotations.JsonElement;
import org.example.annotations.JsonSerializable;
import org.example.exception.ClassNotSerializableException;

public class ObjectConverter {
    public static String convert(Object object) throws ClassNotSerializableException {
        isSerializable(object);
        return toJson(object);
    }

    private static void isSerializable(Object object) {
        if (Objects.isNull(object)) {
            throw new ClassNotSerializableException("Can't serialize a null object");
        }

        Class<?> type = object.getClass();
        if (!type.isAnnotationPresent(JsonSerializable.class)) {
            throw new ClassNotSerializableException("Class is not serializable");
        }
    }

    private static String toJson(Object object) throws ClassNotSerializableException {
        try {
            Class<?> type = object.getClass();
            Map<String, String> fields = new TreeMap<>();

            for (Field field : type.getDeclaredFields()) {
                field.setAccessible(true);
                if (field.isAnnotationPresent(JsonElement.class)) {
                    String name = field.getAnnotation(JsonElement.class).name();
                    if (name.isEmpty()) {
                        name = field.getName();
                    }
                    String value = String.valueOf(field.get(object));

                    if (field.isAnnotationPresent(IgnoreNull.class)) {
                        if (!value.equals("null")) {
                            fields.put(name, value);
                        }
                    } else {
                        fields.put(name, value);
                    }
                }
                field.setAccessible(false);
            }

            return "{\n" +
                    fields.entrySet()
                          .stream()
                          .map(entry -> " \"" + entry.getKey() + "\": \"" + entry.getValue() + "\"")
                          .collect(Collectors.joining(",\n")) +
                    "\n}";
        } catch (IllegalArgumentException | IllegalAccessException ignored) {
            throw new ClassNotSerializableException("Error during serializing!");
        }
    }
}