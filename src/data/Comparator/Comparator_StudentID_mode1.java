package data.Comparator;

import data.save.Student;

import java.util.Comparator;

public class Comparator_StudentID_mode1 implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        return o1.StudentID.compareTo(o2.StudentID);
    }
}
