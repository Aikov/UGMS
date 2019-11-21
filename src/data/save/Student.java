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
    public Course selected; //For sort and calc only used in LISTGRADE and SELECT

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

    public boolean setCourseBasicInfo(String CourseName, int Credit) {      //A old method that never used
        for (Course cour : course) {                                        //with coding goes on , i realize my primary struct is wrong
            if (cour.CourseName.equals(CourseName)) return false;           //but i dont want to del it
            // Skip init if there is the same
        }
        int i;
        for (i = 0; course[i].CourseName != null; i++) continue;
        course[i + 1].CourseName = CourseName;
        course[i + 1].CourseCredit = Credit;
        return true;
    }

    public void setScore(String CourseName, int CourseCredit, double Score) {
        boolean isExist = true;
        for (Course cour : course) {                      //to check course exist?
            if (CourseName.equals(cour.CourseName)) {     //if yes , change the score
                cour.CourseScore = Score;
                isExist = true;
                break;
            } else isExist = false;
        }
        if (!isExist) {                                  //if no build a new Course
            int posi;                                    //in fact , it will be redundant if no Score change require
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
        toTold.append(this.Surname).append(" ").append(this.FirstName).append(" ").append(this.StudentID).append("\n");
        for (int i = 0; i < course.length && (!course[i].CourseName.equals("")); i++){
            toTold.append(course[i].CourseName).append(" ").append(course[i].CourseGrade).append("\n");
        }
        toTold.append("GPA: ").append(this.GPA);
        return toTold.toString();
    }
}
