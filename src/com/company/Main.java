/*
*Assignment University Grade Management
*Author: Du, Zhaolong
*Student ID: 1809853D-I011-0073
*E-Mail: 1809853di011007@student.must.edu.mo
*Course:LP002
*
* Description:
* This is the main file of the project
* Play the most important part in Input and Output
* At the same time, it call all public method in other class
*/
package com.company;

import data.operate.FileOperator;
import data.operate.Tool;
import data.save.Course;
import data.save.Student;

import java.io.IOException;
import java.util.Scanner;
import java.lang.Thread;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Student[] students = new Student[10000];
        FileOperator fileOperator = new FileOperator(students);
        Tool t = new Tool();
        for (int i = 0; i < students.length; i++) {
            students[i] = new Student();
        }
        // init above
        do {
            System.out.print("Please enter the database filename:");
            String course = input.next();
            if (!course.contains(".txt")) course += ".txt";
            System.out.println("Do you have another score report?\n Press \"Y\" for Yes and \"N\" for No");
            fileOperator.read(course);
        } while (input.next().equals("Y"));
        // get file name and read file above
        System.out.println("Reading, Please Wait");
        fileOperator = new FileOperator(t.select_student(fileOperator.students));
        students = fileOperator.students;
        System.out.println("Finished, thanks for your patient!");
        cleaner();    //For clean the screen
        System.out.println("What can I do for you next");
        System.out.println("Input 1 I will give you report on one course");
        System.out.println("Input 2 You can search data");
        System.out.println("Input 3 I will give you report of all students");
        switch (input.nextInt()) {
            case 1:
                System.out.print("Which course do you want me to report?\n");
                String course = input.next();
                System.out.println("Would you like to only have a brief?");
                Student[] select = t.select_course(students, course);
                System.out.println("(Y)es or (N)o");
                if (input.next().equals("Y")) {
                    makeStatisticsReport(select);
                    break;
                }
                System.out.println("Would you like to (S)ort, or just (R)aw report");
                switch (input.next()) {
                    case "R":
                        print_course(select);
                        break;
                    case "S":
                        System.out.println("(1) Surname; (2) ID; (3) Score; (4) Grade");
                        System.out.print("Please choose the sorting field:");
                        int field = input.nextInt(), order;
                        System.out.println("(A)scending order or (D)escending order?");
                        if (input.next().equals("A")) order = 1;
                        else order = 2;
                        switch (field) {
                            case 1:
                                t.Sort_StudentLName(select, order);
                                break;
                            case 2:
                                t.Sort_StudentID(select, order);
                                break;
                            case 3:
                            case 4:
                                t.Sort_Course(select, order);
                                break;
                        }
                        print_course(select);
                }
                System.out.println("Would you like to have brief?");
                System.out.println("(Y)es or (N)o");
                if (input.next().equals("Y")) {
                    makeStatisticsReport(select);
                }
                break;
            case 2:
                System.out.println("Which would you search depend?");
                System.out.println("(1)Student ID (2)Student Surname (3)GPA");
                switch (input.nextInt()) {
                    case 1:
                        System.out.println("Please input ID of the Student:");
                        String ID = input.next();
                        System.out.println(t.SingleSelect(students, ID).Told());
                        break;
                    case 2:
                        System.out.println("Please input Surname:");
                        String surname = input.next();
                        select = t.SurNameSelect(students, surname);
                        if (select.length == 0) {
                            System.out.println("Sorry no student found");
                            break;
                        } else for (Student stu : select) System.out.println(stu.Told());
                        break;
                    case 3:
                        System.out.println("Please input the GPA");
                        double GPA = Double.parseDouble(input.next());
                        select = t.GPASelect(students, GPA);
                        if (select.length == 0) {
                            System.out.println("Sorry no student found");
                            break;
                        } else for (Student stu : select) System.out.println(stu.Told());
                        break;
                }
                break;
            case 3:
                System.out.println("I will list GPA of all students for you.");
                System.out.println("Would you like to (S)ort, or just (R)aw report");
                switch (input.next()) {
                    case "R":
                        ListGPA(students);
                        break;
                    case "S":
                        int order;
                        System.out.println("(A)scending order or (D)escending order?");
                        if (input.next().equals("A")) order = 1;
                        else order = 2;
                        t.Sort_GPA(students, order);
                        ListGPA(students);
                        break ;
                }
                break;
        }
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

    private static void cleaner() {
        try {
            Thread.sleep(1000);
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ignored) {
        }
    }

    private static void makeStatisticsReport(Student[] select) {
        Tool t = new Tool();
        int[] Grade = t.listGrade(select);
        double[] Score = t.getScore(select);
        Course BasicInfo = select[0].selected;
        System.out.println("Course Code: " + BasicInfo.CourseName);
        System.out.println("Credit: " + BasicInfo.CourseCredit);
        System.out.println();
        System.out.printf("The average score: %.2f\n", Score[2]);
        System.out.println("The highest score: " + Score[0]);
        System.out.println("The lowest score: " + Score[1]);
        System.out.println();
        System.out.println("Course Grade Statistics:");
        System.out.println("A+: " + Grade[0]);
        System.out.println("A: " + Grade[1]);
        System.out.println("A-: " + Grade[2]);
        System.out.println("B+: " + Grade[3]);
        System.out.println("B: " + Grade[4]);
        System.out.println("B-: " + Grade[5]);
        System.out.println("C+: " + Grade[6]);
        System.out.println("C: " + Grade[7]);
        System.out.println("C-: " + Grade[8]);
        System.out.println("D: " + Grade[9]);
        System.out.println("F: " + Grade[10]);
    }

    private static void ListGPA(Student[] info) {
        System.out.println("Name               ID                       GPA     ");
        System.out.println("--------------     ------------------       ------  ");
        for (Student stu : info) {
            String name = stu.FirstName + "," + stu.Surname;
            System.out.printf("%-14s    ", name);
            System.out.printf("%19s       ", stu.StudentID);
            System.out.printf("%-6.2f\n", stu.GPA);
        }
    }
}