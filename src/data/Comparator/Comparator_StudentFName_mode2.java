package data.Comparator;

import data.save.Student;

import java.util.Comparator;

public class Comparator_StudentFName_mode2 implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        return o2.Firstname.compareTo(o1.Firstname);
    }
}
