package lk.ijse.hibernate.dao.custom;

import lk.ijse.hibernate.dao.util.SuperDAO;
import lk.ijse.hibernate.dto.CustomDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface QueryDAO extends SuperDAO {
    ArrayList<CustomDTO> getStudentDetailsFromStudentId(String orderId) throws SQLException, ClassNotFoundException;
}
