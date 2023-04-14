package lk.ijse.hibernate.dao.custom.impl;

import lk.ijse.hibernate.dao.custom.ReservationDAO;
import lk.ijse.hibernate.dto.ReservationDTO;
import lk.ijse.hibernate.entity.Reservation;
import lk.ijse.hibernate.entity.Student;
import lk.ijse.hibernate.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAOImpl implements ReservationDAO {
    @Override
    public boolean add(Reservation reservation) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        System.out.println(reservation);
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
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        List<ReservationDTO> students = new ArrayList<>();


        try{
            Query query = session.createQuery("FROM Reservation");
            List <Reservation> list= query.list();
            System.out.println(list);

            transaction.commit();

            return (ArrayList<Reservation>) list;
        }catch (Exception e){
            System.out.println(e);
            transaction.rollback();
            return null;
        }
    }

    @Override
    public ArrayList<Reservation> getPaymentDueSTudennts() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        List<ReservationDTO> students = new ArrayList<>();


        try{
            Query query = session.createQuery("FROM Reservation WHERE status='Pending'");
            List <Reservation> list= query.list();
            System.out.println(list);

            transaction.commit();

            return (ArrayList<Reservation>) list;
        }catch (Exception e){
            System.out.println(e);
            transaction.rollback();
            return null;
        }
    }
}
