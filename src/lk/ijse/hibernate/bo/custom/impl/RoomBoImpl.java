package lk.ijse.hibernate.bo.custom.impl;

import lk.ijse.hibernate.bo.custom.RoomBo;
import lk.ijse.hibernate.dao.custom.RoomsDAO;
import lk.ijse.hibernate.dao.custom.UserDAO;
import lk.ijse.hibernate.dao.util.DAOFactory;
import lk.ijse.hibernate.dao.util.DAOTypes;
import lk.ijse.hibernate.dto.RoomDTO;
import lk.ijse.hibernate.dto.StudentDTO;
import lk.ijse.hibernate.entity.Room;
import lk.ijse.hibernate.entity.Student;
import lk.ijse.hibernate.entity.User;

import java.sql.SQLException;

public class RoomBoImpl implements RoomBo {
    private final RoomsDAO roomDaoImpl = (RoomsDAO) DAOFactory.getDaoFactory().getDAO(DAOTypes.ROOM);
    @Override
    public boolean addRoom(RoomDTO roomDto) throws SQLException, ClassNotFoundException {
        return roomDaoImpl.add(new Room(roomDto.getRoomTypeId(),roomDto.getRoomType(),roomDto.getKeyMoney(),roomDto.getQty()));
    }

    @Override
    public RoomDTO searchRoom(String id) throws SQLException, ClassNotFoundException {
        Room room = roomDaoImpl.search(id);
        return new RoomDTO(room.getRoomTypeId(),room.getType(),room.getKeyMoney(),room.getQty());

    }
}
