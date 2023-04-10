package lk.ijse.hibernate.bo.custom.impl;

import lk.ijse.hibernate.bo.custom.ReservationBo;
import lk.ijse.hibernate.dao.custom.ReservationDAO;
import lk.ijse.hibernate.dao.custom.StudentDAO;
import lk.ijse.hibernate.dao.util.DAOFactory;
import lk.ijse.hibernate.dao.util.DAOTypes;
import lk.ijse.hibernate.dto.ReservationDTO;
import lk.ijse.hibernate.entity.Reservation;
import lk.ijse.hibernate.entity.Student;

import java.sql.SQLException;

public class ReservationBoImpl implements ReservationBo {
    private final ReservationDAO reservationDaoImpl = (ReservationDAO)DAOFactory.getDaoFactory().getDAO(DAOTypes.RESERVATION);

    @Override
    public boolean addReservation(ReservationDTO resrevationDTO) throws SQLException, ClassNotFoundException {
        return reservationDaoImpl.add(new Reservation(resrevationDTO.getResId(),resrevationDTO.getDate(), resrevationDTO.getStudentId(),resrevationDTO.getRoomTypeId(),resrevationDTO.getStatus()));
    }
}
