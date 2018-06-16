package Controllers;

import Database.Customer;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.sql.Connection;
import Connection.*;

public  class Controller {
    public static Scene scene;
    public static Stage primaryStage;
    public static Stage tempStage;
    public static ConnectionDB connection;
    public static String login;
    public static String password;
    public static String host = "localhost";
    public static int port = 3306;
    public static Customer customer;

    public void ShowAlert(Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Error in " + e.getMessage());
        alert.setContentText("Error" + e);
        alert.showAndWait();
    }
}
