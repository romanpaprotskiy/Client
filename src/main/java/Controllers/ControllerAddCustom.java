package Controllers;

import DAO.CustomDAO;
import DAO.CustomerDAO;
import DAO.EmployeeDAO;
import Database.Customer;
import Database.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

public class ControllerAddCustom extends Controller {

    @FXML
    public ImageView Back;
    @FXML
    public ImageView Add;
    @FXML
    public ImageView Calc;
    @FXML
    public ComboBox<String> typeCustom;
    @FXML
    public TextField Quest;
    @FXML
    public DatePicker DateCus;
    @FXML
    public ComboBox<Employee> Employee;
    @FXML
    public Label CustomerText;

    private ObservableList<String> listType = FXCollections.observableArrayList("усне", "письмове", "економічно-правове", "забезпечення", "семінар", "аудит");
    private ObservableList<Employee> cell;
    private int customerId;

    @FXML
    public void initialize() {
        Callback<ListView<Employee>, ListCell<Employee>> factory = lv -> new ListCell<Employee>() {

            @Override
            protected void updateItem(Employee item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getName());
            }

        };
        Employee.setCellFactory(factory);
        Employee.setButtonCell(factory.call(null));
        typeCustom.setItems(listType);
        try {
            EmployeeDAO employeeDAO = new EmployeeDAO(Controller.connection);
            ObservableList<Employee> list = FXCollections.observableArrayList(employeeDAO.selectAllNameEmployees());
            Employee.setItems(list);
        } catch (SQLException e) {
            ShowAlert(e);
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
    public void AddCustomClick(MouseEvent mouseEvent) {
        try {
            if (typeCustom.getValue() != null && Quest.getText() != null && DateCus.getValue() != null && Employee.getValue() != null
                    && Controller.customer != null) {
                CustomDAO customDAO = new CustomDAO(connection);
                String type = typeCustom.getValue();
                int quest = Integer.parseInt(Quest.getText());
                Date dateCus = Date.valueOf(DateCus.getValue());
                int empId = Employee.getValue().getId();
                int cusId = Controller.customer.getId();
                customDAO.addCustom(type, quest, dateCus, empId, cusId);
            } else {
                ShowAlert(new NullPointerException("Введіть всі поля"));
            }
        } catch (SQLException | NullPointerException e) {
            ShowAlert(e);
            e.printStackTrace();
        }
    }

    @FXML
    public void CusClick(MouseEvent mouseEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Layouts/CustomerList.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("SelectCustomer");
            stage.setResizable(false);
            stage.show();
            tempStage = stage;
        } catch (IOException e) {
            ShowAlert(e);
            e.printStackTrace();
        }
    }

    @FXML
    public void CustomMove(MouseEvent mouseEvent) {
        CustomerText.setText(Controller.customer.getPerson_customer());
        customerId = Controller.customer.getId();
    }
}
