package Controllers;

import DAO.EmployeeDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

public class ControllerEditEmployee extends Controller {

    @FXML
    public TextField name;
    @FXML
    public DatePicker birthday;
    @FXML
    public DatePicker dateV;
    @FXML
    public TextField pos;
    @FXML
    public TextField sel;
    @FXML
    public Label dep;
    @FXML
    public ImageView deplist;

    private EmployeeDAO employeeDAO;

    public void initialize(){
        try {
            employeeDAO = new EmployeeDAO(connection);
        } catch (SQLException e) {
            ShowAlert(e);
            e.printStackTrace();
        }
    }

    public void Back(MouseEvent mouseEvent) {
        primaryStage.setTitle("Main");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene2);
        primaryStage.show();
    }

    public void MovedDep(MouseEvent mouseEvent) {
        dep.setText(department.getName());
    }

    public void DepClick(MouseEvent mouseEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Layouts/Department.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Відділ");
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (IOException e) {
            ShowAlert(e);
            e.printStackTrace();
        }
    }


    @FXML
    public void updName(MouseEvent mouseEvent) {
        try {
            employeeDAO.EditEmployeeName(Controller.employee.getId(),name.getText());
        } catch (SQLException e) {
            ShowAlert(e);
            e.printStackTrace();
        }
    }

    @FXML
    public void updBdate(MouseEvent mouseEvent) {
        try {
            employeeDAO.EditEmployeeBirthday(Controller.employee.getId(),Date.valueOf(birthday.getValue()));
        } catch (SQLException e) {
            ShowAlert(e);
            e.printStackTrace();
        }
    }

    @FXML
    public void updDate(MouseEvent mouseEvent) {
        try {
            employeeDAO.EditEmployeeDate(Controller.employee.getId(),Date.valueOf(dateV.getValue()));
        } catch (SQLException e) {
            ShowAlert(e);
            e.printStackTrace();
        }
    }

    @FXML
    public void updPos(MouseEvent mouseEvent) {
        try {
            employeeDAO.EditEmployeePosition(Controller.employee.getId(),pos.getText());
        } catch (SQLException e) {
            ShowAlert(e);
            e.printStackTrace();
        }
    }

    @FXML
    public void updSalary(MouseEvent mouseEvent) {
        try {
            employeeDAO.EditEmployeeSalary(Controller.employee.getId(),Double.valueOf(sel.getText()));
        } catch (SQLException e) {
            ShowAlert(e);
            e.printStackTrace();
        }
    }

    @FXML
    public void updDep(MouseEvent mouseEvent) {
        try {
            employeeDAO.EditEmployeeDepartment(Controller.employee.getId(),Controller.department.getID());
        } catch (SQLException e) {
            ShowAlert(e);
            e.printStackTrace();
        }
    }
}
