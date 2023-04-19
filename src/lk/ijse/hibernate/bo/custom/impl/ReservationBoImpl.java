package lk.ijse.hibernate.bo.custom.impl;

import lk.ijse.hibernate.bo.custom.ReservationBo;
import lk.ijse.hibernate.dao.custom.ReservationDAO;
import lk.ijse.hibernate.dao.custom.RoomsDAO;
import lk.ijse.hibernate.dao.custom.StudentDAO;
import lk.ijse.hibernate.dao.util.DAOFactory;
import lk.ijse.hibernate.dao.util.DAOTypes;
import lk.ijse.hibernate.dto.ReservationDTO;
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
        System.out.println(resrevationDTO.getStudentId());
        Reservation reservation = new Reservation(resrevationDTO.getResId(), resrevationDTO.getDate(), resrevationDTO.getStatus(), new Student(resrevationDTO.getStudentId()),new Room(resrevationDTO.getRoomTypeId()));

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
                            reservation.getStudentId().getId(),reservation.getRoomTypeId().getRoomTypeId(),reservation.getStatus()));
        }
        return reservationDTOArrayList;
    }

    @Override
    public List<ReservationDTO> getAllReservations() throws SQLException, ClassNotFoundException {
        ArrayList <ReservationDTO> arrayList = new ArrayList<>();
        ArrayList<Reservation> all = reservationDaoImpl.getAll();
        for (Reservation room:all){
            arrayList.add(new ReservationDTO(room.getResId(),room.getDate(),room.getStudentId().getId(),room.getRoomTypeId().getRoomTypeId(),room.getStatus()));
        }
        return arrayList;
    }

    @Override
    public boolean stdIdExist(String studentID) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ReservationDTO searchReservation(String id) throws SQLException, ClassNotFoundException {
        Reservation room = reservationDaoImpl.search(id);
        return new ReservationDTO(room.getResId(),room.getDate(),room.getRoomTypeId().getRoomTypeId(),room.getStudentId().getId(),room.getStatus());
    }

    @Override
    public boolean updateReservation(ReservationDTO resrevationDTO) throws SQLException, ClassNotFoundException {

        return reservationDaoImpl.update(new Reservation(resrevationDTO.getResId(),resrevationDTO.getDate(),resrevationDTO.getStatus(),new Student(resrevationDTO.getStudentId()),new Room(resrevationDTO.getRoomTypeId())));
    }

    @Override
    public boolean deleteReservation(String id) throws SQLException, ClassNotFoundException {
        return reservationDaoImpl.delete(id);
    }


}
