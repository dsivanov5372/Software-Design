package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalTime;

@Data
@AllArgsConstructor
public class DigitalWatch {
    LocalTime currTime;
}
