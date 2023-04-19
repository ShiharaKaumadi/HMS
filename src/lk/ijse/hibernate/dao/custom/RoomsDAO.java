package lk.ijse.hibernate.dao.custom;

import lk.ijse.hibernate.dao.util.CrudDAO;
import lk.ijse.hibernate.entity.Reservation;
import lk.ijse.hibernate.entity.Room;

import java.util.ArrayList;

public interface RoomsDAO extends CrudDAO<Room, String> {
    void getAvailableAcRooms();


    ArrayList<String> loadRoomTypeIDs();

    ArrayList<String> loadAvailableRooms();

    ArrayList<Reservation> getPaymentDueStudentList();

    int countAcFoodAvailableooms();

    int countAcAvailableRooms();

    int countNonAcFoodAvailableRooms();

    int countNonAcAvailableRooms();
}
