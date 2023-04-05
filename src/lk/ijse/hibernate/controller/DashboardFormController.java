package lk.ijse.hibernate.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import lk.ijse.hibernate.util.Navigation;
import lk.ijse.hibernate.util.Routes;

import java.io.IOException;

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
}
