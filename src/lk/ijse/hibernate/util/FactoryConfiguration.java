package lk.ijse.hibernate.util;

import lk.ijse.hibernate.entity.Reservation;
import lk.ijse.hibernate.entity.Room;
import lk.ijse.hibernate.entity.Student;
import lk.ijse.hibernate.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryConfiguration {
    public static FactoryConfiguration factoryConfiguration;

    private SessionFactory sessionFactory;
    private FactoryConfiguration(){
        Configuration configuration = new Configuration().addAnnotatedClass(Student.class).addAnnotatedClass(User.class).addAnnotatedClass(Room.class).addAnnotatedClass(Reservation.class);

        sessionFactory = configuration.buildSessionFactory();

    }
    public static FactoryConfiguration getInstance(){
        return (null==factoryConfiguration?factoryConfiguration=new FactoryConfiguration():factoryConfiguration);
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }
}