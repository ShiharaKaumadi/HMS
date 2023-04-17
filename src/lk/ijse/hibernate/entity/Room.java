package lk.ijse.hibernate.entity;

import lk.ijse.hibernate.dto.ReservationDTO;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Room {
    @Id

    private String roomTypeId;
    private String type;
    private String keyMoney;
    private int qty;
    @OneToMany(mappedBy = "resId",targetEntity = Reservation.class)
    private List<Reservation> list = new ArrayList<>();

    public Room() {

    }

    public Room(String roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public Room(String roomTypeId, String type, String keyMoney, int qty, List<Reservation> list) {
        this.roomTypeId = roomTypeId;
        this.type = type;
        this.keyMoney = keyMoney;
        this.qty = qty;
        this.list = list;
    }

    public String getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(String roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKeyMoney() {
        return keyMoney;
    }

    public void setKeyMoney(String keyMoney) {
        this.keyMoney = keyMoney;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public List<Reservation> getList() {
        return list;
    }

    public void setList(List<Reservation> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomTypeId='" + roomTypeId + '\'' +
                ", type='" + type + '\'' +
                ", keyMoney='" + keyMoney + '\'' +
                ", qty=" + qty +
                ", list=" + list +
                '}';
    }
}
