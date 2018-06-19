package Controllers;

import DAO.CustomerDAO;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.sql.SQLException;

public class ControllerEditCustomer extends Controller {

    @FXML
    public TextField perCusField;
    @FXML
    public TextField perRepField;
    @FXML
    public TextField addressField;

    @FXML
    public void editPerCus(MouseEvent mouseEvent) {
        try {
            CustomerDAO customerDAO = new CustomerDAO(connection);
            customerDAO.updatePerCus(Controller.customer.getId(),perCusField.getText());
        } catch (SQLException e) {
            ShowAlert(e);
            e.printStackTrace();
        }
    }

    @FXML
    public void editPerRep(MouseEvent mouseEvent) {
        try {
            CustomerDAO customerDAO = new CustomerDAO(connection);
            customerDAO.updatePerRep(Controller.customer1.getId(),perRepField.getText());
        } catch (SQLException e) {
            ShowAlert(e);
            e.printStackTrace();
        }
    }

    @FXML
    public void editAddress(MouseEvent mouseEvent) {
        try {
            CustomerDAO customerDAO = new CustomerDAO(connection);
            customerDAO.updatePerAddress(Controller.customer1.getId(),addressField.getText());
        } catch (SQLException e) {
            ShowAlert(e);
            e.printStackTrace();
        }
    }

    @FXML
    public void Edit(MouseEvent mouseEvent) {
        CustomerDAO customerDAO = null;
        String pC = null,pR = null,adr = null;
        try {
            customerDAO = new CustomerDAO(connection);
            if (perRepField.getText() != null) pC = perCusField.getText();
            if (perRepField.getText() != null) pR = perRepField.getText();
            if (addressField.getText() != null) adr = addressField.getText();
            customerDAO.updateCustomer(Controller.customer1.getId(),pC,pR,adr);
        } catch (SQLException e) {
            ShowAlert(e);
            e.printStackTrace();
        }

    }
}
