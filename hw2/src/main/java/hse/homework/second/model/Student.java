package hse.homework.second.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Student implements Comparable<Student> {
    String name;
    String surname;
    boolean isAbsent;

    @Override
    public int compareTo(Student student) {
        if (student == null) {
            return -1;
        }

        int result = this.surname.compareTo(student.surname);
        if (result == 0) {
            return this.name.compareTo(student.name);
        }

        return result;
    }
}
