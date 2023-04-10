package lk.ijse.hibernate.dao.custom.impl;

import lk.ijse.hibernate.dao.custom.UserDAO;
import lk.ijse.hibernate.entity.User;
import lk.ijse.hibernate.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserDaoImpl implements UserDAO {
    public User loginUserName(String username) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        //transaction.begin();
        try{

            User user = session.find(User.class, username);
            System.out.println(username);
            transaction.commit();
            return new User(user.getId(),username,user.getPassword());
        }catch (Exception e){
            System.out.println(e);;
            transaction.rollback();
            return null;
        }
    }

    public User loginPassword(String password) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        //transaction.begin();
        try{

            User user = session.find(User.class, password);
            System.out.println(password);
            transaction.commit();
            return new User(user.getId(),user.getUserName(),user.getPassword());
        }catch (Exception e){
            System.out.println(e);;
            transaction.rollback();
            return null;
        }
    }

    @Override
    public boolean add(User user) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.saveOrUpdate(user);
            System.out.println(user.getUserName());
            transaction.commit();
            return true;
        }catch (Exception e){
            System.out.println(e);;
            transaction.rollback();
            return false;
        }

    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(User user) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public User search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<User> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }
}
