package data.oprate;

import com.sun.xml.internal.ws.util.StringUtils;
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
            BufferedReader reader = new BufferedReader(new FileReader(PathIn + FileName));
            String FirstLine = reader.readLine();
            String[] BasicInfo = FirstLine.split("\\s");    //我为什么总是想着正则不放，WHY？
            int Num = Integer.parseInt(reader.readLine());
            for (int i = 0; i < Num; i++) {                        //这一段逻辑是我最想骂人的地方
                boolean isExist = true;                            //最早的NPE就是从这里开始报错的，找了半天不知道怎么办
                String line = reader.readLine();
                String[] info = line.split(",");            //终于我还是找到了，改了构造器，加了初始化构造
                for (Student stu : students) {                    //这里就是找找学生里面有没有那个人
                    if (info[2].equals(stu.StudentID)) {           //有的话就写分数
                        stu.setScore(BasicInfo[0], Integer.parseInt(BasicInfo[1]), Double.parseDouble(info[3]));
                        isExist = true;
                        break;
                    } else isExist = false;
                }
                if (!isExist) {                                  //没有的话就造一个
                    int posi;                                    //我开始想念SQL了
                    for (posi = 0; !students[posi].StudentID.equals(""); posi++) ;
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
