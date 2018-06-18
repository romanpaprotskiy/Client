package Controllers;

import DAO.CustomDAO;
import DAO.EmployeeDAO;
import Database.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

public class ControllerEmployee extends Controller {

    @FXML
    public ImageView delete;
    @FXML
    public ImageView edit;
    @FXML
    public ImageView add;
    @FXML
    public ImageView search;
    @FXML
    public ImageView update;
    @FXML
    public TableView<Employee> EmployeeTable;
    @FXML
    public TableView<Custom> CustomTable;
    @FXML
    public TableColumn<Employee,Integer> idEmpl;
    @FXML
    public TableColumn<Employee,String> EmpName;
    @FXML
    public TableColumn<Employee,Date> BDate;
    @FXML
    public TableColumn<Employee,Date> DateV;
    @FXML
    public TableColumn<Employee,String> Pos;
    @FXML
    public TableColumn<Employee,String> Dep;
    @FXML
    public TableColumn<Employee,Double> Sel;
    @FXML
    public TableColumn<Custom,Integer> idCus;
    @FXML
    public TableColumn<Custom,String> Type;
    @FXML
    public TableColumn<Custom,Date> DateCus;
    @FXML
    public TableColumn<Custom,Date> DateExe;
    @FXML
    public ImageView customs;
    @FXML
    public ComboBox<String> typeSearch;
    @FXML
    public TextField searchField;

    private EmployeeDAO employeeDAO;

    @FXML
    public void initialize(){
        idEmpl.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        EmpName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        BDate.setCellValueFactory(cellData -> cellData.getValue().birthdayProperty());
        DateV.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        Pos.setCellValueFactory(cellData -> cellData.getValue().positionProperty());
        Dep.setCellValueFactory(cellData -> cellData.getValue().departmentProperty().getValue().nameProperty());
        Sel.setCellValueFactory(cellData -> cellData.getValue().salaryProperty().asObject());
        idCus.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        Type.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        DateCus.setCellValueFactory(cellData -> cellData.getValue().date_customProperty());
        DateExe.setCellValueFactory(cellData -> cellData.getValue().dateOfExecutionProperty());
        EmployeeTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            getCustom(newValue);
            getEmployee(newValue);
        });
        ObservableList<String> list1 = FXCollections.observableArrayList("ID","ПІБ","Посада","Відділ");
        typeSearch.setItems(list1);
        try {
            EmployeeDAO employeeDAO = new EmployeeDAO(connection);
            ObservableList<Employee> list = FXCollections.observableArrayList();
            list.addAll(employeeDAO.selectAllEmployees());
            EmployeeTable.setItems(list);
        } catch (SQLException e) {
            ShowAlert(e);
            e.printStackTrace();
        }
    }

    private void getEmployee(Employee employee) {
        Controller.employee = employee;
    }

    private void getCustom(Employee employee) {
        try {
            CustomDAO customDAO = new CustomDAO(connection);
            ObservableList<Custom> list = FXCollections.observableArrayList();
            list.addAll(customDAO.getCustomEmployee(EmployeeTable.getSelectionModel().getSelectedItem().getId()));
            CustomTable.setItems(list);
        } catch (SQLException e) {
            ShowAlert(e);
            e.printStackTrace();
        }
    }

    @FXML
    public void ExitApp(MouseEvent mouseEvent) {
        ExitWin();
    }

    @FXML
    public void CustomClick(MouseEvent mouseEvent) {
        scene2 = primaryStage.getScene();
        primaryStage.setTitle("Main");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene1);
        primaryStage.show();
    }

    @FXML
    public void DeleteEmployee(MouseEvent mouseEvent) {
        int indexDB = EmployeeTable.getSelectionModel().getSelectedItem().getId();
        int index = EmployeeTable.getSelectionModel().getSelectedIndex();
        try {
            EmployeeDAO employeeDAO = new EmployeeDAO(Controller.connection);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "DeleteCustom", ButtonType.YES, ButtonType.NO);
            alert.setTitle("Delete");
            alert.setHeaderText("Підтвердіть дію");
            alert.setContentText("Ви дійсно хочете видалити?" + "\n" + EmployeeTable.getSelectionModel().getSelectedItem().getId() +
                    " " + EmployeeTable.getSelectionModel().getSelectedItem().getName());
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                employeeDAO.DeleteEmployee(indexDB);
                EmployeeTable.getItems().remove(index);
            }
        } catch (SQLException e) {
            ShowAlert(e);
        }
    }

    public void addEmpl(MouseEvent mouseEvent) {
        scene3 = primaryStage.getScene();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Layouts/AddEmployee.fxml"));
            primaryStage.setTitle("Add");
            primaryStage.setScene(new Scene(root));
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (IOException e) {
            ShowAlert(e);
            e.printStackTrace();
        }
    }

    public void EditEmpl(MouseEvent mouseEvent) {
        int index = EmployeeTable.getSelectionModel().getSelectedIndex();
        if (index < 0) ShowAlert(new NullPointerException("Виберіть поле для редагування"));
        else {
            scene4 = primaryStage.getScene();
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/Layouts/EditEmployee.fxml"));
                primaryStage.setTitle("Edit");
                primaryStage.setResizable(false);
                primaryStage.setScene(new Scene(root));
                primaryStage.show();
            } catch (IOException e) {
                ShowAlert(e);
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void Search(MouseEvent mouseEvent) {
        try {
            switch (typeSearch.getValue()) {
                case "ID":
                    ObservableList<Employee> list = FXCollections.observableArrayList();
                    list.addAll(employeeDAO.searchID(Integer.parseInt(searchField.getText())));
                    EmployeeTable.setItems(list);
                    break;
                case "ПІБ":
                    ObservableList<Employee> list1 = FXCollections.observableArrayList();
                    list1.addAll(employeeDAO.searchName(searchField.getText()));
                    EmployeeTable.setItems(list1);
                    break;
                case "Посада":
                    ObservableList<Employee> list2 = FXCollections.observableArrayList();
                    list2.addAll(employeeDAO.searchPos(searchField.getText()));
                    EmployeeTable.setItems(list2);
                    break;
                case "Відділ":
                    ObservableList<Employee> list3 = FXCollections.observableArrayList();
                    list3.addAll(employeeDAO.searchDep(searchField.getText()));
                    EmployeeTable.setItems(list3);
                    break;
            }
        } catch (SQLException e) {
            ShowAlert(e);
            e.printStackTrace();
        }
    }
}
