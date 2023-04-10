package lk.ijse.hibernate.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.util.Duration;
import lk.ijse.hibernate.bo.custom.BOFactory;
import lk.ijse.hibernate.bo.custom.BOTypes;
import lk.ijse.hibernate.bo.custom.ReservationBo;
import lk.ijse.hibernate.bo.custom.StudentBo;
import lk.ijse.hibernate.dao.util.DAOFactory;
import lk.ijse.hibernate.dto.ReservationDTO;
import lk.ijse.hibernate.dto.StudentDTO;
import lk.ijse.hibernate.util.Navigation;
import lk.ijse.hibernate.util.Routes;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReservationFormController {
    public BorderPane brdPane;
    public TableView tblReservationData;
    public TableColumn colResID;
    public TableColumn colDate;
    public TableColumn colStudentIID;
    public TableColumn colRoomTypeID;
    public TableColumn colStatus;
    public Pane searchBar;
    public TextField txtSearch;
    public JFXTextField txtResID;
    public JFXTextField txtStudentID;
    public JFXTextField txtRoomTypeID;
    public JFXDatePicker dtDate;
    public JFXButton btnReserved;
    public JFXTextField txtStatus;

    ReservationBo reservationBoImpl = (ReservationBo) BOFactory.getBoFactory().getBO(BOTypes.RESERVATION);

    public void btnDashBoardOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.DASHBOARD,brdPane);
    }

    public void btnManageStudentOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.STUDENT,brdPane);
    }

    public void btnManageRoomsOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ROOMS,brdPane);
    }

    public void btnManageReservations(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RESERVATION,brdPane);
    }

    public void btnResrvedOnAction(ActionEvent actionEvent) {
        String resId = txtResID.getText();
        LocalDate date = dtDate.getValue();
        String studentID = txtStudentID.getText();
        String roomTypeID = txtRoomTypeID.getText();
        String status = txtStatus.getText();

        boolean isResIDMatched = resId.matches("^RS-\\d{3}$");
        boolean isStudentIdMatched = studentID.matches("^S\\d{3}$");
        boolean isRoomTypeIDMatched = roomTypeID.matches("^RM-\\d{4}$");


        boolean isStatusMatched = status.matches("^Paid|Not Paid$");

        ReservationDTO resrevationDTO = new ReservationDTO(resId, date, studentID, roomTypeID, status);
        ObservableList<ReservationDTO> reservationDTOS = tblReservationData.getItems();

        if (isResIDMatched) {
            if (isStudentIdMatched) {
                if (isRoomTypeIDMatched) {

                        if (isStatusMatched)
                        try {
                            boolean isAdded = reservationBoImpl.addReservation(resrevationDTO);
                            System.out.println(isAdded);
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Add Reservation");
                            alert.setContentText("Are you sure you want to add Reservation ?");
                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.equals(null)) {
                                Navigation.navigate(Routes.RESERVATION, brdPane);
                            } else if (result.get() == ButtonType.OK) {
                                if (isAdded) {
                                    reservationDTOS.add(resrevationDTO);
                                    tblReservationData.setItems(reservationDTOS);
                                    Notifications notifications = Notifications.create().text("Reservation Added Successfuly").title("Add Reservation").position(Pos.CENTER).hideAfter(Duration.seconds(3));
                                    notifications.showInformation();
                                } else {
                                    Notifications notifications = Notifications.create().text("Reservation Not Added.").title("Saving Error").position(Pos.CENTER).hideAfter(Duration.seconds(3));
                                    notifications.showInformation();

                                }
                            } else if (result.get() == ButtonType.CANCEL) {
                                Navigation.navigate(Routes.RESERVATION, brdPane);
                            }


                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                } else {
                    txtRoomTypeID.setFocusColor(Paint.valueOf("Red"));
                    txtRoomTypeID.requestFocus();
                }
            } else {
                txtStudentID.setFocusColor(Paint.valueOf("Red"));
                txtStudentID.requestFocus();            }
        } else {
            txtResID.setFocusColor(Paint.valueOf("Red"));
            txtResID.requestFocus();
        }


    }

    public void btnCancelOnAction(ActionEvent actionEvent) {

    }
}
