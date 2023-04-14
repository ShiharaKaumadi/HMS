package lk.ijse.hibernate.views.tm;

import lk.ijse.hibernate.entity.Room;

import java.time.LocalDate;

public class ReservationTM {
    private String resId ;
    private LocalDate date;
    private String studentId;
    private String roomTypeId;
    private String status;

    public ReservationTM() {
    }

    public ReservationTM(String resId, LocalDate date, String studentId, String roomTypeId, String status) {
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
