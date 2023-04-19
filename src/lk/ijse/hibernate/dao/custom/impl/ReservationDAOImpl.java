package lk.ijse.hibernate.dao.custom.impl;

import lk.ijse.hibernate.dao.custom.ReservationDAO;
import lk.ijse.hibernate.dao.custom.RoomsDAO;
import lk.ijse.hibernate.dao.util.DAOFactory;
import lk.ijse.hibernate.dao.util.DAOTypes;
import lk.ijse.hibernate.dto.ReservationDTO;
import lk.ijse.hibernate.entity.Reservation;
import lk.ijse.hibernate.entity.Room;
import lk.ijse.hibernate.entity.Student;
import lk.ijse.hibernate.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAOImpl implements ReservationDAO {
    private final RoomsDAO roomDaoImpl = (RoomsDAO) DAOFactory.getDaoFactory().getDAO(DAOTypes.ROOM);
    @Override
    public boolean add(Reservation reservation) throws SQLException, ClassNotFoundException {

        Session session = FactoryConfiguration.getInstance().getSession();
        Session session2 = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Transaction transaction2 = session2.beginTransaction();

        try{
            session.saveOrUpdate(reservation);
            Room search = roomDaoImpl.search(String.valueOf(reservation.getRoomTypeId()));
            search.setQty(search.getQty()-1);
            if (roomDaoImpl.update(search)){
                transaction.commit();
                transaction2.commit();
                return true;
            }else{
                transaction2.rollback();

            }

            return false;

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
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        System.out.println(reservation);
        //transaction.begin();

        try {
            session.update(reservation);
            System.out.println(reservation.getDate());
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            transaction.rollback();
            return false;
        }
    }

    @Override
    public Reservation search(String s) throws SQLException, ClassNotFoundException {
        Session session=FactoryConfiguration.getInstance().getSession();
        Transaction transaction=session.beginTransaction();
        try {
            Reservation entity=session.find(Reservation.class,s);
            transaction.commit();
            return new Reservation(entity.getResId(),entity.getDate(),entity.getStatus(),entity.getStudentId(),entity.getRoomTypeId());
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
            return null;
        }
    }

    @Override
    public ArrayList<Reservation> getAll() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query<Reservation> from_reservation_ = session.createQuery("From Reservation ");
        try {
            List<Reservation> list = from_reservation_.list();

            for (Reservation reservation : list) {

                reservation.getRoomTypeId();
                reservation.getDate();
                reservation.getStatus();
                reservation.getStudentId();
                reservation.getRoomTypeId();

            }
            transaction.commit();
            return (ArrayList<Reservation>) list;
        } catch (Exception e) {
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


        try {
            Query query = session.createQuery("FROM Reservation WHERE status='Pending'");
            List<Reservation> list = query.list();
            System.out.println(list);

            transaction.commit();

            return (ArrayList<Reservation>) list;
        } catch (Exception e) {
            System.out.println(e);
            transaction.rollback();
            return null;
        }
    }

    @Override
    public boolean isstudentReservedRoom(String studentId) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.find(Student.class, studentId);

            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            transaction.rollback();
            return false;
        }
    }


}
