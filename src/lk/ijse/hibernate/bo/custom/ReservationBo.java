package lk.ijse.hibernate.bo.custom;

import lk.ijse.hibernate.dto.ReservationDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ReservationBo extends SuperBo{
    boolean addReservation(ReservationDTO reservationDTO) throws SQLException, ClassNotFoundException;

    ArrayList<String> loadAllStudentIDs() throws SQLException, ClassNotFoundException;

    ArrayList<String> loadRoomTypeID() throws SQLException, ClassNotFoundException;


    ArrayList<ReservationDTO> getPaymentDueStudents() throws SQLException, ClassNotFoundException;

    List<ReservationDTO> getAllReservations()throws SQLException, ClassNotFoundException;


    boolean stdIdExist(String studentID) throws SQLException, ClassNotFoundException;

    ReservationDTO searchReservation(String id) throws SQLException, ClassNotFoundException;

    boolean updateReservation(ReservationDTO resrevationDTO)throws SQLException, ClassNotFoundException;

    boolean deleteReservation(String id)throws SQLException, ClassNotFoundException;
}
