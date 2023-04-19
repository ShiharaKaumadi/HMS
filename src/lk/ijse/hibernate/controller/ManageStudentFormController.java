package lk.ijse.hibernate.controller;

import com.jfoenix.controls.JFXDatePicker;
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
import lk.ijse.hibernate.bo.custom.StudentBo;
import lk.ijse.hibernate.dto.StudentDTO;
import lk.ijse.hibernate.util.Navigation;
import lk.ijse.hibernate.util.Routes;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ManageStudentFormController {
    private final ObservableList<StudentDTO> obList = FXCollections.observableArrayList();
    public BorderPane brdPane;
    public TableView tblStudentData;
    public TableColumn colStudentId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colContactNo;
    public TableColumn colDob;
    public TableColumn colGender;
    public TableColumn colDelete;
    public Pane searchBar;
    public TextField txtSearch;
    public JFXTextField txtStudentID;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtContactNo;
    public JFXDatePicker dtDob;
    public JFXTextField txtGender;
    StudentBo studentBoImpl = (StudentBo) BOFactory.getBoFactory().getBO(BOTypes.STUDENT);
    private StudentDTO studentDTO;

    public void initialize() {
        setCellValueFactory();
        loadItemTableData();
        loadNextStudentId();

    }

    private void loadNextStudentId() {
        String stdId = null;
        try {
            stdId = studentBoImpl.getNextStudnetId();
            txtStudentID.setText(stdId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private void loadItemTableData() {
        List<StudentDTO> studentDTOS = null;


        try {
            studentDTOS = studentBoImpl.getAllItems();
            for (StudentDTO studentDTO : studentDTOS) {

                StudentDTO studentDTO1 = new StudentDTO(studentDTO.getStudentId(), studentDTO.getName(), studentDTO.getAddress(), studentDTO.getContactNo(), studentDTO.getDob(), studentDTO.getGender());
                obList.add(studentDTO1);
                System.out.println(studentDTO1);

                tblStudentData.setItems(obList);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContactNo.setCellValueFactory(new PropertyValueFactory<>("contactNo"));
        colDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));


    }


    public void btnDashBoardOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.DASHBOARD, brdPane);
    }

    public void btnManageStudentOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.STUDENT, brdPane);
    }

    public void btnManageRoomsOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ROOMS, brdPane);
    }

    public void btnManageReservations(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RESERVATION, brdPane);
    }

    public void btnAddOnAction(ActionEvent actionEvent) throws IOException {
        String studentId = txtStudentID.getText();

        String name = txtName.getText();
        String address = txtAddress.getText();
        LocalDate dob = dtDob.getValue();
        String contactNo = txtContactNo.getText();
        String gender = txtGender.getText();

        boolean isNameMatched = name.matches("^[a-zA-Z][a-zA-Z\\s]{0,40}$");
        boolean isAddressMatched = address.matches("^[a-zA-Z][a-zA-Z\\s]{0,100}$");
        boolean isContactMatched = contactNo.matches("^(070|071|072|074|075|078|077|076)([0-9]{7})$");
        boolean isGenderMatched = gender.matches("^(Female|female|Male|male)$");
        StudentDTO studentDTO = new StudentDTO(studentId,name, address, contactNo, dob, gender);
        ObservableList<StudentDTO> studentDTOS = tblStudentData.getItems();

        if (isNameMatched) {
            if (isAddressMatched) {
                if (isContactMatched) {
                    if (isGenderMatched) {
                        try {
                            boolean isAdded = studentBoImpl.addStudent(studentDTO);
                            System.out.println(isAdded);
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Add Student");
                            alert.setContentText("Are you sure you want to add student " + txtStudentID.getText() + " ?");
                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.equals(null)) {
                                Navigation.navigate(Routes.STUDENT, brdPane);
                            } else if (result.get() == ButtonType.OK) {
                                if (isAdded) {
                                    studentDTOS.add(studentDTO);
                                    tblStudentData.setItems(studentDTOS);
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
                        txtGender.setFocusColor(Paint.valueOf("Red"));
                        txtGender.requestFocus();
                    }
                } else {
                    txtContactNo.setFocusColor(Paint.valueOf("Red"));
                    txtContactNo.requestFocus();
                }
            } else {
                txtAddress.setFocusColor(Paint.valueOf("Red"));
                txtAddress.requestFocus();
            }
        } else {
            txtName.setFocusColor(Paint.valueOf("Red"));
            txtName.requestFocus();
        }

    }

    public void txtSearchOnAction(ActionEvent actionEvent) {
        String id = txtSearch.getText();
        try {
            StudentDTO studentDTO = studentBoImpl.searchStudent(id);
            if (studentDTO != null) {
                fillData(studentDTO);

            } else {
                Notifications notifications = Notifications.create().title(" Search student").text("student Not Found").hideAfter(Duration.seconds(3)).position(Pos.TOP_CENTER);
                notifications.show();
                txtStudentID.setText("");
                txtName.setText("");
                txtContactNo.setText("");
                txtAddress.setText("");
                txtGender.setText("");

            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void fillData(StudentDTO studentDTO) {
        txtStudentID.setText(studentDTO.getStudentId());
        txtName.setText(studentDTO.getName());
        txtContactNo.setText(studentDTO.getContactNo());
        txtAddress.setText(studentDTO.getAddress());
        dtDob.setValue(studentDTO.getDob());
        txtGender.setText(studentDTO.getGender());


    }


    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String id = txtStudentID.getText();
        boolean isDeleted;
        try {
            isDeleted = studentBoImpl.deleteStudent(id);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Student");
            alert.setContentText("Are you sure you want to delete details ?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.equals(null)) {
                Navigation.navigate(Routes.STUDENT, brdPane);
            } else if (result.get() == ButtonType.OK) {
                if (isDeleted) {
                    int selectedIndex = tblStudentData.getSelectionModel().getSelectedIndex();
                    tblStudentData.getItems().remove(selectedIndex);
                    Notifications notifications = Notifications.create().text("Student Details Deleted.").title("Delete ").position(Pos.CENTER).hideAfter(Duration.seconds(3));
                    notifications.showInformation();
                } else {
                    Notifications notifications = Notifications.create().text("Student Details Not Found.").title("Error").position(Pos.CENTER).hideAfter(Duration.seconds(3));
                    notifications.showError();
                }
            } else if (result.get() == ButtonType.CANCEL) {
                Navigation.navigate(Routes.STUDENT, brdPane);
            }


        } catch (ClassNotFoundException e) {
            Notifications notifications = Notifications.create().text("Driver Not Found.").title("ClassNotFound Exception").position(Pos.CENTER).hideAfter(Duration.seconds(3));
            notifications.showError();

        } catch (SQLException e) {
            Notifications notifications = Notifications.create().text("Student Not Identified").title("Warning").position(Pos.CENTER).hideAfter(Duration.seconds(3));
            notifications.showError();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws IOException {
        String studentID = txtStudentID.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        LocalDate dob = dtDob.getValue();
        String contactNo = txtContactNo.getText();
        String gender = txtGender.getText();
        boolean isStudentIdMatched = studentID.matches("^S\\d{3}$");
        boolean isNameMatched = name.matches("^[a-zA-Z][a-zA-Z\\s]{0,40}$");
        boolean isAddressMatched = address.matches("^[a-zA-Z][a-zA-Z\\s]{0,100}$");
        boolean isContactMatched = contactNo.matches("^(070|071|072|074|075|078|077|076)([0-9]{7})$");
        boolean isGenderMatched = gender.matches("^(Female|female|Male|male)$");
        StudentDTO studentDTO = new StudentDTO(studentID, name, address, contactNo, dob, gender);
        ObservableList<StudentDTO> studentDTOS = tblStudentData.getItems();

        if (isStudentIdMatched) {
            if (isNameMatched) {
                if (isAddressMatched) {
                    if (isContactMatched) {
                        if (isGenderMatched) {
                            try {
                                studentBoImpl.updateStudentDetails(studentDTO);
                                System.out.println(" I am in controller");
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setTitle("Update Student");
                                alert.setContentText("Are you sure you want to update student " + txtStudentID.getText() + " ?");
                                Optional<ButtonType> result = alert.showAndWait();
                                if (result.equals(null)) {
                                    Navigation.navigate(Routes.STUDENT, brdPane);
                                } else if (result.get() == ButtonType.OK) {
                                        studentDTOS.add(studentDTO);
                                        tblStudentData.setItems(studentDTOS);

                                        Notifications notifications = Notifications.create().text("Student Updated Successfuly").title("Update Student").position(Pos.CENTER).hideAfter(Duration.seconds(3));
                                        notifications.showInformation();
                                    } else {
                                    Notifications notifications = Notifications.create().text("Student Not Updated.").title("Saving Error").position(Pos.CENTER).hideAfter(Duration.seconds(3));
                                    notifications.showInformation();
                                } {
                                    Navigation.navigate(Routes.STUDENT, brdPane);
                                }

                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        } else {
                            txtGender.setFocusColor(Paint.valueOf("Red"));
                            txtGender.requestFocus();
                        }
                    } else {
                        txtContactNo.setFocusColor(Paint.valueOf("Red"));
                        txtContactNo.requestFocus();
                    }
                } else {
                    txtAddress.setFocusColor(Paint.valueOf("Red"));
                    txtAddress.requestFocus();
                }
            } else {
                txtName.setFocusColor(Paint.valueOf("Red"));
                txtName.requestFocus();
            }
        } else {
            txtStudentID.setFocusColor(Paint.valueOf("Red"));
            txtStudentID.requestFocus();
        }
    }
   

    public void tblDataOnMouseClickOnAction(MouseEvent mouseEvent) {
        studentDTO = (StudentDTO) tblStudentData.getSelectionModel().getSelectedItem();
        if (studentDTO != null) {
            txtStudentID.setText(studentDTO.getStudentId());
            txtName.setText(studentDTO.getName());
            txtContactNo.setText(studentDTO.getContactNo());
            dtDob.setValue(studentDTO.getDob());
            txtGender.setText(String.valueOf(studentDTO.getGender()));
            txtAddress.setText(studentDTO.getAddress());
        }
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        txtStudentID.setText("");
        txtName.setText("");
        txtContactNo.setText("");
        dtDob.setValue(null);
        txtGender.setText("");
        txtAddress.setText("");
    }
}
