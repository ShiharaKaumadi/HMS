package lk.ijse.hibernate.bo.custom.impl;

import lk.ijse.hibernate.bo.custom.RoomBo;
import lk.ijse.hibernate.dao.custom.RoomsDAO;
import lk.ijse.hibernate.dao.util.DAOFactory;
import lk.ijse.hibernate.dao.util.DAOTypes;
import lk.ijse.hibernate.dto.RoomDTO;
import lk.ijse.hibernate.dto.StudentDTO;
import lk.ijse.hibernate.entity.Reservation;
import lk.ijse.hibernate.entity.Room;
import lk.ijse.hibernate.entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomBoImpl implements RoomBo {
    private final RoomsDAO roomDaoImpl = (RoomsDAO) DAOFactory.getDaoFactory().getDAO(DAOTypes.ROOM);
    @Override
    public boolean addRoom(RoomDTO roomDto) throws SQLException, ClassNotFoundException {
        return roomDaoImpl.add(new Room(roomDto.getRoomTypeId(),roomDto.getRoomType(),roomDto.getKeyMoney(),roomDto.getQty(),null));
    }

    @Override
    public RoomDTO searchRoom(String id) throws SQLException, ClassNotFoundException {
        Room room = roomDaoImpl.search(id);
        return new RoomDTO(room.getRoomTypeId(),room.getType(),room.getKeyMoney(),room.getQty());

    }

    @Override
    public void getAllAvailableACRooms() {
        roomDaoImpl.getAvailableAcRooms();
    }

    @Override
    public List<RoomDTO> getAllRooms() throws SQLException, ClassNotFoundException {
        ArrayList <RoomDTO> arrayList = new ArrayList<>();
        ArrayList<Room> all = roomDaoImpl.getAll();
        for (Room room:all){
            arrayList.add(new RoomDTO(room.getRoomTypeId(),room.getType(),room.getKeyMoney(),room.getQty()));
        }
        return arrayList;
    }

    @Override
    public boolean updateRoom(RoomDTO roomDto) throws SQLException, ClassNotFoundException {
        return roomDaoImpl.update(new Room(roomDto.getRoomTypeId(),roomDto.getRoomType(),roomDto.getKeyMoney(),roomDto.getQty(),null));
    }

    @Override
    public boolean deleteRoom(String id) throws SQLException, ClassNotFoundException {
        return roomDaoImpl.delete(id);
    }


}
