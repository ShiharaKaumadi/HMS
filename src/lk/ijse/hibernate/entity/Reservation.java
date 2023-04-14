package lk.ijse.hibernate.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
@Entity
public class Reservation {
    @Id
    private String resId;
    private LocalDate date;
    private String status;
    private String id;
    private String rId;
    @ManyToOne
    private Student studentId;
    @ManyToOne
    private Room roomTypeId;

    public Reservation() {
    }

    public Reservation(String resId, LocalDate date, String status, Student studentId, Room roomTypeId) {
        this.resId = resId;
        this.date = date;
        this.status = status;
        this.studentId = studentId;
        this.roomTypeId = roomTypeId;
    }

    public Reservation(String resId, LocalDate date, String status, String id, String rId, Student studentId, Room roomTypeId) {
        this.resId = resId;
        this.date = date;
        this.status = status;
        this.id = id;
        this.rId = rId;
        this.studentId = studentId;
        this.roomTypeId = roomTypeId;
    }

    public Reservation(String resId, LocalDate date, String status) {
    }

    public Reservation(String resId, LocalDate date, String status, String id) {
        this.resId=resId;
        this.date =date;
        this.status=status;
        this.id=id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Student getStudentId() {
        return studentId;
    }

    public void setStudentId(Student studentId) {
        this.studentId = studentId;
    }

    public Room getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Room roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "resId='" + resId + '\'' +
                ", date=" + date +
                ", status='" + status + '\'' +
                ", id='" + id + '\'' +
                ", rId='" + rId + '\'' +
                ", studentId=" + studentId +
                ", roomTypeId=" + roomTypeId +
                '}';
    }
}
