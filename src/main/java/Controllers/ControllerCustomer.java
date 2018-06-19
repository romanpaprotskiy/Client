package Controllers;

import DAO.*;
import Database.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

public class ControllerCustomer extends Controller {


    @FXML
    public TableView<Customer> customerTable;
    @FXML
    public TableColumn<Customer, Integer> cId;
    @FXML
    public TableColumn<Customer, String> cCus;
    @FXML
    public TableColumn<Customer, String> CRep;
    @FXML
    public TableColumn<Customer, String> Address;
    @FXML
    public TableView<Custom> CustomTable;
    @FXML
    public TableColumn<Custom, Integer> idCus;
    @FXML
    public TableColumn<Custom, String> typeCus;
    @FXML
    public TableColumn<Custom, Date> dateCus;
    @FXML
    public TableColumn<Custom, Date> dateExe;

    private CustomerDAO customerDAO;
    private CustomDAO customDAO;

    public void initialize() {
        cId.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        cCus.setCellValueFactory(cellData -> cellData.getValue().person_customerProperty());
        CRep.setCellValueFactory(cellData -> cellData.getValue().person_representativeProperty());
        Address.setCellValueFactory(cellData -> cellData.getValue().addressProperty());
        idCus.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        typeCus.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        dateCus.setCellValueFactory(cellData -> cellData.getValue().date_customProperty());
        dateExe.setCellValueFactory(cellData -> cellData.getValue().dateOfExecutionProperty());
        customerTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> getCustom(newValue));
        ObservableList<Customer> list = FXCollections.observableArrayList();
        try {
            customerDAO = new CustomerDAO(connection);
            customDAO = new CustomDAO(connection);
            list.addAll(customerDAO.selectAllCustomer());
            customerTable.setItems(list);
        } catch (SQLException e) {
            ShowAlert(e);
            e.printStackTrace();
        }
    }

    private void getCustom(Customer customer) {
        CustomTable.getItems().removeAll();
        int index = customerTable.getSelectionModel().getSelectedItem().getId();
        ObservableList<Custom> list = FXCollections.observableArrayList();
        try {
            list.addAll(customDAO.selectAllCustomForCustomerTable(index));
            CustomTable.setItems(list);
        } catch (SQLException e) {
            ShowAlert(e);
            e.printStackTrace();
        }
    }

    @FXML
    public void EmployeeClick(MouseEvent mouseEvent) {
        primaryStage.setTitle("Main");
        primaryStage.setScene(scene2);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    @FXML
    public void ExitApp(MouseEvent mouseEvent) {
        ExitWin();
    }


    @FXML
    public void CustomClick(MouseEvent mouseEvent) {
        primaryStage.setTitle("Main");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene1);
        primaryStage.show();
    }

    @FXML
    public void AddClick(MouseEvent mouseEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Layouts/AddCustomer.fxml"));
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle("Add");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            ShowAlert(e);
            e.printStackTrace();
        }
    }

    @FXML
    public void update(MouseEvent mouseEvent) {
        customerTable.getItems().removeAll();
        try {
            ObservableList<Customer> list = FXCollections.observableArrayList();
            list.addAll(customerDAO.selectAllCustomer());
            customerTable.setItems(list);
        } catch (SQLException e) {
            ShowAlert(e);
            e.printStackTrace();
        }

    }

    @FXML
    public void delete(MouseEvent mouseEvent) {
        int indexDB = customerTable.getSelectionModel().getSelectedItem().getId();
        int index = customerTable.getSelectionModel().getSelectedIndex();
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "DeleteCustomer", ButtonType.YES, ButtonType.NO);
            alert.setTitle("Delete");
            alert.setHeaderText("Підтвердіть дію");
            alert.setContentText("Ви дійсно хочете видалити?" + "\n" + customerTable.getSelectionModel().getSelectedItem().getId() +
                    " " + customerTable.getSelectionModel().getSelectedItem().getPerson_customer());
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                customerDAO.deleteCustomer(indexDB);
                customerTable.getItems().remove(index);
            }
        } catch (SQLException e) {
            ShowAlert(e);
        }
    }

    public void EditClick(MouseEvent mouseEvent) {
        Controller.customer1 = customerTable.getSelectionModel().getSelectedItem();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Layouts/EditCustomer.fxml"));
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle("Edit");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            ShowAlert(e);
            e.printStackTrace();
        }
    }
}
