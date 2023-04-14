package lk.ijse.hibernate.controller;

import javafx.scene.control.Label;
import lk.ijse.hibernate.dao.custom.QueryDAO;
import lk.ijse.hibernate.dto.CustomDTO;
import lk.ijse.hibernate.dto.ReservationDTO;
import lk.ijse.hibernate.dto.StudentDTO;
import lk.ijse.hibernate.entity.Student;

public class PaymentDueFormController {
    public Label llName;
    public Label lblPaymentDue;
    private ReservationDTO reservationDTO=new ReservationDTO();


    public void setData(ReservationDTO reservationDTO) {
        this.reservationDTO=reservationDTO;

        System.out.println(reservationDTO.getStudentId());
         llName.setText(reservationDTO.getStudentId());

        lblPaymentDue.setText(String.valueOf(reservationDTO.getStatus()));
    }


    public void setData(CustomDTO customDTO) {
        this.reservationDTO=reservationDTO;

        llName.setText(reservationDTO.getStudentId());

        lblPaymentDue.setText(String.valueOf(reservationDTO.getStatus()));
    }
}
