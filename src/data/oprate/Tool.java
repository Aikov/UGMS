package data.oprate;

import data.Comparator.Comparator_course_score_mode1;
import data.Comparator.Comparator_course_score_mode2;
import data.save.Student;

import java.util.Arrays;

public class Tool {
    public Student[] select(Student[] all, String CourseName) {
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
                    Tar[k].key = all[i].course[j].CourseScore;
                    k++;
                }
        }
        return Tar;
    }

    public void Sort_Course(Student[] students, int Mode, String Coursename) {
        //@param Mode:Mode 1 从大到小 ，Mode 2 从小到大 排序依据是students.course.Score
        if (Mode == 1) {
            Arrays.sort(students, new Comparator_course_score_mode1());
        } else if (Mode == 2) {
            Arrays.sort(students, new Comparator_course_score_mode2());
        }
    }
}
