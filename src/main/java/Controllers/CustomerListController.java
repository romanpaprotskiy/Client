package Controllers;

import DAO.CustomerDAO;
import Database.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.sql.SQLException;

public class CustomerListController extends ControllerAddCustom {

    @FXML
    public ImageView add;
    @FXML
    public TableView<Customer> customerListTable;
    @FXML
    public TableColumn<Customer,Integer> idCus;
    @FXML
    public TableColumn<Customer,String> Cus;
    @FXML
    public TableColumn<Customer,String> Rep;
    @FXML
    public TableColumn<Customer,String> Addr;

    public void initialize(){
        idCus.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        Cus.setCellValueFactory(cellData -> cellData.getValue().person_customerProperty());
        Rep.setCellValueFactory(cellData -> cellData.getValue().person_representativeProperty());
        Addr.setCellValueFactory(cellData -> cellData.getValue().addressProperty());
        try {
            CustomerDAO customerDAO = new CustomerDAO(connection);
            ObservableList<Customer> list = FXCollections.observableArrayList();
            list.addAll(customerDAO.selectAllCustomer());
            customerListTable.setItems(list);
        } catch (SQLException e) {
            ShowAlert(e);
            e.printStackTrace();
        }
        customerListTable.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> getCustomerId(newValue)));
    }

    private void getCustomerId(Customer customer) {
        Controller.customer = customerListTable.getSelectionModel().getSelectedItem();
        CustomerText.setText(customerListTable.getSelectionModel().getSelectedItem().getPerson_customer());
    }

}
