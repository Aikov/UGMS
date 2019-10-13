package data.Comparator;

import data.save.Student;

import java.util.Comparator;

public class Comparator_StudentLName_mode1 implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        return o1.Lastname.compareTo(o2.Lastname);
    }
}
