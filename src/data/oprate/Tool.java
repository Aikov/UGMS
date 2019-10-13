package data.oprate;

import data.Comparator.*;
import data.save.Student;

import java.util.Arrays;

public class Tool {
    public Student[] select_score(Student[] all, String CourseName) {
        int count = 0;
        for (int i = 0; (i < all.length) && (!all[i].StudentID.equals("")); i++) {  //这里的equals主要是为了减少时间消耗
            for (int j = 0; (j < all[i].course.length) && (!all[i].course[j].CourseName.equals("")); j++)  //同上
                if (all[i].course[j].CourseName.equals(CourseName)) count++;
        }
        Student[] Tar = new Student[count];
        int k = 0;
        for (int i = 0; (i < all.length) && (!all[i].StudentID.equals("")); i++) {  //这里的equals主要是为了减少时间消耗
            for (int j = 0; (j < all[i].course.length) && (!all[i].course[j].CourseName.equals("")); j++)  //同上
                if (all[i].course[j].CourseName.equals(CourseName)) {
                    Tar[k] = all[i];
                    Tar[k].key = all[i].course[j].CourseScore;       //将比较的key设置成需要比较的分数
                    k++;
                }
        }
        return Tar;
    }

    public void Sort_Course(Student[] students, int Mode) {
        //@param Mode:Mode 1 从大到小 ，Mode 2 从小到大 排序依据是students.key
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

    public void Sort_StdentLName(Student[] all, int Mode) {
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

}
