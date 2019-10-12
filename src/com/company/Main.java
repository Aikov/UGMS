package com.company;

import data.oprate.FileOprator;
import data.save.Student;

public class Main {
    public static void main(String[] args) {
        Student[] students = new Student[10000];
        for (int i = 0; i < students.length; i++) {
            students[i] = new Student();
        }
        FileOprator fileOprator = new FileOprator(students);
        fileOprator.read("Bye.txt");
        fileOprator.read("Hello.txt");
        for (int i = 0; (i < students.length) && !students[i].StudentID.equals("");i++)
            students[i].CaluateGPA();
            System.out.println(students[0].GPA);
    }
}
