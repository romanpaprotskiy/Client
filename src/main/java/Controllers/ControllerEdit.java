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

    private CustomDAO customDAO;

    @FXML
    public void initialize(){
        try {
            customDAO = new CustomDAO(connection);
        } catch (SQLException e) {
            ShowAlert(e);
            e.printStackTrace();
        }
    }


    @FXML
    public void BackClick(MouseEvent mouseEvent) {
        primaryStage.close();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Main");
        primaryStage.show();
    }

    @FXML
    public void SaveClick(MouseEvent mouseEvent) {
        Date dateExc,appAcc,appDir;
        Double pr;
        if (DateExe.getValue() != null && AppAcc.getValue() != null && AppDir.getValue() != null && price.getText() != null) {
            try {
                CustomDAO customDAO = new CustomDAO(connection);
                dateExc = Date.valueOf(DateExe.getValue());
                appAcc = Date.valueOf(AppAcc.getValue());
                appDir = Date.valueOf(AppDir.getValue());
                pr = Double.valueOf(price.getText());
                customDAO.editCustom(custom.getId(), dateExc, appAcc, appDir, pr);
            } catch (SQLException e) {
                ShowAlert(e);
                e.printStackTrace();
            }
        } else ShowAlert(new NullPointerException("Введіть дані у всі поля"));
    }

    @FXML
    public void updDateExe(MouseEvent mouseEvent) {
        try {
            customDAO.editCustomDateExe(custom.getId(),Date.valueOf(DateExe.getValue()));
        } catch (SQLException e) {
            ShowAlert(e);
            e.printStackTrace();
        }
    }

    @FXML
    public void updAppAcc(MouseEvent mouseEvent) {
        try {
            customDAO.editCustomAppAcc(custom.getId(),Date.valueOf(AppAcc.getValue()));
        } catch (SQLException e) {
            ShowAlert(e);
            e.printStackTrace();
        }
    }

    @FXML
    public void updAppDir(MouseEvent mouseEvent) {
        try {
            customDAO.editCustomAppDir(custom.getId(),Date.valueOf(AppDir.getValue()));
        } catch (SQLException e) {
            ShowAlert(e);
            e.printStackTrace();
        }
    }

    @FXML
    public void updPr(MouseEvent mouseEvent) {
        try {
            customDAO.editCustomPrice(custom.getId(),Double.valueOf(price.getText()));
        } catch (SQLException e) {
            ShowAlert(e);
            e.printStackTrace();
        }
    }
}
