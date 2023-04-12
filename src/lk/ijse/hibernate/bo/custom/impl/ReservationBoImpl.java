package lk.ijse.hibernate.bo.custom.impl;

import lk.ijse.hibernate.bo.custom.ReservationBo;
import lk.ijse.hibernate.dao.custom.ReservationDAO;
import lk.ijse.hibernate.dao.custom.RoomsDAO;
import lk.ijse.hibernate.dao.custom.StudentDAO;
import lk.ijse.hibernate.dao.util.DAOFactory;
import lk.ijse.hibernate.dao.util.DAOTypes;
import lk.ijse.hibernate.dto.ReservationDTO;
import lk.ijse.hibernate.entity.Reservation;

import java.sql.SQLException;
import java.util.ArrayList;

public class ReservationBoImpl implements ReservationBo {
    private final ReservationDAO reservationDaoImpl = (ReservationDAO) DAOFactory.getDaoFactory().getDAO(DAOTypes.RESERVATION);
    private final StudentDAO studentDAOImpl = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOTypes.STUDENT);
    private final RoomsDAO roomsDAOImpl = (RoomsDAO) DAOFactory.getDaoFactory().getDAO(DAOTypes.ROOM);

    @Override
    public boolean addReservation(ReservationDTO resrevationDTO) throws SQLException, ClassNotFoundException {
        return reservationDaoImpl.add(new Reservation(resrevationDTO.getResId(), resrevationDTO.getDate(), resrevationDTO.getStudentId(), resrevationDTO.getRoomTypeId(), resrevationDTO.getStatus()));
    }

    @Override
    public ArrayList<String> loadAllStudentIDs() throws SQLException, ClassNotFoundException{
        return studentDAOImpl.loadStudentIds();
    }

    @Override
    public ArrayList<String> loadRoomTypeID() throws SQLException, ClassNotFoundException{
        return roomsDAOImpl.loadRoomTypeIDs();
    }



}
