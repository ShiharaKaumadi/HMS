package lk.ijse.hibernate.bo.custom;

import lk.ijse.hibernate.dto.StudentDTO;

import java.sql.SQLException;
import java.util.List;

public interface StudentBo extends SuperBo{
    StudentDTO searchStudent(String id) throws SQLException, ClassNotFoundException;

    boolean addStudent(StudentDTO studentDTO) throws SQLException, ClassNotFoundException;

    boolean updateStudentDetails(StudentDTO studentDTO) throws SQLException, ClassNotFoundException;

    String generateNextStudentId() throws SQLException, ClassNotFoundException;

    boolean deleteStudent(String id) throws SQLException, ClassNotFoundException;

    List<StudentDTO> getAllItems()  throws SQLException, ClassNotFoundException;

    long countAllStudent() throws SQLException, ClassNotFoundException ;

    long countFemaleStudent() throws SQLException, ClassNotFoundException ;

    long countMaleStudent()throws SQLException, ClassNotFoundException ;
}
