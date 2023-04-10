package lk.ijse.hibernate.bo.custom;

import lk.ijse.hibernate.dto.UserDTO;

import java.sql.SQLException;

public interface UserBO extends SuperBo{
    boolean addUser(UserDTO userDTO) throws SQLException, ClassNotFoundException;
}
