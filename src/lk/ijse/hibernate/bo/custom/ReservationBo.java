package lk.ijse.hibernate.bo.custom;

import lk.ijse.hibernate.dto.ReservationDTO;

import java.sql.SQLException;

public interface ReservationBo extends SuperBo{
    boolean addReservation(ReservationDTO reservationDTO) throws SQLException, ClassNotFoundException;
}
