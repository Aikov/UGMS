package data;

import java.lang.RuntimeException;

public class Course {
    public String CourseName;
    public int CourseCredit;
    public double CourseScore;
    public double CoursePoints;
    public String CourseGrade;

    //我也许真的该找个人帮我把这个函数写完，So Boring
    public void CalculatePointsAndGrade() {
        if (CourseScore < 0 || CourseScore > 100) {
            throw new RuntimeException();
        } else if (CourseScore >= 93) {
            setGradeAndPoint("A+", 4.0);
        } else if (CourseScore >= 88) {
            setGradeAndPoint("A", 3.7);
        } else if (CourseScore >= 83) {
            setGradeAndPoint("A-", 3.3);
        } else if (CourseScore >= 78) {
            setGradeAndPoint("B+", 3.0);
        } else if (CourseScore >= 72) {
            setGradeAndPoint("B", 2.7);
        } else if (CourseScore >= 68) {
            setGradeAndPoint("B-", 2.3);
        } else if (CourseScore >= 63) {
            setGradeAndPoint("C+", 2.0);
        } else if (CourseScore >= 58) {
            setGradeAndPoint("C", 1.7);
        } else if (CourseScore >= 53) {
            setGradeAndPoint("C-", 1.3);
        } else if (CourseScore >= 50) {
            setGradeAndPoint("D", 1.0);
        } else if (CourseScore >= 40) {
            setGradeAndPoint("F", 0.0);
        } else {
            setGradeAndPoint("O", 0.0);
        }
    }

    private void setGradeAndPoint(String Grade, double Point) {
        this.CourseGrade = Grade;
        this.CoursePoints = Point;
    }
}

