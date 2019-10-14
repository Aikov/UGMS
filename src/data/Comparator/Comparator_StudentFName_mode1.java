package data.Comparator;

import data.save.Student;

import java.util.Comparator;

public class Comparator_StudentFName_mode1 implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        return o1.FirstName.compareTo(o2.FirstName);
    }
}
