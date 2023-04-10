package lk.ijse.hibernate.bo.custom.impl;

import lk.ijse.hibernate.bo.custom.UserBO;
import lk.ijse.hibernate.dao.custom.StudentDAO;
import lk.ijse.hibernate.dao.custom.UserDAO;
import lk.ijse.hibernate.dao.util.DAOFactory;
import lk.ijse.hibernate.dao.util.DAOTypes;
import lk.ijse.hibernate.dto.UserDTO;
import lk.ijse.hibernate.entity.Student;
import lk.ijse.hibernate.entity.User;

import java.sql.SQLException;

public class UserBoImpl implements UserBO {
    private final UserDAO userDaoImpl = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOTypes.USER);
    @Override
    public boolean addUser(UserDTO userDTO) throws SQLException, ClassNotFoundException {
        return userDaoImpl.add(new User(userDTO.getUserId(),userDTO.getUsrename(),userDTO.getPassword()));
    }
}
