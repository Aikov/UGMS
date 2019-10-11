package com.company;

import data.oprate.FileOprator;
import data.save.Student;

public class Main {
    public static void main(String[] args) {
        Student[] students;
        students = new Student[10000];
        for (int i = 0; i < 10000; i++) {
            students[i] = new Student();
        }

    }
}
