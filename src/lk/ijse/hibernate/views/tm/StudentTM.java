package lk.ijse.hibernate.views.tm;

import javafx.scene.control.Button;

import java.time.LocalDate;

public class StudentTM {
    private String studentId;
    private String name;
    private String address;
    private String contactNo;
    private LocalDate dob;
    private String gender;
    private Button btnDelete = new Button();

    public StudentTM() {
    }

    public StudentTM(String studentId, String name, String address, String contactNo, LocalDate dob, String gender, Button btnDelete) {
        this.studentId = studentId;
        this.name = name;
        this.address = address;
        this.contactNo = contactNo;
        this.dob = dob;
        this.gender = gender;
        this.btnDelete = btnDelete;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Button getBtnDelete() {
        return btnDelete;
    }

    public void setBtnDelete(Button btnDelete) {
        this.btnDelete = btnDelete;
    }

    @Override
    public String toString() {
        return "StudentTM{" +
                "studentId='" + studentId + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contactNo='" + contactNo + '\'' +
                ", dob=" + dob +
                ", gender='" + gender + '\'' +
                ", btnDelete=" + btnDelete +
                '}';
    }
}
