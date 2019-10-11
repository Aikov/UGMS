package data.save;

import data.save.Course;

public class Student {
    public String Firstname;
    public String Lastname;
    public String StudentID;
    public float score;
    Course[] course = new Course[100];

    public void setCourseBasicInfo(String CourseName, int Credit) {
        for (Course cour : course) {
            if (cour.CourseName.equals(CourseName)) return;  // Skip init if there is the same
        }
        int i;
        for (i = 0; course[i].CourseName != null; i++) continue;
        course[i+1].CourseName = CourseName;
        course[i+1].CourseCredit = Credit;
    }
}
