package hse.homework.second;

import hse.homework.second.model.Student;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<Student, Integer> studentsMarks = new HashMap<>();
        Set<Student> notMarkedStudents = new TreeSet<>();
        Set<Student> students = new TreeSet<>();

        while (true) {
            printMenu();

            int command = Integer.parseInt(scanner.next());
            switch (command) {
                case 1 -> printStudents(students);
                case 2 -> {
                    Student student = randomChoose(notMarkedStudents);
                    if (student != null) {
                        isStudentAbsent(student, scanner);
                        if (student.isAbsent()) {
                            studentsMarks.put(student, 0);
                        } else {
                            setMark(student, studentsMarks, scanner);
                        }
                    }
                }
                case 3 -> {
                    enterStudents(students, scanner);
                    notMarkedStudents = new TreeSet<>(students);
                }
                case 4 -> printStudentsMarks(studentsMarks);
                case 0 -> {
                    scanner.close();
                    return;
                }
                default -> System.out.println("Неверный номер команды!");
            }
        }
    }

    private static void printMenu() {
        System.out.println("Доступные команды:");
        System.out.println("1. Печать списка студентов");
        System.out.println("2. Случайный выбор студента для оценивания");
        System.out.println("3. Ввод списка студентов");
        System.out.println("4. Печать оценок студентов");
        System.out.println("0. Выход из программы");
    }

    private static void printStudentsMarks(Map<Student, Integer> studentsMarks) {
        if (studentsMarks.isEmpty()) {
            System.out.println("Сначала введите список студентов!");
            return;
        }

        for (Map.Entry<Student, Integer> entry : studentsMarks.entrySet()) {
            System.out.println(entry.getKey() + ", оценка: " + entry.getValue());
        }
    }

    private static void enterStudents(Set<Student> students, Scanner scanner) {
        while (true) {
            System.out.println("Введите число студентов:");
            int count = Integer.parseInt(scanner.next());
            if (count < 0) {
                System.out.println("Неправильное число студентов!");
            } else {
                for (int i = 0; i < count; ++i) {
                    System.out.println("Введите имя:");
                    String name = scanner.next();
                    System.out.println("Введите фамилию:");
                    String surname = scanner.next();

                    students.add(Student.builder()
                                        .name(name)
                                        .surname(surname)
                                        .build());
                }
                break;
            }
        }
    }

    private static void setMark(Student student,  Map<Student, Integer> studentsMarks, Scanner scanner) {
        while (true) {
            System.out.println("Введите оценку студента от 0 до 10");
            int mark = Integer.parseInt(scanner.next());
            if (mark >= 0 && mark <= 10) {
                studentsMarks.put(student, mark);
                break;
            } else {
                System.out.println("Неправильная оценка!");
            }
        }
    }

    private static void isStudentAbsent(Student student, Scanner scanner) {
        System.out.println("Присутствовал ли студент на паре?");
        System.out.println("1. Да");
        System.out.println("2. Нет");

        int command = Integer.parseInt(scanner.next());
        while (true) {
            switch (command) {
                case 1 -> {
                    student.setAbsent(false);
                    return;
                }
                case 2 -> {
                    student.setAbsent(true);
                    return;
                }
                default -> System.out.println("Неправильная команда!");
            }
        }
    }

    private static Student randomChoose(Set<Student> students) {
        if (students.size() == 0) {
            System.out.println("Сначала введите список студентов!");
            return null;
        } else {
            List<Student> arrays = new ArrayList<>(students);
            Student result = arrays.get(new Random().nextInt(arrays.size()));
            students.remove(result);
            System.out.println("Выбранный студент: " + result.toString());
            return result;
        }
    }

    private static void printStudents(Set<Student> students) {
        System.out.println("Количество человек в группе: " + students.size());
        for (Student student : students) {
            System.out.println(student.toString());
        }
    }
}