package lk.ijse.hibernate.dao.custom.impl;

import lk.ijse.hibernate.dao.custom.ReservationDAO;
import lk.ijse.hibernate.entity.Reservation;
import lk.ijse.hibernate.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;

public class ReservationDAOImpl implements ReservationDAO {
    Session session = FactoryConfiguration.getInstance().getSession();
    @Override
    public boolean add(Reservation reservation) throws SQLException, ClassNotFoundException {
        Transaction transaction = session.beginTransaction();
        //transaction.begin();
        try{
            session.saveOrUpdate(reservation);
            System.out.println(reservation.getDate());
            transaction.commit();
            return true;
        }catch (Exception e){
            System.out.println(e);;
            transaction.rollback();
            return false;
        }
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Reservation reservation) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Reservation search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<Reservation> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }
}
