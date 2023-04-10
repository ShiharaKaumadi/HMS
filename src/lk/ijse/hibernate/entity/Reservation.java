package lk.ijse.hibernate.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
@Entity
public class Reservation {
    @Id
    private String resId;
    private LocalDate date;
    private String studentId;
    private String roomTypeId;
    private String status;

    public Reservation() {
    }

    public Reservation(String resId, LocalDate date, String studentId, String roomTypeId, String status) {
        this.resId = resId;
        this.date = date;
        this.studentId = studentId;
        this.roomTypeId = roomTypeId;
        this.status = status;
    }

    public String getResId() {
        return resId;
    }

    public void setResId(String resId) {
        this.resId = resId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(String roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}