package lk.ijse.hibernate.dao.util;

import lk.ijse.hibernate.dao.custom.impl.ReservationDAOImpl;
import lk.ijse.hibernate.dao.custom.impl.RoomsDaoImpl;
import lk.ijse.hibernate.dao.custom.impl.StudentDaoImpl;
import lk.ijse.hibernate.dao.custom.impl.UserDaoImpl;

public class DAOFactory {
    public static DAOFactory daoFactory;

    private DAOFactory(){
    }

    public static DAOFactory getDaoFactory(){
        return (daoFactory==null)?daoFactory= new DAOFactory():daoFactory;
    }

    public SuperDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes) {
            case USER:
                return new UserDaoImpl();
            case STUDENT:
                return new StudentDaoImpl();
            case ROOM:
                return new RoomsDaoImpl();
            case RESERVATION:
                return new ReservationDAOImpl();
            default:
                return null;
        }
        }

}
