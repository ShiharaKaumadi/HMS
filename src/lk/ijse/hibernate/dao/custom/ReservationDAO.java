package lk.ijse.hibernate.dao.custom;

import lk.ijse.hibernate.dao.util.CrudDAO;
import lk.ijse.hibernate.entity.Reservation;

import java.util.ArrayList;

public interface ReservationDAO extends CrudDAO<Reservation, String> {
    ArrayList<Reservation> getPaymentDueSTudennts();

    boolean isstudentReservedRoom(String studentId);
}
