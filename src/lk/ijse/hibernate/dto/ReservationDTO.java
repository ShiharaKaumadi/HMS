package lk.ijse.hibernate.dto;

import lk.ijse.hibernate.entity.Room;
import lk.ijse.hibernate.entity.Student;

import java.time.LocalDate;

public class ReservationDTO {
    private String resId ;
    private LocalDate date;
    private String studentId;
    private String roomTypeId;
    private String status;

    public ReservationDTO() {
    }



    public ReservationDTO(String studentId, String status) {
        this.studentId = studentId;
        this.status = status;
    }

    public ReservationDTO(String resId, LocalDate date, String studentID, String roomTypeID, String status) {
        this.resId = resId;
        this.date = date;
        this.studentId = studentID;
        this.roomTypeId = roomTypeID;
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
