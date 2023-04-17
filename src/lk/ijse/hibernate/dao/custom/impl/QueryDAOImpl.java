package lk.ijse.hibernate.dao.custom.impl;

import lk.ijse.hibernate.dao.custom.QueryDAO;
import lk.ijse.hibernate.dao.custom.ReservationDAO;
import lk.ijse.hibernate.dto.CustomDTO;
import lk.ijse.hibernate.entity.Reservation;
import lk.ijse.hibernate.entity.Student;
import lk.ijse.hibernate.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class QueryDAOImpl implements QueryDAO {
    @Override
    public ArrayList<CustomDTO> getStudentDetailsFromStudentId(String studentID) throws SQLException, ClassNotFoundException {
       /* ArrayList<CustomDTO> customDTOArrayList = new ArrayList<>();
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        List<CustomDTO> students = new ArrayList<>();

        try{
            Query query = session.createQuery("FROM Reservation r LEFT JOIN Student s ON r.studentId=s.id");
            List <CustomDTO> list= query.list();
            System.out.println(list);

            transaction.commit();

            return (ArrayList<CustomDTO>) list;
        }catch (Exception e){
            System.out.println(e);
            transaction.rollback();*/
            return null;
        }

}
