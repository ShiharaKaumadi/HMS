package lk.ijse.hibernate.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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
import lk.ijse.hibernate.dto.RoomDTO;
import lk.ijse.hibernate.dto.StudentDTO;
import lk.ijse.hibernate.entity.Reservation;
import lk.ijse.hibernate.entity.Room;
import lk.ijse.hibernate.entity.Student;
import lk.ijse.hibernate.util.FactoryConfiguration;
import lk.ijse.hibernate.util.Navigation;
import lk.ijse.hibernate.util.Routes;
import lk.ijse.hibernate.views.tm.ReservationTM;
import org.controlsfx.control.Notifications;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

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

    public JFXDatePicker dtDate;
    public JFXButton btnReserved;

    public JFXComboBox cmbStudentID;
    public JFXComboBox cmbRoomTypeID;
    public JFXComboBox cmbStatus;
    private final ObservableList<ReservationDTO> obList = FXCollections.observableArrayList();
    ReservationBo reservationBoImpl = (ReservationBo) BOFactory.getBoFactory().getBO(BOTypes.RESERVATION);
    StudentBo studentBoImpl = (StudentBo) BOFactory.getBoFactory().getBO(BOTypes.STUDENT);

    public void initialize(){
        loadRegisteredStudents();
        loadRoomTypeIDsAvailable();
        loadTableData();
        loadSetCellValueFactory();


        ObservableList<String> status = FXCollections.observableArrayList("Paid","Pending");
        cmbStatus.setItems(status);

    }

    private void loadSetCellValueFactory() {
        colResID.setCellValueFactory(new PropertyValueFactory<>("resId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colStudentIID.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colRoomTypeID.setCellValueFactory(new PropertyValueFactory<>("roomTypeId"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    private void loadTableData() {
        List<ReservationDTO> roomDTOS = null;
        try {
            roomDTOS = reservationBoImpl.getAllReservations();
            for (ReservationDTO roomDTO : roomDTOS) {

                ReservationDTO roomDTO1 = new ReservationDTO(roomDTO.getResId(),roomDTO.getDate(),roomDTO.getStudentId(),roomDTO.getRoomTypeId(),roomDTO.getStatus());
                obList.add(roomDTO1);
                System.out.println(roomDTO1);
                tblReservationData.setItems(obList);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadRoomTypeIDsAvailable() {
        try {
            ObservableList<String> observableList = FXCollections.observableArrayList();
            ArrayList<String> idList = reservationBoImpl.loadRoomTypeID();



            for (String id : idList) {
                observableList.add(id);

            }
            cmbRoomTypeID.setItems(observableList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }




    private void loadRegisteredStudents() {
        try {
            ObservableList<String> observableList = FXCollections.observableArrayList();
            ArrayList<String> idList = reservationBoImpl.loadAllStudentIDs();

            for (String id : idList) {
                observableList.add(id);
            }
            cmbStudentID.setItems(observableList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

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
        String studentID = (String) cmbStudentID.getSelectionModel().getSelectedItem();
        System.out.println(studentID);
        String roomTypeID = cmbRoomTypeID.getSelectionModel().getSelectedItem().toString();
        System.out.println(roomTypeID);
        String status = cmbStatus.getSelectionModel().getSelectedItem().toString();
        boolean isResIDMatched = resId.matches("^RS-\\d{3}$");

        boolean isStatusMatched = status.matches("^Paid|Pending$");

        ReservationDTO resrevationDTO = new ReservationDTO(resId, date,studentID,roomTypeID,status);
        ObservableList<ReservationDTO> reservationDTOS = tblReservationData.getItems();

        if (isResIDMatched) {
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

                                } catch(SQLException e){
                                    e.printStackTrace();
                                } catch(IOException e){
                                    e.printStackTrace();
                                } catch(ClassNotFoundException e){
                                    e.printStackTrace();
                                }

        } else {
            txtResID.setFocusColor(Paint.valueOf("Red"));
            txtResID.requestFocus();
        }


    }



    public void btnCancelOnAction(ActionEvent actionEvent) {

    }

    public void cmbSelectStudentIdOnAction(ActionEvent actionEvent) {

    }
}
