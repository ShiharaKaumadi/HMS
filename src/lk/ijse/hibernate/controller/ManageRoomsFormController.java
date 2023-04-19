package lk.ijse.hibernate.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
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
import java.util.List;
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
    private final ObservableList<RoomDTO> obList = FXCollections.observableArrayList();

    RoomBo roomBoImpl = (RoomBo) BOFactory.getBoFactory().getBO(BOTypes.ROOM);

    public void initialize(){
        loadSetCellValueFactory();
        loadTableData();
        laodAvaialableAcRooms();
        loadAvaiableACFoodRooms();
        loadAvailableNonACRooms();
        loadAvaialableNonACFoodRooms();
    }

    private void loadSetCellValueFactory() {
        colRoomTypeId.setCellValueFactory(new PropertyValueFactory<>("roomTypeId"));
        colType.setCellValueFactory(new PropertyValueFactory<>("roomType"));
        colKeyMoney.setCellValueFactory(new PropertyValueFactory<>("keyMoney"));
        colQtyAvailable.setCellValueFactory(new PropertyValueFactory<>("qty"));

    }

    private void loadTableData() {
        List<RoomDTO> roomDTOS = null;
        try {
            roomDTOS = roomBoImpl.getAllRooms();
            for (RoomDTO roomDTO : roomDTOS) {

                RoomDTO roomDTO1 = new RoomDTO(roomDTO.getRoomTypeId(),roomDTO.getRoomType(),roomDTO.getKeyMoney(),roomDTO.getQty(),roomDTO.getList());
                obList.add(roomDTO1);
                System.out.println(roomDTO1);
                tblRoomData.setItems(obList);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadAvaialableNonACFoodRooms() {
        roomBoImpl.getAllAvailableACRooms();
    }

    private void loadAvailableNonACRooms() {
    }

    private void loadAvaiableACFoodRooms() {
    }

    private void laodAvaialableAcRooms() {
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
        String roomTypeId = txtRoomTypeId.getText();
        String type = txtType.getText();
        String keyMoney = txtKeyMoney.getText();
        int qty = Integer.parseInt(txtQty.getText());

        boolean isRoomTypeMatched =roomTypeId.matches("^RM-\\d{4}$");
        boolean isTypeMatched = type.matches("^NON-AC|non_ac|NON_AC/Food|non-ac/food|AC|AC/Food|ac|ac/food$");
        boolean isKeyMoneyMatched = keyMoney.matches("^\\d{4}|\\d{5}$");
        boolean isQtyMatched = String.valueOf(qty).matches("^\\d{2}$");

        RoomDTO roomDto = new RoomDTO(roomTypeId, type, keyMoney, qty);
        ObservableList<RoomDTO> items = tblRoomData.getItems();

        if (isRoomTypeMatched) {
            if (isTypeMatched) {
                if (isKeyMoneyMatched) {
                    if (isQtyMatched) {
                        try{
                            roomBoImpl.updateRoom(roomDto);

                            System.out.println(" I am in controller");
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Update Room");
                            alert.setContentText("Are you sure you want to update room " + txtRoomTypeId.getText() + " ?");
                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.equals(null)) {
                                Navigation.navigate(Routes.ROOMS, brdPane);
                            } else if (result.get() == ButtonType.OK) {
                                items.add(roomDto);
                                tblRoomData.setItems(items);

                                    Notifications notifications = Notifications.create().text("Room Details Updated Successfuly").title("Add Student").position(Pos.CENTER).hideAfter(Duration.seconds(3));
                                    notifications.showInformation();
                                } else {
                                    Notifications notifications = Notifications.create().text("Room Not Updated.").title("Saving Error").position(Pos.CENTER).hideAfter(Duration.seconds(3));
                                    notifications.showInformation();

                                }
                            } catch (SQLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }catch (Exception e) {
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

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String id = txtRoomTypeId.getText();
        boolean isDeleted;
        try {
            isDeleted = roomBoImpl.deleteRoom(id);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Room");
            alert.setContentText("Are you sure you want to delete details ?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.equals(null)) {
                Navigation.navigate(Routes.ROOMS, brdPane);
            } else if (result.get() == ButtonType.OK) {
                if (isDeleted) {
                    int selectedIndex = tblRoomData.getSelectionModel().getSelectedIndex();
                    tblRoomData.getItems().remove(selectedIndex);
                    Notifications notifications = Notifications.create().text("Room Details Deleted.").title("Delete ").position(Pos.CENTER).hideAfter(Duration.seconds(3));
                    notifications.showInformation();
                } else {
                    Notifications notifications = Notifications.create().text("Room Details Not Found.").title("Error").position(Pos.CENTER).hideAfter(Duration.seconds(3));
                    notifications.showError();
                }
            } else if (result.get() == ButtonType.CANCEL) {
                Navigation.navigate(Routes.ROOMS, brdPane);
            }


        } catch (ClassNotFoundException e) {
            Notifications notifications = Notifications.create().text("Driver Not Found.").title("ClassNotFound Exception").position(Pos.CENTER).hideAfter(Duration.seconds(3));
            notifications.showError();

        } catch (SQLException e) {
            Notifications notifications = Notifications.create().text("Room Not Identified").title("Warning").position(Pos.CENTER).hideAfter(Duration.seconds(3));
            notifications.showError();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnClear(ActionEvent actionEvent) {
        txtRoomTypeId.setText("");
        txtType.setText("");
        txtKeyMoney.setText("");
        txtQty.setText("");
    }

    public void tblOnMouseClicked(MouseEvent mouseEvent) {
        RoomDTO roomDTO = (RoomDTO) tblRoomData.getSelectionModel().getSelectedItem();
        if (roomDTO != null) {
            txtRoomTypeId.setText(roomDTO.getRoomTypeId());
            txtType.setText(roomDTO.getRoomType());
            txtKeyMoney.setText(roomDTO.getKeyMoney());
            txtQty.setText(String.valueOf(roomDTO.getQty()));
        }
    }
}
