package Controllers;

import Connection.ConnectionDB;
import Database.Custom;
import Database.Customer;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class Controller {
    public static Scene scene;
    public static Scene scene1;
    public static Scene scene2;
    public static Scene scene3;
    public static Scene scene4;
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
