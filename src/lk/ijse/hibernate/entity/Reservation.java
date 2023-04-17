package lk.ijse.hibernate.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
@Entity
public class Reservation {
    @Id
    private String resId;
    private LocalDate date;
    private String status;
    @ManyToOne
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Student studentId;
    @ManyToOne
   @JoinColumn(name = "roomTypeId", referencedColumnName = "roomTypeId")
    private Room roomTypeId;

    public Reservation() {
    }

    public Reservation(String resId, LocalDate date, String status, Student studentId, Room roomTypeId) {
        System.out.println("Room Type ID: "+roomTypeId);
        System.out.println("Room Type ID: "+studentId);
        this.resId = resId;
        this.date = date;
        this.status = status;
        this.studentId = studentId;
        this.roomTypeId = roomTypeId;
    }



    public Reservation(String resId, LocalDate date, String status) {
        this.resId=resId;
        this.date =date;
        this.status=status;

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



    @Override
    public String toString() {
        return "Reservation{" +
                "resId='" + resId + '\'' +
                ", date=" + date +
                ", status='" + status + '\'' +

                ", studentId=" + studentId +
                ", roomTypeId=" + roomTypeId +
                '}';
    }
}
