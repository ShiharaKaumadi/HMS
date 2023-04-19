package lk.ijse.hibernate.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import lk.ijse.hibernate.bo.custom.BOFactory;
import lk.ijse.hibernate.bo.custom.BOTypes;
import lk.ijse.hibernate.bo.custom.ReservationBo;
import lk.ijse.hibernate.bo.custom.StudentBo;
import lk.ijse.hibernate.dto.CustomDTO;
import lk.ijse.hibernate.dto.ReservationDTO;
import lk.ijse.hibernate.entity.Reservation;
import lk.ijse.hibernate.util.Navigation;
import lk.ijse.hibernate.util.Routes;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;

public class DashboardFormController {
    public BorderPane brdPane;
    public ScrollPane scrlPaymentDueList;
    public Label lblTotalStudents;
    public Label lblGirls;
    public Label lblBoys;
    public Label lblNonACAvailable;
    public Label lblNonAcFoodAvailable;
    public Label lblACAvailable;
    public Label lblACFoodAvailable;
    public ScrollPane scrllAvailableKeys;
    public GridPane gridPane;
    public AnchorPane anchrPane;
    public GridPane grid;
    public AnchorPane anchorPane;
    public Label lblTime;
    ArrayList <ReservationDTO> reservationDTOArrayList = new ArrayList<>();

    StudentBo studentBOImpl = (StudentBo) BOFactory.getBoFactory().getBO(BOTypes.STUDENT);
    ReservationBo reservationBoImpl = (ReservationBo) BOFactory.getBoFactory().getBO(BOTypes.RESERVATION);

    public void initialize() throws SQLException, ClassNotFoundException {
        loadTotalStudentCount();
        loadTotalFemaleStudentCount();
        loadTotalFemaleStudentCount();
        loadTotalMaleStudentCount();
        loadTime();
        ArrayList<ReservationDTO> paymentDueStudents = reservationBoImpl.getPaymentDueStudents();
        paymentDueStudents.addAll(getData());
        System.out.println(paymentDueStudents.size());
        int column=0;
        int row =1;

        try {
            for(int i=0; i<paymentDueStudents.size();i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/lk/ijse/hibernate/views/PaymentDueForm.fxml"));
                Pane anchorPane = fxmlLoader.load();
                PaymentDueFormController itemFormController=fxmlLoader.getController();
                itemFormController.setData(paymentDueStudents.get(i));

                if(column ==1){
                    column =0;
                    row++;
                }
                gridPane.add(anchorPane,column,row++);
                gridPane.setMinWidth(Region.USE_COMPUTED_SIZE);
                gridPane.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gridPane.setMaxWidth(Region.USE_COMPUTED_SIZE);

                gridPane.setMaxHeight(Region.USE_COMPUTED_SIZE);
                gridPane.setPrefHeight(Region.USE_COMPUTED_SIZE);
                gridPane.setMaxHeight(Region.USE_COMPUTED_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void loadTime() {
        LocalTime time = LocalTime.now();

        if (time.isAfter(LocalTime.NOON)){
            lblTime.setText(time.getHour()+"."+time.getMinute()+" PM");
        }else{
            lblTime.setText(time.getHour()+"."+time.getMinute()+" AM");
        }
    }

    private ArrayList<ReservationDTO> getData() throws SQLException, ClassNotFoundException {
        ArrayList<ReservationDTO> dueList = new ArrayList<>();
        for(ReservationDTO reservationDTO : dueList){
            ReservationDTO reservationDTO1 = new ReservationDTO(reservationDTO.getStudentId(),reservationDTO.getStatus());
            dueList.add(reservationDTO);
        }
        return dueList  ;

    }

    private void loadTotalMaleStudentCount() {
        try {
            long student= studentBOImpl.countMaleStudent();
            lblBoys.setText(String.valueOf(student));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadTotalFemaleStudentCount() {
        try {
            long student= studentBOImpl.countFemaleStudent();
            lblGirls.setText(String.valueOf(student));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadTotalStudentCount() {
        try {
            long student= studentBOImpl.countAllStudent();
            lblTotalStudents.setText(String.valueOf(student));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    public void btnManageRoomsOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ROOMS,brdPane);
    }

    public void btnDashboardOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.DASHBOARD, brdPane);
    }

    public void btnManageStudentsOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.STUDENT,brdPane);
    }

    public void btnReservationsOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RESERVATION,brdPane);
    }

    public void btnSettingsOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.SETTINGS,brdPane);
    }
}
