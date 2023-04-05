package lk.ijse.hibernate.dao.custom.impl;

import lk.ijse.hibernate.dao.custom.StudentDAO;
import lk.ijse.hibernate.entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;

public class StudentDaoImpl implements StudentDAO {
    @Override
    public boolean add(Student student) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Student student) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Student search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<Student> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }
}
