package org.example;

import org.example.annotations.JsonElement;
import org.example.annotations.JsonSerializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonSerializable
public class UserSerializableCustomName {
    @JsonElement
    String name;
    @JsonElement(name = "number")
    int age;
}
