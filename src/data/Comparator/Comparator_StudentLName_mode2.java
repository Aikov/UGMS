/*
 *Assignment University Grade Management
 *Author: Du, Zhaolong
 *Student ID: 1809853D-I011-0073
 *E-Mail: 1809853di011007@student.must.edu.mo
 *
 * Description:
 * All files in this package are ready to call
 * Arrays.sort()
 *
 * No any other usage
 */
package data.Comparator;

import data.save.Student;

import java.util.Comparator;

public class Comparator_StudentLName_mode2 implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        return o2.Surname.compareTo(o1.Surname);
    }
}
