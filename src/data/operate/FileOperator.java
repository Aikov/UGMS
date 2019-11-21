/*
 *Assignment University Grade Management
 *Author: Du, Zhaolong
 *Student ID: 1809853D-I011-0073
 *Course:LP002
 *E-Mail: 1809853di011007@student.must.edu.mo
 *
 * Description:
 * This file contains FileOperator class
 * This class is designed to operate file
 * At now (2019/10/28 23:57) It only has method read()
 * which designed to read data set from tester
 * Maybe later I will write save-load method
 */
package data.operate;

import data.save.Student;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileOperator {
    private String PathIn;
    private String PathOut;
    private String PathOutput;
    public Student[] students;

    public FileOperator(Student[] Info) {
        File tool = new File("");
        String path = tool.getAbsolutePath();
        //System.out.println(path);
        String[] P = path.split("out");
        this.PathIn = P[0] + "\\FileIn\\";
        this.PathOut = P[0] + "\\FileOut\\";
        this.PathOutput = this.PathOut + "\\Student\\";
        this.students = Info;
    }

    public void read(String FileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(PathIn + FileName));
            String FirstLine = reader.readLine();
            String[] BasicInfo = FirstLine.split(",");    //Why i can forget regex
            int Num = Integer.parseInt(reader.readLine());
            for (int i = 0; i < Num; i++) {                        //here is where i fucked up
                boolean isExist = true;                            //here throws NPE first, and i dont know how to do
                String line = reader.readLine();
                String[] info = line.split(",");            //finally i got it, add init to constructor
                for (Student stu : students) {                    //find if there a student name this
                    if (info[2].equals(stu.StudentID)) {           //if there, write score
                        stu.setScore(BasicInfo[0], Integer.parseInt(BasicInfo[1]), Double.parseDouble(info[3]));
                        stu.CalculateGPA();
                        isExist = true;
                        break;
                    } else isExist = false;
                }
                if (!isExist) {                                  //if no, build new one
                    int posi = 0;                                    //oh, i start to miss SQL
                    while (!students[posi].StudentID.equals("")){
                        posi ++;
                    }
                    students[posi].FirstName = info[1];
                    students[posi].Surname = info[0];
                    students[posi].StudentID = info[2];
                    students[posi].setScore(BasicInfo[0], Integer.parseInt(BasicInfo[1]), Double.parseDouble(info[3]));
                    students[posi].CalculateGPA();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
