package org.example;

import org.example.annotations.JsonElement;
import org.example.annotations.JsonSerializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@JsonSerializable
@Data
@AllArgsConstructor
public class UserSerializable {
    @JsonElement
    String name;
    @JsonElement
    int age;
}