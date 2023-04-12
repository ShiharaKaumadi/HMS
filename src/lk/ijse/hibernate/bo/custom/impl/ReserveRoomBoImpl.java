package lk.ijse.hibernate.bo.custom.impl;

import lk.ijse.hibernate.bo.custom.ReserveRoomBo;
import lk.ijse.hibernate.dao.custom.ReservationDAO;
import lk.ijse.hibernate.dao.custom.RoomsDAO;
import lk.ijse.hibernate.dao.custom.StudentDAO;
import lk.ijse.hibernate.dao.util.DAOFactory;
import lk.ijse.hibernate.dao.util.DAOTypes;
import lk.ijse.hibernate.entity.Reservation;
import lk.ijse.hibernate.util.FactoryConfiguration;

public class ReserveRoomBoImpl implements ReserveRoomBo {

    ReservationDAO reservationDAOImpl = (ReservationDAO)DAOFactory.getDaoFactory().getDAO(DAOTypes.RESERVATION);

    public boolean reserveRoom(Reservation reservationDetail){

       try{

       }catch (Exception e){

       }
       return false;

    }


}
