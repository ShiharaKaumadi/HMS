package lk.ijse.hibernate.bo.custom;

import lk.ijse.hibernate.bo.custom.impl.ReservationBoImpl;
import lk.ijse.hibernate.bo.custom.impl.RoomBoImpl;
import lk.ijse.hibernate.bo.custom.impl.StudentBoImpl;
import lk.ijse.hibernate.bo.custom.impl.UserBoImpl;

public class BOFactory {
    public static BOFactory boFactory;
    private BOFactory(){

    }
    public static BOFactory getBoFactory(){
        return (boFactory==null)?boFactory= new BOFactory():boFactory;

    }

    public SuperBo getBO(BOTypes boTypes){
        switch (boTypes){
            case STUDENT:
                return new StudentBoImpl();
            case ROOM:
                return new RoomBoImpl();
            case USER:
                return new UserBoImpl();
            case RESERVATION:
                return new ReservationBoImpl();
            default:
                return null;
        }
    }
}


