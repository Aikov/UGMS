package com.company;

import data.oprate.FileOperator;
import data.oprate.Tool;
import data.save.Student;

import java.awt.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Student[] students = new Student[10000];
        FileOperator fileOperator = new FileOperator(students);
        Tool t = new Tool();
        for (int i = 0; i < students.length; i++) {
            students[i] = new Student();
        }
        do {
            System.out.print("Please enter the database filename:");
            String course = input.nextLine();
            System.out.println("Do you have another score report?\n Press \"Y\" for Yes and \"N\" for No");
            fileOperator.read(course);
        } while (input.nextLine().equals("Y"));
        Student[] selected = t.select_score(students, "CN105");
        t.Sort_Course(selected, 1);
        /*for (Student stu : selected) {
            if (stu.StudentID.equals(""))
                break;
            else System.out.println(stu.StudentID + " " + stu.selected.CourseScore);
        }*/
        print_course(selected);
    }

    private static void print_course(Student[] info) {
        System.out.println("Course Code: " + info[0].selected.CourseName);
        System.out.println("Credit: " + info[0].selected.CourseCredit);
        System.out.println("Number of Students: " + info.length);
        System.out.println("Name               ID                       Score      Grade ");
        System.out.println("--------------     ------------------       ------     ------");
        for (Student stu : info) {
            String name = stu.FirstName + "," + stu.Surname;
            System.out.printf("%-14s    ", name);
            System.out.printf("%19s       ", stu.StudentID);
            System.out.printf("%6s     ", stu.selected.CourseScore);
            System.out.printf("%-6s\n", stu.selected.CourseGrade);
        }
    }
}
