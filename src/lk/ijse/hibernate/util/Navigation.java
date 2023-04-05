package lk.ijse.hibernate.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigation {
    private static BorderPane pane;

    public static void navigate(Routes routes,BorderPane pane) throws IOException {
        Navigation.pane=pane;
        Navigation.pane.getChildren().clear();
        Stage window = (Stage)Navigation.pane.getScene().getWindow();
        switch(routes){
            case DASHBOARD:
                window.setTitle("Dashboard");
                initUI("DashboardForm.fxml");
                break;

            case STUDENT:
                window.setTitle("Manage Student");
                initUI("ManageStudentForm.fxml");
                break;

            case ROOMS:
                window.setTitle("Manage Rooms");
                initUI("ManageRoomsForm.fxml");
                break;

            case RESERVATION:
                window.setTitle("Manage Reservations");
                initUI("ReservationForm.fxml");
                break;



        }

    }

    public static void initUI(String location) throws IOException {
        Navigation.pane.getChildren().add(FXMLLoader.load(Navigation.class.getResource("/lk/ijse/hibernate/views/"+location)));

    }
}
