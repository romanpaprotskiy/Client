package Controllers;

import DAO.CustomDAO;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.sql.SQLException;


public class ControllerMain {

    @FXML
    public ImageView customers;
    @FXML
    public ImageView employees;
    @FXML
    public ImageView statistics;
    @FXML
    public AnchorPane main;
    @FXML
    public ImageView customs;
    @FXML
    public ImageView exit;
    @FXML
    public ImageView update;
    @FXML
    public ImageView add;
    @FXML
    public ImageView search;
    @FXML
    public ImageView delete;
    @FXML
    public ComboBox typeSearch;
    @FXML
    public ImageView edit;
    @FXML
    public TableColumn cId;
    @FXML
    public TableColumn cType;
    @FXML
    public TableColumn cCQ;
    @FXML
    public TableColumn cDC;
    @FXML
    public TableColumn cDE;
    @FXML
    public TableColumn cAA;
    @FXML
    public TableColumn cAD;

    @FXML
    private void initialize () {
        
    }

    @FXML
    public void SettingsClick(MouseEvent mouseEvent) {

    }

    @FXML
    public void CustomersClick(MouseEvent mouseEvent) {

    }

    @FXML
    public void EmployeesClick(MouseEvent mouseEvent) {

    }

    @FXML
    public void StatisticsClick(MouseEvent mouseEvent) {

    }

    public void CustomsClick(MouseEvent mouseEvent) throws SQLException {

    }
}
