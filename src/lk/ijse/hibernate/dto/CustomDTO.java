package lk.ijse.hibernate.dto;

import java.time.LocalDate;

public class CustomDTO {
    private String id;
    private String address;
    private String name;
    private String contactNo;
    private String gender;
    private LocalDate dob;
    private String resId;
    private LocalDate dateofReservation;
    private String roomTypeId;
    private String status;

    public CustomDTO() {
    }

    public CustomDTO(String id, String address, String name, String contactNo, String gender, LocalDate dob, String
            resId, LocalDate dateofReservation, String roomTypeId, String status) {
        this.id = id;
        this.address = address;
        this.name = name;
        this.contactNo = contactNo;
        this.gender = gender;
        this.dob = dob;
        this.resId = resId;
        this.dateofReservation = dateofReservation;
        this.roomTypeId = roomTypeId;
        this.status = status;
    }

    public CustomDTO(String id, String name, String status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getResId() {
        return resId;
    }

    public void setResId(String resId) {
        this.resId = resId;
    }

    public LocalDate getDateofReservation() {
        return dateofReservation;
    }

    public void setDateofReservation(LocalDate dateofReservation) {
        this.dateofReservation = dateofReservation;
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
