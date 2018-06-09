package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerOptions implements Initializable {

    @FXML
    public TextField hostfield;
    @FXML
    public TextField portfield;
    @FXML
    public Button Save;
    @FXML
    public ImageView back;

    @FXML
    public void Saving(ActionEvent actionEvent) {
        try {
            String host = hostfield.getText();
            int port = Integer.parseInt(portfield.getText());
            Controller.host = host;
            Controller.port = port;
        } catch (NumberFormatException exc){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Incorrect field");
            alert.setContentText("Error" + exc);
            alert.showAndWait();
        }
    }

    @FXML
    public void Back(MouseEvent mouseEvent) {
        Controller.primaryStage.close();
        Controller.primaryStage = Controller.tempStage;
        Controller.primaryStage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hostfield.setText(Controller.host);
        portfield.setText(String.valueOf(Controller.port));
    }
}
