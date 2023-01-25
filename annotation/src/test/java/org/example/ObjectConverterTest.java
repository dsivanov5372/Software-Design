package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

import org.example.exception.ClassNotSerializableException;
import org.example.parser.ObjectConverter;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ObjectConverterTest {
    public Stream<Arguments> messages() {
        return Stream.of(
            Arguments.of("Class is not serializable", new User("Name", 21)),
            Arguments.of("Can't serialize a null object", null)
        );
    }

    @ParameterizedTest
    @MethodSource("messages")
    public void throwsExceptionIfNotSerializable(String message, Object object) {
        ClassNotSerializableException exception = assertThrows(ClassNotSerializableException.class, () -> {
            ObjectConverter.convert(object);
        });
        assertEquals(message, exception.getMessage());
    }

    public Stream<Arguments> classes() {
        return Stream.of(
                Arguments.of(new UserSerializable("name", 21),
                             "{\n" + " \"age\": \"21\",\n" + " \"name\": \"name\"\n" + "}"),
                Arguments.of(new UserSerializable(null, 21),
                             "{\n" + " \"age\": \"21\",\n" + " \"name\": \"null\"\n" + "}"),
                Arguments.of(new UserSerializableNull(null, 21),
                             "{\n" + " \"age\": \"21\"\n" + "}"),
                Arguments.of(new UserSerializableNull("name", 21),
                             "{\n" + " \"age\": \"21\",\n" + " \"name\": \"name\"\n" + "}"),
                Arguments.of(new UserSerializableCustomName("name", 21),
                             "{\n" + " \"name\": \"name\",\n" + " \"number\": \"21\"\n" + "}")
        );
    }

    @ParameterizedTest
    @MethodSource("classes")
    public void serializeIfSerializable(Object object, String expected) {
        assertEquals(ObjectConverter.convert(object), expected);
    }
}