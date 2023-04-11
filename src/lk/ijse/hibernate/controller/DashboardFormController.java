package lk.ijse.hibernate.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import lk.ijse.hibernate.bo.custom.BOFactory;
import lk.ijse.hibernate.bo.custom.BOTypes;
import lk.ijse.hibernate.bo.custom.StudentBo;
import lk.ijse.hibernate.util.Navigation;
import lk.ijse.hibernate.util.Routes;

import java.io.IOException;
import java.sql.SQLException;

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

    StudentBo studentBOImpl = (StudentBo) BOFactory.getBoFactory().getBO(BOTypes.STUDENT);

    public void initialize(){

        loadTotalStudentCount();
        loadTotalFemaleStudentCount();
        loadTotalFemaleStudentCount();
        loadTotalMaleStudentCount();
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
