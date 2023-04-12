package lk.ijse.hibernate.dao.custom;

import lk.ijse.hibernate.dao.util.CrudDAO;
import lk.ijse.hibernate.entity.Student;

import java.util.ArrayList;

public interface StudentDAO extends CrudDAO<Student,String> {


   

    long collectTotalStudents();

    long collectTotalFemaleStudents();

    long collectTotalMaleStudents();

    void isStudentExist();

    ArrayList<String> loadStudentIds();
}
