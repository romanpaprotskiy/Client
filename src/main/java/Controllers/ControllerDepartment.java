package Controllers;

import DAO.CustomDAO;
import DAO.EmployeeDAO;
import Database.Department;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.sql.SQLException;

public class ControllerDepartment extends Controller {

    @FXML
    public TableView<Department> deptable;
    @FXML
    public TableColumn<Department,Integer> idDep;
    @FXML
    public TableColumn<Department,String> NameDep;
    @FXML
    public TextField addDepName;


    public void initialize(){
        idDep.setCellValueFactory(cellData -> cellData.getValue().IDProperty().asObject());
        NameDep.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        deptable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> setDepId(newValue));
        try {
            EmployeeDAO employeeDAO = new EmployeeDAO(connection);
            ObservableList<Department> list = FXCollections.observableArrayList();
            list.addAll(employeeDAO.selectAllDepartment());
            deptable.setItems(list);
        } catch (SQLException e) {
            ShowAlert(e);
            e.printStackTrace();
        }
    }

    private void setDepId(Department newValue) {
        Controller.department = newValue;
    }

    @FXML
    public void DeleteDep(MouseEvent mouseEvent) {
        int indexDB = deptable.getSelectionModel().getSelectedItem().getID();
        int index = deptable.getSelectionModel().getSelectedIndex();
        try {
            EmployeeDAO employeeDAO = new EmployeeDAO(Controller.connection);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "DeleteCustom", ButtonType.YES, ButtonType.NO);
            alert.setTitle("Delete");
            alert.setHeaderText("Підтвердіть дію");
            alert.setContentText("Ви дійсно хочете видалити?" + "\n" + deptable.getSelectionModel().getSelectedItem().getID() +
                    " " + deptable.getSelectionModel().getSelectedItem().getName());
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                employeeDAO.deletedepartment(indexDB);
                deptable.getItems().remove(index);
            }
        } catch (SQLException e) {
            ShowAlert(e);
        }
    }


    @FXML
    public void AddDep(MouseEvent mouseEvent) {
        if (addDepName.getText() != null) {
            try {
                EmployeeDAO employeeDAO = new EmployeeDAO(connection);
                employeeDAO.addDepartment(addDepName.getText());
            } catch (SQLException e) {
                ShowAlert(e);
                e.printStackTrace();
            }
        } else {
            ShowAlert(new Exception("Введіть назву відділу"));
        }
    }
}
