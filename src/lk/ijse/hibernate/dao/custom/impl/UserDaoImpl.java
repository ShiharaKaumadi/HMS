package lk.ijse.hibernate.dao.custom.impl;

import lk.ijse.hibernate.dao.custom.UserDAO;
import lk.ijse.hibernate.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserDaoImpl implements UserDAO {
    public User loginUserName(String username) {
        return null;
    }

    public User loginPassword(String password) {
        return null;
    }

    @Override
    public boolean add(User user) throws SQLException, ClassNotFoundException {
        return false;
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
