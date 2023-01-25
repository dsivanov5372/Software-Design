package org.example;

import org.example.annotations.IgnoreNull;
import org.example.annotations.JsonElement;
import org.example.annotations.JsonSerializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonSerializable
public class UserSerializableNull {
    @JsonElement
    @IgnoreNull
    String name;
    @JsonElement
    int age;
}