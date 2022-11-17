package hw_lesson_9;

import java.util.*;
import java.util.stream.Collectors;

public class MainApp {

    public static void main(String[] args) {

        List<Student> students = new ArrayList<>(Arrays.asList(

        new Student("Anton", 18, new ArrayList<>(Arrays.asList(
                new Course("English"),
                new Course("Math"),
                new Course("Programming")
        ))),

        new Student("Elena", 20, new ArrayList<>(Arrays.asList(
                new Course("English"),
                new Course("Web Design")
        ))),

        new Student("Olga", 19, new ArrayList<>(Arrays.asList(
                new Course("English"),
                new Course("Math"),
                new Course("Programming"),
                new Course("Web Design")
        ))),

        new Student("Vlad", 25, new ArrayList<>(Arrays.asList(
                new Course("English"),
                new Course("Math")
        ))),

        new Student("Anna", 21, new ArrayList<>(Arrays.asList(
                new Course("English"),
                new Course("Math"),
                new Course("Programming"),
                new Course("Web Design"),
                new Course("UI/UX")
        )))));

        //Получение уникальных названий курсов
        System.out.println(students.stream()
                .map(Student::getCourses)
                .flatMap(Collection::stream)
                .map(Course::getCourse_name)
                .collect(Collectors.toSet()));

        //Получение трех самых любознательных студентов (реализовала вывод только имен студентов)
        System.out.println(students.stream()
                .sorted((a, b) -> b.getCourses().size() - a.getCourses().size())
                .limit(3)
                .map(Student::getName)
                .collect(Collectors.toList()));

        //Получение списка студентов по курсу (реализовала вывод только имен студентов)
        System.out.println(students.stream()
                .filter(s -> s.getCourses().contains(new Course("Web Design")))
                .map(Student::getName)
                .collect(Collectors.toList()));
    }
}
