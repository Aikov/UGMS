package data.save;

import data.save.Course;

public class Student {
    public String Firstname;
    public String Lastname;
    public String StudentID;
    public double key;  //这个用于比较，只在select和sort中使用
    public double GPA;
    public int TotalCredit;

    public Student() {
        this.StudentID = "";
        this.Lastname = "";
        this.Firstname = "";
        for(int i=0;i<100;i++){
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
        try{
            for(Course cour:course){
                cour.CalculatePointsAndGrade();
            }
        }catch(RuntimeException e){
            e.printStackTrace();
        }
    }

    public void CalculateGPA() {
        double Ctotal = 0;
        double Ptotal = 0;
        for (Course cour : course) {
            if (!cour.CourseName.equals("")) {
                Ctotal += cour.CourseCredit * cour.CoursePoints;
                Ptotal += cour.CourseCredit;
            } else break;
        }
        this.GPA = Ctotal / Ptotal;
        this.GPA = Ptotal;
    }
}
