package lk.ijse.hibernate.controller;

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
import lk.ijse.hibernate.bo.custom.RoomBo;
import lk.ijse.hibernate.bo.custom.StudentBo;
import lk.ijse.hibernate.dao.util.DAOFactory;
import lk.ijse.hibernate.dto.RoomDTO;
import lk.ijse.hibernate.dto.StudentDTO;
import lk.ijse.hibernate.util.Navigation;
import lk.ijse.hibernate.util.Routes;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class ManageRoomsFormController {
    public BorderPane brdPane;
    public TableView tblRoomData;
    public TableColumn colRoomTypeId;
    public TableColumn colType;
    public TableColumn colKeyMoney;
    public TableColumn colQtyAvailable;
    public TableColumn colDelete;
    public Pane searchBar;
    public TextField txtSearch;
    public Pane pane;
    public JFXTextField txtRoomTypeId;
    public JFXTextField txtType;
    public JFXTextField txtKeyMoney;
    public JFXTextField txtQty;

    RoomBo roomBoImpl = (RoomBo) BOFactory.getBoFactory().getBO(BOTypes.ROOM);


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

    public void btnAddOnAction(ActionEvent actionEvent) {
        String roomTypeId = txtRoomTypeId.getText();
        String type = txtType.getText();
        String keyMoney = txtKeyMoney.getText();
        int qty = Integer.parseInt(txtQty.getText());

        boolean isRoomTypeMatched =roomTypeId.matches("^RM-\\d{4}$");
        boolean isTypeMatched = type.matches("^NON-AC|non_ac|NON_AC/Food|non-ac/food|AC|AC/Food|ac|ac/food$");
        boolean isKeyMoneyMatched = keyMoney.matches("^\\d{4}|\\d{5}$");
        boolean isQtyMatched = String.valueOf(qty).matches("^\\d{2}$");

        RoomDTO roomDto = new RoomDTO(roomTypeId, type, keyMoney, qty);
        ObservableList items = tblRoomData.getItems();

        if (isRoomTypeMatched) {
            if (isTypeMatched) {
                if (isKeyMoneyMatched) {
                    if (isQtyMatched) {
                            try{
                                boolean isAdded = roomBoImpl.addRoom(roomDto);
                                System.out.println(isAdded);
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setTitle("Add ROOM");
                                alert.setContentText("Are you sure you want to add Room ?");
                                Optional<ButtonType> result = alert.showAndWait();
                                if (result.equals(null)) {
                                    Navigation.navigate(Routes.ROOMS,brdPane);
                                } else if (result.get() == ButtonType.OK) {
                                    if (isAdded) {
                                        items.add(roomDto);
                                        tblRoomData.setItems(items);
                                        Notifications notifications = Notifications.create().text("Student Added Successfuly").title("Add Student").position(Pos.CENTER).hideAfter(Duration.seconds(3));
                                        notifications.showInformation();
                                    } else {
                                        Notifications notifications = Notifications.create().text("Student Not Added.").title("Saving Error").position(Pos.CENTER).hideAfter(Duration.seconds(3));
                                        notifications.showInformation();

                                    }
                                } else if (result.get() == ButtonType.CANCEL) {
                                    Navigation.navigate(Routes.USER, brdPane);
                                }


                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        } else {
                            txtQty.setFocusColor(Paint.valueOf("Red"));
                            txtQty.requestFocus();
                        }
                    } else {
                        txtKeyMoney.setFocusColor(Paint.valueOf("Red"));
                        txtKeyMoney.requestFocus();
                    }
                } else {
                    txtType.setFocusColor(Paint.valueOf("Red"));
                    txtType.requestFocus();
                }
            }else{
                txtRoomTypeId.setFocusColor(Paint.valueOf("Red"));
                txtRoomTypeId.requestFocus();
            }

    }

    public void btnUpdate(ActionEvent actionEvent) {
    }

    public void txtSearchOnAction(ActionEvent actionEvent) {
        String id = txtSearch.getText();
        try {
            RoomDTO roomDTO = roomBoImpl.searchRoom(id);
            if (roomDTO != null) {
                fillData(roomDTO);
                txtSearch.setText("");
            } else {
                Notifications notifications = Notifications.create().title(" Search room").text("room Not Found").hideAfter(Duration.seconds(3)).position(Pos.TOP_CENTER);
                notifications.show();
                txtRoomTypeId.setText("");
                txtKeyMoney.setText("");
                txtType.setText("");
                txtQty.setText("");


            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void fillData(RoomDTO roomDTO) {
        txtRoomTypeId.setText(roomDTO.getRoomTypeId());
        txtType.setText(roomDTO.getRoomType());
        txtKeyMoney.setText(roomDTO.getKeyMoney());
        txtQty.setText(String.valueOf(roomDTO.getQty()));



    }
}
