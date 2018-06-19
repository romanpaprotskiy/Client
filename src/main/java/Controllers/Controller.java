package Controllers;

import Connection.ConnectionDB;
import Database.Custom;
import Database.Customer;
import Database.Department;
import Database.Employee;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public abstract class Controller {

    public static Scene scene;
    public static Scene scene1;//customs
    public static Scene scene2;//employee
    public static Scene scene3;//customer
    public static Scene scene4;
    public static Scene scene5;//statistics
    public static Stage primaryStage;
    public static Stage tempStage;
    public static Stage backStage;
    public static ConnectionDB connection;
    public static String login;
    public static String password;
    public static String host = "localhost";
    public static int port = 3306;
    public static Customer customer;
    public static Custom custom;
    public static Department department;
    public static Employee employee;
    public static Customer customer1;

    public void ShowAlert(Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Error: " + e.getMessage());
        alert.setContentText("Error" + e);
        alert.showAndWait();
    }

    public void ExitWin(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("Підтвердіть дію");
        alert.setContentText("Ви дійсно хочете вийти?");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK) primaryStage.close();
    }
}
