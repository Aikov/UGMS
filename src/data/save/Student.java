/*
 *Assignment University Grade Management
 *Author: Du, Zhaolong
 *Student ID: 1809853D-I011-0073
 *Course:LP002
 *E-Mail: 1809853di011007@student.must.edu.mo
 *
 * Description:
 * This file contains Student class
 * the main Data-Saving class of this project
 * In this class, I declare all information I need
 * Name ID GPA and so on
 * I also write some basic method of this class
 * Such as setScore() and Told()
 * As you can see I made some mistake at first
 * method setCourseBasicInfo() is an old method
 * It become useless during project update
 */
package data.save;

public class Student {
    public String FirstName;
    public String Surname;
    public String StudentID;
    public double GPA;
    public int TotalCredit;
    public Course selected; //这个用于统计和排序，仅在select和listGrade中使用

    public Student() {
        this.StudentID = "";
        this.Surname = "";
        this.FirstName = "";
        selected = new Course();
        for (int i = 0; i < 100; i++) {
            this.course[i] = new Course();
        }
    }

    public Course[] course = new Course[100];

    public boolean setCourseBasicInfo(String CourseName, int Credit) {      //这里是个老逻辑
        for (Course cour : course) {                                        //写着写着发现自己最早架构错了，就扔了，反正也没用过，放着吧
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
        for (Course cour : course) {                      //这一段逻辑是检查学生课表里有没有这个课程
            if (CourseName.equals(cour.CourseName)) {     //有的话就直接改分数
                cour.CourseScore = Score;
                isExist = true;
                break;
            } else isExist = false;
        }
        if (!isExist) {                                  //没有的话就寻找第一个可以使用的空Course创建
            int posi;                                    //其实如果没有改分数的功能的话这里有点冗余
            for (posi = 0; !course[posi].CourseName.equals(""); posi++) ;
            course[posi].CourseName = CourseName;
            course[posi].CourseCredit = CourseCredit;
            course[posi].CourseScore = Score;
        }
        try {
            for (Course cour : course) {
                cour.CalculatePointsAndGrade();
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    public void CalculateGPA() {
        double Ctotal = 0;
        int Ptotal = 0;
        for (Course cour : course) {
            if (!cour.CourseName.equals("")) {
                Ctotal += cour.CourseCredit * cour.CoursePoints;
                Ptotal += cour.CourseCredit;
            } else break;
        }
        this.GPA = Ctotal / Ptotal;
        this.TotalCredit = Ptotal;
    }

    public String Told() {
        StringBuilder toTold = new StringBuilder("\n\n");
        toTold.append(this.FirstName).append(" ").append(this.Surname).append(" ").append(this.StudentID).append("\n");
        for (int i = 0; i < course.length && (!course[i].CourseName.equals("")); i++){
            toTold.append(course[i].CourseName).append(" ").append(course[i].CourseGrade).append("\n");
        }
        toTold.append("GPA: ").append(this.GPA);
        return toTold.toString();
    }
}
