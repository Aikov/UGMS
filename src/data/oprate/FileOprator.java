package data.oprate;

import data.save.Student;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileOprator {
    private String name;
    private String PathIn = "\\Github\\UGMS\\FileIn\\";       //TODO：交作业的时候把这里的Github删掉
    private String PathOut = "\\Github\\UGMS\\FileOut\\";
    private String Path;

    public FileOprator(String Filename, int mode) {
        // Mode 0 : read the file processed by program
        // Mode 1 : read the given file
        // Mode 2 : Output file
        this.name = Filename;
        if (1 == mode) this.Path = PathIn + this.name;
        else this.Path = PathOut + this.name;
    }

    public void read(Student[] students) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.Path));
            String FirstLine = reader.readLine();
            String[] BasicInfo = FirstLine.split("\\s");    //我为什么总是想着正则不放，WHY？
            reader.readLine();
            for (Student stu : students) {
                stu.setCourseBasicInfo(BasicInfo[0], Integer.parseInt(BasicInfo[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
