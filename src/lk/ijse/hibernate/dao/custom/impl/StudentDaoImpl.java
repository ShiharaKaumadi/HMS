package lk.ijse.hibernate.dao.custom.impl;

import lk.ijse.hibernate.dao.custom.StudentDAO;
import lk.ijse.hibernate.entity.Student;
import lk.ijse.hibernate.entity.User;
import lk.ijse.hibernate.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentDaoImpl implements StudentDAO {
    Session session = FactoryConfiguration.getInstance().getSession();
    @Override
    public boolean add(Student student) throws SQLException, ClassNotFoundException {

        Transaction transaction = session.beginTransaction();
        //transaction.begin();
        try{
            session.saveOrUpdate(student);
            System.out.println(student.getName());
            transaction.commit();
            return true;
        }catch (Exception e){
            System.out.println(e);;
            transaction.rollback();
            return false;
        }
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {

        Transaction transaction = session.beginTransaction();
        //transaction.begin();

        try{
            session.delete(id,Student.class);
            transaction.commit();
            System.out.println("commited");
            return true;
        }catch (Exception e){
            System.out.println(e);
            System.out.println("eshani");
            transaction.rollback();
            return false;
        }
    }

    @Override
    public boolean update(Student student) throws SQLException, ClassNotFoundException {

        Transaction transaction = session.beginTransaction();
        //transaction.begin();
        try{
            session.saveOrUpdate(student);
            
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            System.out.println(e);;
            transaction.rollback();
            return false;
        }
    }

    @Override
    public Student search(String id) throws SQLException, ClassNotFoundException {
        Transaction transaction = session.beginTransaction();

        try{
            Student student1 = session.find(Student.class, id);

            transaction.commit();
            System.out.println(student1.getDob());
           return new Student(student1.getId(),student1.getName(),student1.getAdddress(),student1.getContactNo(), student1.getDob(),student1.getGender());
        }catch (Exception e){
            System.out.println(e);
            transaction.rollback();
            return null;
        }
    }

    @Override
    public ArrayList<Student> getAll() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();


        ArrayList<Student> students = new ArrayList<>();
        NativeQuery sqlQuery = session.createSQLQuery("SELECT * FROM Student");
        sqlQuery.addEntity(Student.class);
        List<Student> customerList = sqlQuery.list();
        String code;
        for (Student student:customerList){
            students.add(student);

        }
        return students;
    }
}
