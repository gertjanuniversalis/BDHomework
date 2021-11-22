package GJ.EE.services;

import GJ.EE.domain.Student;
import GJ.EE.domain.Students;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.ejb.Stateful;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;


@Stateful()
public class StudentService {

    private List<Student> students = new ArrayList<>();

    public Student getById(int id) {
        return students.get(id);
    }

    public Students getByLastName(String lastName){
        return Students.of(filterStudentBy(lastName));
    }

    public Student[] getAll() {
        return (Student[]) students.toArray();
    }

    public Student remove(int id) {
        return students.remove(id);
    }

    public boolean add(Student student) {
        return students.add(student);
    }

    private List<Student> filterStudentBy(String name) {
        return students.stream()
                .filter(s ->name.toLowerCase().contains(s.getLastName().toLowerCase()))
                .collect(toList());
    }
}
