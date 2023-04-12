package lk.ijse.hibernate.bo.custom;

import lk.ijse.hibernate.dto.ReservationDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ReservationBo extends SuperBo{
    boolean addReservation(ReservationDTO reservationDTO) throws SQLException, ClassNotFoundException;

    ArrayList<String> loadAllStudentIDs() throws SQLException, ClassNotFoundException;

    ArrayList<String> loadRoomTypeID() throws SQLException, ClassNotFoundException;


}
