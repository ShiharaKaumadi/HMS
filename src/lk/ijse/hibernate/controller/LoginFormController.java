package lk.ijse.hibernate.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import lk.ijse.hibernate.dao.custom.impl.UserDaoImpl;
import lk.ijse.hibernate.entity.User;
import lk.ijse.hibernate.util.Navigation;
import lk.ijse.hibernate.util.Routes;

import java.io.IOException;

public class LoginFormController {
    public BorderPane brdPane;
    public Pane pnLogin;
    public JFXTextField txtUsername;
    public JFXPasswordField pswd;
    public Label lblUserNameError;
    public Label lblPsswordError;

    UserDaoImpl userDAOImpl = new UserDaoImpl();

    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
        String username =txtUsername.getText();
        String password = pswd.getText();

        if (username.matches("^Nimal$")){
        }else{
            lblUserNameError.setText("Invalid Username");
            txtUsername.setFocusColor(Paint.valueOf("Red"));
            txtUsername.requestFocus();
        }

            if (password.matches("^\\d{4}$")){
                Navigation.navigate(Routes.DASHBOARD,brdPane);
            }else{
                lblUserNameError.setText("");
                lblPsswordError.setText("Invalid Password");
                pswd.setFocusColor(Paint.valueOf("Red"));
                pswd.requestFocus();

            }


    }

    public void hyperForgetPasswordOnAction(ActionEvent actionEvent) {

    }

    public void btnCreateAccountOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.USER,brdPane);
    }
}
