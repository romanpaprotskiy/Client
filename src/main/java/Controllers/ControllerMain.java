package Controllers;

import DAO.CustomDAO;
import Database.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.sql.Date;
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
    public TableColumn<Custom, Integer> cId;
    @FXML
    public TableColumn<Custom, String> cType;
    @FXML
    public TableColumn<Custom, Integer> cCQ;
    @FXML
    public TableColumn<Custom, Date> cDC;
    @FXML
    public TableColumn<Custom, Date> cDE;
    @FXML
    public TableColumn<Custom, Date> cAA;
    @FXML
    public TableColumn<Custom, Date> cAD;
    @FXML
    public TableView<Custom> customTable;
    @FXML
    private Label CusLabel;
    @FXML
    private Label RepLabel;
    @FXML
    private Label EmplLabel;
    @FXML
    private Label PosLabel;

    @FXML
    private void initialize() {
        cId.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        cType.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        cCQ.setCellValueFactory(cellData -> cellData.getValue().countOfQuestionsProperty().asObject());
        cDC.setCellValueFactory(cellData -> cellData.getValue().date_customProperty());
        cDE.setCellValueFactory(cellData -> cellData.getValue().dateOfExecutionProperty());
        cAA.setCellValueFactory(cellData -> cellData.getValue().approvedAccoutantProperty());
        cAD.setCellValueFactory(cellData -> cellData.getValue().approvedDirectionProperty());
        customTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> getEmployeeClick(newValue));
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

    @FXML
    public void Update(MouseEvent mouseEvent) {
        try {
            CustomDAO customDAO = new CustomDAO(Controller.connection);
            ObservableList<Custom> list = FXCollections.observableArrayList();
            list.addAll(customDAO.selectAllCustom());
            customTable.setItems(list);
        } catch (SQLException e) {
            ShowAlert(e);
        }
    }


    private void ShowAlert(Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Error in " + e.getMessage());
        alert.setContentText("Error" + e);
        alert.showAndWait();
    }

    public void getEmployeeClick(Custom custom) {
        CusLabel.setText(custom.getCustomer().getPerson_customer());
        RepLabel.setText(custom.getCustomer().getPerson_representative());
        EmplLabel.setText(custom.getEmployee().getName());
        PosLabel.setText(custom.getEmployee().getPosition());
    }

    @FXML
    public void ExitApp(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("Підтвердіть дію");
        alert.setContentText("Ви дійсно хочете вийти?");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK) Controller.primaryStage.close();
    }

    @FXML
    public void DeleteCustom(MouseEvent mouseEvent) {
        int indexDB = customTable.getSelectionModel().getSelectedItem().getId();
        int index = customTable.getSelectionModel().getSelectedIndex();
        try {
            CustomDAO customDAO = new CustomDAO(Controller.connection);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "DeleteCustom", ButtonType.YES, ButtonType.NO);
            alert.setTitle("Delete");
            alert.setHeaderText("Підтвердіть дію");
            alert.setContentText("Ви дійсно хочете видалити?" + "\n" + customTable.getSelectionModel().getSelectedItem().getId() +
                    " " + customTable.getSelectionModel().getSelectedItem().getType());
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                customDAO.DeleteCustom(indexDB);
                customTable.getItems().remove(index);
            }
        } catch (SQLException e) {
            ShowAlert(e);
        }
    }
}