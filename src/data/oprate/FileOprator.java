package data.oprate;

import data.save.Student;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileOprator {
    private String name;
    private String PathIn = "\\Github\\UGMS\\FileIn\\";       //TODO：交作业的时候把这里的Github删掉
    private String PathOut = "\\Github\\UGMS\\FileOut\\";
    private String PathOutput = "\\Github\\UGMS\\FileOut\\Student\\";
    private String Path;
    public Student[] students;

    public FileOprator(Student[] Info) {
        this.students = Info;
    }

    public void read(String FileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FileName));
            String FirstLine = reader.readLine();
            String[] BasicInfo = FirstLine.split("\\s");    //我为什么总是想着正则不放，WHY？
            int Num = Integer.parseInt(reader.readLine());
            for (int i = 0; i < Num; i++) {
                boolean isExist = true;
                String line = reader.readLine();
                String[] info = line.split(",");
                for (Student stu : students) {
                    if (stu.StudentID.equals(info[2])) {
                        stu.setScore(BasicInfo[0], Integer.parseInt(BasicInfo[1]), Double.parseDouble(info[3]));
                        isExist = true;
                        break;
                    } else isExist = false;
                }
                if (!isExist) {
                    int posi;
                    for (posi = 0; students[posi].StudentID != null; posi++) ;
                    students[posi].Firstname = info[0];
                    students[posi].Lastname = info[1];
                    students[posi].StudentID = info[2];
                    students[posi].setScore(BasicInfo[0], Integer.parseInt(BasicInfo[1]), Double.parseDouble(info[3]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
