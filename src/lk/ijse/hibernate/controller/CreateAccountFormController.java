package lk.ijse.hibernate.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Paint;
import javafx.util.Duration;
import lk.ijse.hibernate.bo.custom.BOFactory;
import lk.ijse.hibernate.bo.custom.BOTypes;
import lk.ijse.hibernate.bo.custom.StudentBo;
import lk.ijse.hibernate.bo.custom.UserBO;
import lk.ijse.hibernate.dto.UserDTO;
import lk.ijse.hibernate.util.Navigation;
import lk.ijse.hibernate.util.Routes;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class CreateAccountFormController {
    public JFXTextField textUserID;
    public JFXTextField txtUsername;
    public JFXPasswordField pswd;
    public BorderPane brdPane;
    UserBO userBoImpl = (UserBO) BOFactory.getBoFactory().getBO(BOTypes.USER);

    public void btnCreateAccountOnAction(ActionEvent actionEvent) {
        String userID = textUserID.getText();
        String username = txtUsername.getText();
        String password = pswd.getText();
        UserDTO userDTO = new UserDTO(userID,username,password);
        boolean isUserIdMatched = userID.matches("^U-\\d{4}$");
        boolean isUserNameMatched = username.matches("^[a-zA-Z][a-zA-Z\\s]{0,40}$");
        boolean isPasswordMatched =password.matches("^U-\\d{4}$");
        if (isUserIdMatched){
            if (isUserNameMatched){
                if (isPasswordMatched){
                    try{
                        boolean isAdded = userBoImpl.addUser(userDTO);
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Add User");
                        alert.setContentText("Are you sure you want to add user " + textUserID.getText() + " ?");
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.equals(null)) {
                            Navigation.navigate(Routes.USER,brdPane);
                        } else if (result.get() == ButtonType.OK) {
                            if (isAdded) {
                                Notifications notifications = Notifications.create().text("User Added Successfuly").title("Add User").position(Pos.CENTER).hideAfter(Duration.seconds(3));
                                notifications.showInformation();
                            } else {
                                Notifications notifications = Notifications.create().text("User Not Added.").title("Saving Error").position(Pos.CENTER).hideAfter(Duration.seconds(3));
                                notifications.showInformation();
                            }
                        } else if (result.get() == ButtonType.CANCEL) {
                            Navigation.navigate(Routes.USER, brdPane);
                        }


                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    textUserID.setFocusColor(Paint.valueOf("Red"));
                    textUserID.requestFocus();
                }
            } else {
                txtUsername.setFocusColor(Paint.valueOf("Red"));
                txtUsername.requestFocus();
            }
        } else {
            pswd.setFocusColor(Paint.valueOf("Red"));
            pswd.requestFocus();
        }
    }

}
