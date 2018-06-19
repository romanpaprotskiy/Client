package Controllers;

import DAO.CustomerDAO;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.sql.SQLException;

public class ControllerAddCustomer extends Controller {

    @FXML
    public TextField perCusField;
    @FXML
    public TextField perRepField;
    @FXML
    public TextField addressField;

    @FXML
    public void Add(MouseEvent mouseEvent) {
        String pC,pR,adr;
        try {
            CustomerDAO customerDAO = new CustomerDAO(connection);
            if (perRepField.getText() != null) pC = perCusField.getText();
            else pC = null;
            if (perRepField.getText() != null) pR = perRepField.getText();
            else pR = null;
            if (addressField.getText() != null) adr = addressField.getText();
            else adr = null;
            customerDAO.addCustomer(pC,pR,adr);
        } catch (SQLException e) {
            ShowAlert(e);
            e.printStackTrace();
        }

    }
}
