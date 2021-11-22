package GJ.EE.domain;

import java.util.Arrays;
import java.util.List;

public class StudentValues {
    public static Student JANSSENS = Student.builder().lastName("Janssens").yearOfBirth(1979).build();
    public static Student JANSSENS2 = Student.builder().lastName("Janssens").yearOfBirth(1976).build();
    public static Student KLAASSEN = Student.builder().lastName("Klaassen").yearOfBirth(1985).build();
    public static Student PIETERSEN = Student.builder().lastName("Pietersen").yearOfBirth(1931).build();

    public static List<Student> STUDENTS = Arrays.asList(JANSSENS, JANSSENS2, KLAASSEN, PIETERSEN);
}
