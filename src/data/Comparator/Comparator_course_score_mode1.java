package data.Comparator;

import data.save.Student;

import java.util.Comparator;

public class Comparator_course_score_mode1 implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        return (int) (10 * (o1.selected.CourseScore - o2.selected.CourseScore));
    }
}
