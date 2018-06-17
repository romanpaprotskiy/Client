package Controllers;

import DAO.CustomDAO;
import Database.Custom;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

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

    @FXML
    public void SaveClick(MouseEvent mouseEvent) {
        try {
            CustomDAO customDAO = new CustomDAO(connection);
            Date dateExc = Date.valueOf(DateExe.getValue());
            Date appAcc = Date.valueOf(AppAcc.getValue());
            Date appDir = Date.valueOf(AppDir.getValue());
            Double pr = Double.valueOf(price.getText());
            customDAO.editCustom(custom.getId(),dateExc,appAcc,appDir,pr);
        } catch (SQLException e) {
            ShowAlert(e);
            e.printStackTrace();
        }
    }
}
