package Controllers;

import DAO.CustomDAO;
import DAO.EmployeeDAO;
import Database.*;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.sql.Date;
import java.sql.SQLException;


public class ControllerMain {

    @FXML
    public ImageView customers;
    @FXML
    public ImageView employees;
    @FXML
    public ImageView statistics;
    @FXML
    public AnchorPane main;
    @FXML
    public ImageView customs;
    @FXML
    public ImageView exit;
    @FXML
    public ImageView update;
    @FXML
    public ImageView add;
    @FXML
    public ImageView search;
    @FXML
    public ImageView delete;
    @FXML
    public ComboBox typeSearch;
    @FXML
    public ImageView edit;
    @FXML
    public TableColumn<Custom,Integer> cId;
    @FXML
    public TableColumn<Custom,String> cType;
    @FXML
    public TableColumn<Custom,Integer> cCQ;
    @FXML
    public TableColumn<Custom,Date> cDC;
    @FXML
    public TableColumn<Custom,Date> cDE;
    @FXML
    public TableColumn<Custom,Date> cAA;
    @FXML
    public TableColumn<Custom,Date> cAD;
    @FXML
    public TableView customTable;
    @FXML
    public TableView EmplCus;
    @FXML
    public TableColumn<Custom,String> Cus;
    @FXML
    public TableColumn<Custom,String> Rep;
    @FXML
    public TableColumn<Custom,String> Empl;
    @FXML
    public TableColumn<Custom,String> Pos;

    @FXML
    private void initialize () {
        cId.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        cType.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        cCQ.setCellValueFactory(cellData -> cellData.getValue().countOfQuestionsProperty().asObject());
        cDC.setCellValueFactory(cellData -> cellData.getValue().date_customProperty());
        cDE.setCellValueFactory(cellData -> cellData.getValue().dateOfExecutionProperty());
        cAA.setCellValueFactory(cellData -> cellData.getValue().approvedAccoutantProperty());
        cAD.setCellValueFactory(cellData -> cellData.getValue().approvedDirectionProperty());
        Cus.setCellValueFactory(cellData -> cellData.getValue().customerProperty().getValue().person_customerProperty());
        Rep.setCellValueFactory(cellData -> cellData.getValue().customerProperty().getValue().person_representativeProperty());
        Empl.setCellValueFactory(cellData -> cellData.getValue().employeeProperty().getValue().nameProperty());
        Pos.setCellValueFactory(cellData -> cellData.getValue().employeeProperty().getValue().positionProperty());
    }

    @FXML
    public void SettingsClick(MouseEvent mouseEvent) {

    }

    @FXML
    public void CustomersClick(MouseEvent mouseEvent) {

    }

    @FXML
    public void EmployeesClick(MouseEvent mouseEvent) {

    }

    @FXML
    public void StatisticsClick(MouseEvent mouseEvent) {

    }

    public void CustomsClick(MouseEvent mouseEvent) throws SQLException {

    }

    @FXML
    public void Update(MouseEvent mouseEvent) {
        try {
            CustomDAO customDAO = new CustomDAO(Controller.connection);
            ObservableList<Custom> list = FXCollections.observableArrayList();
            list.addAll(customDAO.selectAllCustom());
            customTable.setItems(list);
        } catch (SQLException e) {
            ShowAlert(e);
        }
    }

    public void CustomTableClick(MouseEvent mouseEvent) {
        try {
            CustomDAO customDAO = new CustomDAO(Controller.connection);
            ObservableList<Custom> list = FXCollections.observableArrayList();
            list.addAll(customDAO.selectAllCustom());
            EmplCus.setItems(list);
        } catch (SQLException e) {
            ShowAlert(e);
        }
    }

    private void ShowAlert(SQLException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Error in connection");
        alert.setContentText("Error" + e);
        alert.showAndWait();
    }

    public void EmplClick(TableColumn.CellEditEvent<Custom, Integer> customIntegerCellEditEvent) {
        try {
            CustomDAO customDAO = new CustomDAO(Controller.connection);
            ObservableList<Custom> list = FXCollections.observableArrayList();
            list.addAll(customDAO.selectAllCustom());
            EmployeeDAO employeeDAO = new EmployeeDAO(Controller.connection);
            //employeeDAO.searchEmployee();
            //EmplCus.setItems(list);
        } catch (SQLException e) {
            ShowAlert(e);
        }
    }
}
