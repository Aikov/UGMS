package data.oprate;

import data.save.Student;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileOperator {
    private String PathIn;
    private String PathOut;
    private String PathOutput;
    private Student[] students;

    public FileOperator(Student[] Info) {
        File tool = new File("");
        String path = tool.getAbsolutePath();
        System.out.println(path);
        this.PathIn = path + "\\FileIn\\";
        this.PathOut = path + "\\FileOut\\";
        this.PathOutput = this.PathOut + "\\Student\\";
        this.students = Info;
    }

    public void read(String FileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(PathIn + FileName));
            String FirstLine = reader.readLine();
            String[] BasicInfo = FirstLine.split(",");    //我为什么总是想着正则不放，WHY？
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
                    int posi = 0;                                    //我开始想念SQL了
                    while (!students[posi].StudentID.equals("")){
                        posi ++;
                    }
                    students[posi].FirstName = info[0];
                    students[posi].Surname = info[1];
                    students[posi].StudentID = info[2];
                    students[posi].setScore(BasicInfo[0], Integer.parseInt(BasicInfo[1]), Double.parseDouble(info[3]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
