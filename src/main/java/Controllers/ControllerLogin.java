package Controllers;


import Connection.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.*;
import java.sql.SQLException;

public class ControllerLogin extends Controller {

    @FXML
    public PasswordField passwordfield;
    @FXML
    public Button enter;
    @FXML
    public TextField loginfield;
    @FXML
    public Button exit;
    @FXML
    public ImageView options;

    @FXML
    public void entered(ActionEvent actionEvent) {
        String login = loginfield.getText();
        String password = passwordfield.getText();
        try {
            connection = new ConnectionDB(Controller.host,Controller.port,login,password);
            if (!connection.getConnection().isClosed()) {
                Parent root = FXMLLoader.load(getClass().getResource("/Layouts/MainLayout.fxml"));
                primaryStage.setTitle("Main");
                primaryStage.setScene(new Scene(root));
                primaryStage.setResizable(false);
                primaryStage.show();
            }
        } catch (IOException e) {
            ShowAlert(e);
        } catch (SQLException e) {
            ShowAlertAuto();
        }
    }

    private void ShowAlertAuto() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Incorrect login or password");
        alert.setContentText("Будь ласка введіть коректний логін і пароль");
        alert.showAndWait();
    }

    @FXML
    public void exited(ActionEvent actionEvent) {
        primaryStage.close();
    }

    @FXML
    public void SettingsClick(MouseEvent mouseEvent) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/Layouts/OptionsLayout.fxml"));
        } catch (IOException e) {
            ShowAlert(e);
        }
        tempStage = primaryStage;
        primaryStage.close();
        Stage stage = new Stage();
        primaryStage = stage;
        stage.setTitle("Settings");
        stage.setScene(new Scene(root));
        stage.show();
    }
}


