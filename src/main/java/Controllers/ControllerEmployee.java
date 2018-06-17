package Controllers;

import DAO.CustomDAO;
import DAO.EmployeeDAO;
import Database.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

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
    public TableColumn<Department,String> Dep;
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
    public void initialize(){
        idEmpl.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        EmpName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        BDate.setCellValueFactory(cellData -> cellData.getValue().birthdayProperty());
        DateV.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        Pos.setCellValueFactory(cellData -> cellData.getValue().positionProperty());
        Sel.setCellValueFactory(cellData -> cellData.getValue().salaryProperty().asObject());
        idCus.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        Type.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        DateCus.setCellValueFactory(cellData -> cellData.getValue().date_customProperty());
        DateExe.setCellValueFactory(cellData -> cellData.getValue().dateOfExecutionProperty());
        EmployeeTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> getCustom(newValue));
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

    }
}
