package lk.ijse.hibernate.bo.custom;

import lk.ijse.hibernate.dto.StudentDTO;
import lk.ijse.hibernate.views.tm.StudentTM;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StudentBo extends SuperBo{
    StudentDTO searchStudent(String id) throws SQLException, ClassNotFoundException;

    boolean addStudent(StudentDTO studentDTO) throws SQLException, ClassNotFoundException;

    boolean updateStudentDetails(StudentDTO studentDTO) throws SQLException, ClassNotFoundException;

    String generateNextStudentId() throws SQLException, ClassNotFoundException;

    boolean deleteStudent(String id) throws SQLException, ClassNotFoundException;

    ArrayList<StudentDTO> getAllItems()  throws SQLException, ClassNotFoundException;
}
