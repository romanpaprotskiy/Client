package Controllers;

import DAO.EmployeeDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

public class ControllerAddEmployee extends Controller {

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
    public void Back(MouseEvent mouseEvent) {
        primaryStage.close();
        primaryStage.setTitle("Main");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene3);
        primaryStage.show();
    }

    @FXML
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
    public void addEmpl(MouseEvent mouseEvent) {
        try {
            EmployeeDAO employeeDAO = new EmployeeDAO(connection);
            String strn = name.getText();
            Date bdate = Date.valueOf(birthday.getValue());
            Date vdate = Date.valueOf(dateV.getValue());
            String p = pos.getText();
            Double s = Double.valueOf(sel.getText());
            int iddep = department.getID();
            employeeDAO.addEmployee(strn,bdate,vdate,p,s,iddep);
        } catch (SQLException e) {
            ShowAlert(e);
            e.printStackTrace();
        }
    }

    @FXML
    public void MovedDep(MouseEvent mouseEvent) {
        dep.setText(department.getName());
    }
}
