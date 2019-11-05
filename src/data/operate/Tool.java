/*
 *Assignment University Grade Management
 *Author: Du, Zhaolong
 *Student ID: 1809853D-I011-0073
 *Course:LP002
 *E-Mail: 1809853di011007@student.must.edu.mo
 *
 * Description:
 * This file is designed to store all method of select and sort
 * so that why it`s called Tool
 *
 * I still don`t know how to write a method only provide to a Object Array
 * So I decide package them all in a class, this class Tool
 *
 * I think method name in this class is clear enough
 * for every to under stand function of each method
 */
package data.operate;

import data.Comparator.*;
import data.save.Student;

import java.util.Arrays;

public class Tool {
    public Student[] select_course(Student[] all, String CourseName) {
        //这个函数的作用是根据课程名称选择学生
        int count = 0;
        int[] pos = new int[all.length + 1];  // +1 防炸
        int k = 0;
        for (int i = 0; (i < all.length) && (!all[i].StudentID.equals("")); i++) {  //这里的equals主要是为了减少时间消耗
            for (int j = 0; (j < all[i].course.length) && (!all[i].course[j].CourseName.equals("")); j++)  //同上
                if (all[i].course[j].CourseName.equals(CourseName)) {
                    count++;
                    all[i].selected = all[i].course[j];
                    pos[k] = i + 1;
                    k++;
                    break;
                }
        }
        Student[] Tar = new Student[count];
        for (k = 0; pos[k] != 0; k++) {
            Tar[k] = all[pos[k] - 1];
        }
        return Tar;
    }

    public void Sort_Course(Student[] students, int Mode) {
        //@param Mode:Mode 1 从小到大 ，Mode 2 从大到小 排序依据是students.selected
        if (Mode == 1) {
            Arrays.sort(students, new Comparator_course_score_mode1());
        } else if (Mode == 2) {
            Arrays.sort(students, new Comparator_course_score_mode2());
        }
    }

    public Student[] select_student(Student[] all) {         //这个是用来找有用的数据段的
        int count = 0;                                      //省得后面排序的时候炸掉
        for (int i = 0; (i < all.length) && (!all[i].StudentID.equals("")); i++)
            count++;
        int k = 0;
        Student[] Tar = new Student[count];
        for (int i = 0; (i < all.length) && (!all[i].StudentID.equals("")); i++) {
            Tar[k] = all[i];
            k++;
        }
        return Tar;
    }

    public void Sort_StudentFName(Student[] all, int Mode) {
        if (Mode == 1) {
            Arrays.sort(all, new Comparator_StudentFName_mode1());
        } else if (Mode == 2) {
            Arrays.sort(all, new Comparator_StudentFName_mode2());
        }
    }

    public void Sort_StudentID(Student[] all, int Mode) {
        if (Mode == 1) {
            Arrays.sort(all, new Comparator_StudentID_mode1());
        } else if (Mode == 2) {
            Arrays.sort(all, new Comparator_StudentID_mode2());
        }
    }

    public void Sort_StudentLName(Student[] all, int Mode) {
        if (Mode == 1) {
            Arrays.sort(all, new Comparator_StudentLName_mode1());
        } else if (Mode == 2) {
            Arrays.sort(all, new Comparator_StudentLName_mode2());
        }
    }

    public void Sort_GPA(Student[] all, int Mode) {
        if (Mode == 1) {
            Arrays.sort(all, new Comparator_GPA_Mode1());
        } else if (Mode == 2) {
            Arrays.sort(all, new Comparator_GPA_Mode2());
        }
    }

    public int[] listGrade(Student[] body) {        //这个函数是实现统计某一门课等级数量的
        int[] Res = new int[12];
        for (Student stu : body) {
            switch (stu.selected.CourseGrade) {
                case "A+":
                    Res[0]++;
                    break;
                case "A":
                    Res[1]++;
                    break;
                case "A-":
                    Res[2]++;
                    break;
                case "B+":
                    Res[3]++;
                    break;
                case "B":
                    Res[4]++;
                    break;
                case "B-":
                    Res[5]++;
                    break;
                case "C+":
                    Res[6]++;
                    break;
                case "C":
                    Res[7]++;
                    break;
                case "C-":
                    Res[8]++;
                    break;
                case "D":
                    Res[9]++;
                    break;
                case "F":
                    Res[10]++;
                    break;
                case "O":
                    Res[11]++;
                    break;
            }
        }
        return Res;
    }

    public double[] getScore(Student[] body) {
        /*
         * :param body:所有的数据
         * :return :一个数组，依次是最大最小和平均值
         */
        double[] Res = new double[3];
        double max = 0, min = 100;
        double all = 0;
        for (Student stu : body) {
            if (stu.selected.CourseScore > max) max = stu.selected.CourseScore;
            if (stu.selected.CourseScore < min) min = stu.selected.CourseScore;
            all += stu.selected.CourseScore;
        }
        Res[0] = max;
        Res[1] = min;
        Res[2] = all / body.length;
        return Res;
    }

    //没有找到的话返回空
    public Student SingleSelect(Student[] body, String ID) {
        //使用这个函数之前，请一定一定一定要使用select_student，不然可能会炸掉
        for (Student stu : body) {
            if (ID.equals(stu.StudentID)) return stu;
        }
        return new Student();
    }

    public Student[] SurNameSelect(Student[] body, String Surname) {
        int count = 0;
        for (int i = 0; (i < body.length) && (!body[i].StudentID.equals("")); i++) {
            if (Surname.equals(body[i].Surname)) count++;
        }
        Student[] Tar = new Student[count];
        int k = 0;
        for (int i = 0; (i < body.length) && (!body[i].StudentID.equals("")); i++) {
            if (Surname.equals(body[i].Surname)) {
                Tar[k] = body[i];
                k++;
            }
        }
        return Tar;
    }

    public Student[] GPASelect(Student[] body, Double GPA) {
        int count = 0;
        for (Student stu : body) {
            if (stu.GPA == GPA) count++;
        }
        Student[] Tar = new Student[count];
        int k = 0;
        for (Student student : body) {
            if (student.GPA == GPA) {
                Tar[k] = student;
                k++;
            }
        }
        return Tar;
    }


    public Student[] RangeSearch(Student[] all, int mode, double Limit) {
        //param : mode 1 >  & mode 2 <
        int[] pos = new int[all.length + 1];     //老样子 +1防炸
        int k = 0, count = 0;
        for (int i = 0; i < all.length; i++) {
            if (mode == 1) {
                if (all[i].GPA >= Limit) {
                    pos[k] = i + 1;
                    k++;
                    count++;
                }
            } else {
                if (all[i].GPA < Limit) {
                    pos[k] = i + 1;
                    k++;
                    count++;
                }
            }
        }
        Student[] Tar = new Student[count];
        for (k = 0; pos[k] != 0; k++){
            Tar[k] = all[pos[k] - 1];
        }
        return Tar ;
    }

}
