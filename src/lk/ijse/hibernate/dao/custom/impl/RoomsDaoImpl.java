package lk.ijse.hibernate.dao.custom.impl;

import lk.ijse.hibernate.dao.custom.RoomsDAO;
import lk.ijse.hibernate.entity.Room;
import lk.ijse.hibernate.entity.Student;
import lk.ijse.hibernate.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;

public class RoomsDaoImpl implements RoomsDAO {
    Session session = FactoryConfiguration.getInstance().getSession();
    @Override
    public boolean add(Room room) throws SQLException, ClassNotFoundException {


        Transaction transaction = session.beginTransaction();
        //transaction.begin();
        try{
            session.saveOrUpdate(room);
            transaction.commit();
            session.close();
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

    public boolean update(Room room) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        //transaction.begin();
        try{
            session.update(room);
            transaction.commit();
            session.close();
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

            return new Room(room.getRoomTypeId(),room.getType(),room.getKeyMoney(),room.getQty());

        }catch (Exception e){
            System.out.println(e);
            transaction.rollback();
            return null;
        }
    }

    @Override
    public ArrayList<Room> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }
}
