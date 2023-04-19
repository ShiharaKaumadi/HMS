package lk.ijse.hibernate.bo.custom;

import lk.ijse.hibernate.dto.RoomDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface RoomBo extends SuperBo{
    boolean addRoom(RoomDTO roomDto) throws SQLException, ClassNotFoundException;

    RoomDTO searchRoom(String id) throws SQLException, ClassNotFoundException;

    void getAllAvailableACRooms();


    List<RoomDTO> getAllRooms() throws SQLException, ClassNotFoundException;

    boolean updateRoom(RoomDTO roomDto) throws SQLException, ClassNotFoundException;

    boolean deleteRoom(String id) throws SQLException, ClassNotFoundException;

    int countAcFoodAvailableRooms() throws SQLException, ClassNotFoundException;

    int countAcAvailableRooms() throws SQLException, ClassNotFoundException;

    int countNonAcFoodAvailableRooms() throws SQLException, ClassNotFoundException;

    int countNonAcAvailableRooms() throws SQLException, ClassNotFoundException;

}
