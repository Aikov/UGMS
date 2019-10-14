package com.company;

import data.oprate.FileOperator;
import data.save.Student;

public class Main {
    public static void main(String[] args) {
        Student[] students = new Student[10000];
        for (int i = 0; i < students.length; i++) {
            students[i] = new Student();
        }
        FileOperator fileOperator = new FileOperator(students);
    }
}
