package org.example;

import java.util.Objects;

public class TrafficPatrol {
    private final Integer id;

    public TrafficPatrol(Integer id) {
        this.id = id;
    }

    public void getAlert(String message) {
        System.out.println("Пост с id '" + id + "' получил сообщение:\n" + message);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
