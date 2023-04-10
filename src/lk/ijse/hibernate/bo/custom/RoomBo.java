package lk.ijse.hibernate.bo.custom;

import lk.ijse.hibernate.dto.RoomDTO;

import java.sql.SQLException;

public interface RoomBo extends SuperBo{
    boolean addRoom(RoomDTO roomDto) throws SQLException, ClassNotFoundException;

    RoomDTO searchRoom(String id) throws SQLException, ClassNotFoundException;
}
