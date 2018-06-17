package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ControllerEdit extends Controller {

    @FXML
    public DatePicker DateExe;
    @FXML
    public DatePicker AppAcc;
    @FXML
    public DatePicker AppDir;
    @FXML
    public TextField price;
    @FXML
    public ImageView Back;
    @FXML
    public ImageView Save;

    @FXML
    public void BackClick(MouseEvent mouseEvent) {
        primaryStage.close();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Main");
        primaryStage.show();
    }
}
