package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Clocks {
    Integer hours;
    Integer minutes;
    Boolean isDay;
}
