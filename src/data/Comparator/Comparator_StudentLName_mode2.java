package data.Comparator;

import data.save.Student;

import java.util.Comparator;

public class Comparator_StudentLName_mode2 implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        return o2.Surname.compareTo(o1.Surname);
    }
}
