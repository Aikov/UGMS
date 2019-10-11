package data.save;

import data.save.Course;

public class Student {
    public String Firstname;
    public String Lastname;
    public String StudentID;
    public double GPA;
    Course[] course = new Course[100];

    public boolean setCourseBasicInfo(String CourseName, int Credit) {
        for (Course cour : course) {
            if (cour.CourseName.equals(CourseName)) return false;  // Skip init if there is the same
        }
        int i;
        for (i = 0; course[i].CourseName != null; i++) continue;
        course[i + 1].CourseName = CourseName;
        course[i + 1].CourseCredit = Credit;
        return true;
    }

    public void setScore(String CourseName, int CourseCredit, double Score) {
        boolean isExist = true;
        for (Course cour : course) {
            if (cour.CourseName.equals(CourseName)) {
                cour.CourseScore = Score;
                isExist = true;
                break;
            } else isExist = false;
        }
        if (!isExist) {
            int posi;
            for (posi = 0; course[posi].CourseName != null; posi++) ;
            course[posi].CourseName = CourseName;
            course[posi].CourseCredit = CourseCredit;
            course[posi].CourseScore = Score;
        }
    }

    public void CaluateGPA() {
        double Ctotal = 0;
        double Ptotal = 0;
        for (Course cour : course) {
            if (cour.CourseName != null) {
                Ctotal += cour.CourseCredit * cour.CourseScore;
                Ptotal += cour.CourseCredit;
            } else break;
        }
        this.GPA = Ctotal / Ptotal;
    }
}
