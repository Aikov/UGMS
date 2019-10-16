package com.company;

import data.oprate.FileOperator;
import data.oprate.Tool;
import data.save.Student;

public class Main {
    public static void main(String[] args) {
        Student[] students = new Student[10000];
        for (int i = 0; i < students.length; i++) {
            students[i] = new Student();
        }
        FileOperator fileOperator = new FileOperator(students);
        fileOperator.read("CN105.txt");
        fileOperator.read("LP002.txt");
        fileOperator.read("LP104.txt");
        fileOperator.read("MA109.txt");
        for(Student stu:students){
            if(stu.StudentID.equals(""))
                break;
            else System.out.println(stu.Told());
        }
        Tool t = new Tool();
        Student[] selected = t.select_score(students,"CN105");
        t.Sort_Course(selected,1);
        for(Student stu:selected){
            if(stu.StudentID.equals(""))
                break;
            else System.out.println(stu.StudentID + " " +stu.selected.CourseScore);
        }
    }
}
