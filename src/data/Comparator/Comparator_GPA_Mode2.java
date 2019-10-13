package data.Comparator;

import data.save.Student;

import java.util.Comparator;

public class Comparator_GPA_Mode2 implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        return (int) (10 * (o2.GPA - o1.GPA));
    }
}
