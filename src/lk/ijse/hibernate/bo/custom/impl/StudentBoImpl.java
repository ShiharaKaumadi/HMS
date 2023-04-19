package lk.ijse.hibernate.bo.custom.impl;

import lk.ijse.hibernate.bo.custom.StudentBo;
import lk.ijse.hibernate.dao.custom.StudentDAO;
import lk.ijse.hibernate.dao.util.DAOFactory;
import lk.ijse.hibernate.dao.util.DAOTypes;
import lk.ijse.hibernate.dto.StudentDTO;
import lk.ijse.hibernate.entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentBoImpl implements StudentBo {
    private final StudentDAO studentDaoImpl = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOTypes.STUDENT);
    @Override
    public StudentDTO searchStudent(String id) throws SQLException, ClassNotFoundException {
        Student student = studentDaoImpl.search(id);

        return new StudentDTO(student.getId(),student.getName(),student.getAdddress(),student.getContactNo(),student.getDob(),student.getGender());
    }

    @Override
    public boolean addStudent(StudentDTO studentDTO) throws SQLException, ClassNotFoundException {


           return studentDaoImpl.add(new Student(studentDTO.getStudentId(), studentDTO.getName(), studentDTO.getAddress(),
                    studentDTO.getContactNo(), studentDTO.getDob(), studentDTO.getGender()));


    }

    @Override
    public boolean updateStudentDetails(StudentDTO studentDTO) throws SQLException, ClassNotFoundException {
        return studentDaoImpl.update(new Student(studentDTO.getStudentId(),studentDTO.getName(),studentDTO.getAddress(),studentDTO.getContactNo(),studentDTO.getDob(),studentDTO.getGender()));
    }

    @Override
    public String generateNextStudentId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean deleteStudent(String id) throws SQLException, ClassNotFoundException {
        return studentDaoImpl.delete(id);
    }

    @Override
    public List<StudentDTO> getAllItems() throws SQLException, ClassNotFoundException {

        ArrayList < StudentDTO> arrayList = new ArrayList<>();
        ArrayList<Student> all = studentDaoImpl.getAll();
        for (Student student:all){
            arrayList.add(new StudentDTO(student.getId(), student.getName(),student.getAdddress(),student.getContactNo(),
                    student.getDob(),student.getGender()));        }
        return arrayList;
    }

    @Override
    public long countAllStudent() throws SQLException, ClassNotFoundException {
        return studentDaoImpl.collectTotalStudents();
    }

    @Override
    public long countFemaleStudent() throws SQLException, ClassNotFoundException {
        return studentDaoImpl.collectTotalFemaleStudents();
    }

    @Override
    public long countMaleStudent() throws SQLException, ClassNotFoundException {
        return studentDaoImpl.collectTotalMaleStudents();
    }

    @Override
    public ArrayList<StudentDTO> getPaymentDueStudents() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String getNextStudnetId() throws SQLException, ClassNotFoundException {
        return studentDaoImpl.getNextStudnetId();
    }
}
