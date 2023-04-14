package lk.ijse.hibernate.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Student {
    @Id

    private String id;
    private String name;
    private String adddress;
    private String contactNo;
    private LocalDate dob;
    private String gender;
    @OneToMany(mappedBy = "resId",targetEntity = Reservation.class)
    private List<Reservation> list = new ArrayList<>();

    public Student() {
    }

    public Student(String id) {
        this.id = id;
    }

    public Student(String id, String name, String adddress, String contactNo, LocalDate dob, String gender, List<Reservation> list) {
        this.id = id;
        this.name = name;
        this.adddress = adddress;
        this.contactNo = contactNo;
        this.dob = dob;
        this.gender = gender;
        this.list = list;
    }

    public Student(String id, String name, String adddress, String contactNo, LocalDate dob, String gender) {
        this.id = id;
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

    public String getAdddress() {
        return adddress;
    }

    public void setAdddress(String adddress) {
        this.adddress = adddress;
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

    public List<Reservation> getList() {
        return list;
    }

    public void setList(List<Reservation> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", adddress='" + adddress + '\'' +
                ", contactNo='" + contactNo + '\'' +
                ", dob=" + dob +
                ", gender='" + gender + '\'' +
                ", list=" + list +
                '}';
    }
}
