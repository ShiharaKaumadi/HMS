package lk.ijse.hibernate.bo.custom.impl;

import lk.ijse.hibernate.bo.custom.ReservationBo;
import lk.ijse.hibernate.dao.custom.ReservationDAO;
import lk.ijse.hibernate.dao.custom.RoomsDAO;
import lk.ijse.hibernate.dao.custom.StudentDAO;
import lk.ijse.hibernate.dao.util.DAOFactory;
import lk.ijse.hibernate.dao.util.DAOTypes;
import lk.ijse.hibernate.dto.ReservationDTO;
import lk.ijse.hibernate.dto.RoomDTO;
import lk.ijse.hibernate.entity.Reservation;
import lk.ijse.hibernate.entity.Room;
import lk.ijse.hibernate.entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationBoImpl implements ReservationBo {
    private final ReservationDAO reservationDaoImpl = (ReservationDAO) DAOFactory.getDaoFactory().getDAO(DAOTypes.RESERVATION);
    private final StudentDAO studentDAOImpl = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOTypes.STUDENT);
    private final RoomsDAO roomsDAOImpl = (RoomsDAO) DAOFactory.getDaoFactory().getDAO(DAOTypes.ROOM);

    @Override
    public boolean addReservation(ReservationDTO resrevationDTO) throws SQLException, ClassNotFoundException {
        String studentId = resrevationDTO.getStudentId();
        Reservation reservation = new Reservation(resrevationDTO.getResId(), resrevationDTO.getDate(), resrevationDTO.getStatus(), new Student(resrevationDTO.getStudentId()),new Room(resrevationDTO.getRoomTypeId()));

        System.out.println("ToString"+reservation);
        return reservationDaoImpl.add(reservation);
    }

    @Override
    public ArrayList<String> loadAllStudentIDs() throws SQLException, ClassNotFoundException{
        return studentDAOImpl.loadStudentIds();
    }

    @Override
    public ArrayList<String> loadRoomTypeID() throws SQLException, ClassNotFoundException{
        return roomsDAOImpl.loadRoomTypeIDs();
    }

    @Override
    public ArrayList<ReservationDTO> getPaymentDueStudents() throws SQLException, ClassNotFoundException {
        ArrayList <ReservationDTO> reservationDTOArrayList= new ArrayList<>();
        ArrayList<Reservation> topSellingItemOrder = reservationDaoImpl.getPaymentDueSTudennts();
        for (Reservation reservation: topSellingItemOrder){
            reservationDTOArrayList.add(
                    new ReservationDTO(reservation.getResId(),reservation.getDate(),
                            String.valueOf(reservation.getStudentId()),String.valueOf(reservation.getRoomTypeId()),reservation.getStatus()));
        }
        return reservationDTOArrayList;
    }

    @Override
    public List<ReservationDTO> getAllReservations() throws SQLException, ClassNotFoundException {
        ArrayList <ReservationDTO> arrayList = new ArrayList<>();
        ArrayList<Reservation> all = reservationDaoImpl.getAll();
        for (Reservation room:all){
            arrayList.add(new ReservationDTO(room.getResId(),room.getDate(), String.valueOf(room.getStudentId()),String.valueOf(room.getRoomTypeId()),room.getStatus()));
        }
        return arrayList;
    }


}
