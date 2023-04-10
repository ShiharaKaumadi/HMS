package lk.ijse.hibernate.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Student {
    @Id
    private String id;
    private String name;
    private String adddress;
    private String contactNo;
    private LocalDate dob;
    private String gender;

    public Student() {
    }

    public Student(String id, String name, String adddress, String contactNo, LocalDate dob, String gender) {
        this.id = id;
        this.name = name;
        this.adddress = adddress;
        this.contactNo = contactNo;
        this.dob = dob;
        this.gender = gender;
    }

    public Student(String name, String adddress, String contactNo, LocalDate dob, String gender) {
        this.name = name;
        this.adddress = adddress;
        this.contactNo = contactNo;
        this.dob = dob;
        this.gender = gender;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdddress(String galle) {
        return adddress;
    }

    public void setAdddress(String adddress) {
        this.adddress = adddress;
    }

    public String getAdddress() {
        return adddress;
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

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", adddress='" + adddress + '\'' +
                '}';
    }
}
