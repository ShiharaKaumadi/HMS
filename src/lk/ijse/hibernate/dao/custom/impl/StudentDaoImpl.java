package lk.ijse.hibernate.dao.custom.impl;

import lk.ijse.hibernate.dao.custom.StudentDAO;
import lk.ijse.hibernate.entity.Student;
import lk.ijse.hibernate.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDAO {

    @Override
    public boolean add(Student student) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
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
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        //transaction.begin();

        try{
            session.delete(id,Student.class);
            System.out.println(id);
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
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        //transaction.begin();
        try{
            session.saveOrUpdate(student);
            transaction.commit();
            System.out.println(student);
            return true;
        }catch (Exception e){
            System.out.println(e);;
            transaction.rollback();
            return false;
        }
    }

    @Override
    public Student search(String id) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
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
        List<Student> students = new ArrayList<>();

        try{
        Query query = session.createQuery("FROM Student");
        List <Student> list= query.list();
            System.out.println(list);

        transaction.commit();

        return (ArrayList<Student>) list;
    }catch (Exception e){
        System.out.println(e);
        transaction.rollback();
        return null;
    }
    }

    @Override
    public long collectTotalStudents() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            Long singleResult = (Long) session.createQuery("select COUNT(*) FROM Student ").getSingleResult();
            System.out.println(singleResult);
            transaction.commit();
            return singleResult;
        }catch (Exception e){
            System.out.println(e);
            transaction.rollback();
            return 0;
        }

    }

    @Override
    public long collectTotalFemaleStudents() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            Long singleResult = (Long) session.createQuery("select COUNT(*) FROM Student WHERE gender Like 'F%'").getSingleResult();
            System.out.println(singleResult);
            transaction.commit();
            return singleResult;
        }catch (Exception e){
            System.out.println(e);
            transaction.rollback();
            return 0;
        }
    }

    @Override
    public long collectTotalMaleStudents() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            Long singleResult = (Long) session.createQuery("select COUNT(*) FROM Student WHERE gender Like 'M%' ").getSingleResult();
            System.out.println(singleResult);
            transaction.commit();
            return singleResult;
        }catch (Exception e){
            System.out.println(e);
            transaction.rollback();
            return 0;
        }
    }

    @Override
    public void isStudentExist() {

    }

    @Override
    public ArrayList<String> loadStudentIds() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        List<Student> students = new ArrayList<>();

        try{
            Query query = session.createQuery("SELECT id FROM Student");
            List <String> list= query.list();
            System.out.println(list);

            transaction.commit();

            return (ArrayList<String>) list;
        }catch (Exception e){
            System.out.println(e);
            transaction.rollback();
            return null;
        }
    }
}
