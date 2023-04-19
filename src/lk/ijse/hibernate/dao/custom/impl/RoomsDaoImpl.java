package lk.ijse.hibernate.dao.custom.impl;

import lk.ijse.hibernate.dao.custom.RoomsDAO;
import lk.ijse.hibernate.dao.util.DAOFactory;
import lk.ijse.hibernate.dao.util.DAOTypes;
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

public class RoomsDaoImpl implements RoomsDAO {


    @Override
    public boolean add(Room room) throws SQLException, ClassNotFoundException {

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();



        try{
            session.saveOrUpdate(room);
            transaction.commit();
                return true;

        }catch (Exception e){
            System.out.println(e);;
            transaction.rollback();
            return false;
        }
    }

    @Override

    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        Session session=FactoryConfiguration.getInstance().getSession();
        Transaction transaction=session.beginTransaction();
        try {
            Room room=session.load(Room.class,id);
            session.delete(room);
            transaction.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
            return false;
        }
    }

    @Override

    public boolean update(Room room) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        //transaction.begin();
        try{
            session.update(room);
            transaction.commit();
            return true;
        }catch (Exception e){
            System.out.println(e);;
            transaction.rollback();
            return false;
        }
    }

    @Override
    public Room search(String id) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try{
            Room room = session.find(Room.class, id);

            transaction.commit();
            System.out.println(room.getQty());
            return new Room(room.getRoomTypeId(),room.getType(),room.getKeyMoney(),room.getQty());
        }catch (Exception e){
            System.out.println(e);
            transaction.rollback();
            return null;
        }
    }

    @Override
    public ArrayList<Room> getAll() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        List<Room> rooms = new ArrayList<>();

        try{
            Query query = session.createQuery("FROM Room");
            List <Room> list= query.list();
            System.out.println(list);

            transaction.commit();

            return (ArrayList<Room>) list;
        }catch (Exception e){
            System.out.println(e);
            transaction.rollback();
            return null;
        }
    }

    @Override
    public void getAvailableAcRooms() {

    }

    @Override
    public ArrayList<String> loadRoomTypeIDs() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        List<Room> rooms = new ArrayList<>();

        try{
            Query query = session.createQuery("SELECT roomTypeId FROM Room");
            List <String> list= query.list();
            System.out.println(list);

            transaction.commit();

            return (ArrayList<String>) list;
        }catch (Exception e){
            System.out.println(e);
            transaction.rollback();
            return null;
        }
    }

    @Override
    public ArrayList<String> loadAvailableRooms() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        List<Room> rooms = new ArrayList<>();

        try{
            Query query = session.createQuery("SELECT roomTypeId FROM Room WHERE qty>0");

            List <String> list= query.list();
            System.out.println(list);

            transaction.commit();

            return (ArrayList<String>) list;
        }catch (Exception e){
            System.out.println(e);
            transaction.rollback();
            return null;
        }
    }

    @Override
    public ArrayList<Reservation> getPaymentDueStudentList() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        List<Reservation> rooms = new ArrayList<>();

        try{
            Query query = session.createQuery("FROM Reservation WHERE status ='Pending'");

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
