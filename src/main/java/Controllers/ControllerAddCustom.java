package Controllers;

import DAO.CustomDAO;
import DAO.CustomerDAO;
import DAO.EmployeeDAO;
import Database.Customer;
import Database.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

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
    public DatePicker DateExe;
    @FXML
    public DatePicker AppAcc;
    @FXML
    public DatePicker AppDir;
    @FXML
    public ComboBox<Employee> Employee;
    @FXML
    public ComboBox<Customer> Customer;
    @FXML
    public TextField price;

    private ObservableList<String> listType = FXCollections.observableArrayList("усне", "письмове", "економічно-правове", "забезпечення", "семінар", "аудит");
    private ObservableList<Employee> cell;

    @FXML
    public void initialize() {
        Employee.setCellFactory(new Callback<ListView<Employee>, ListCell<Employee>>() {

            @Override
            public ListCell<Employee> call(ListView<Employee> p) {

                final ListCell<Employee> cell = new ListCell<Employee>() {

                    @Override
                    protected void updateItem(Employee t, boolean bln) {
                        super.updateItem(t, bln);

                        if (t != null) {
                            setText(t.getId() + ":" + t.getName());
                        } else {
                            setText(null);
                        }
                    }

                };

                return cell;
            }
        });
        Customer.setCellFactory(new Callback<ListView<Customer>, ListCell<Customer>>() {

            @Override
            public ListCell<Customer> call(ListView<Customer> p) {

                final ListCell<Customer> cell = new ListCell<Customer>() {

                    @Override
                    protected void updateItem(Customer t, boolean bln) {
                        super.updateItem(t, bln);

                        if (t != null) {
                            setText(t.getId() + ":" + t.getPerson_customer());
                        } else {
                            setText(null);
                        }
                    }

                };

                return cell;
            }
        });
        typeCustom.setItems(listType);
        try {
            EmployeeDAO employeeDAO = new EmployeeDAO(Controller.connection);
            CustomerDAO customerDAO = new CustomerDAO(Controller.connection);
            ObservableList<Employee> list = FXCollections.observableArrayList(employeeDAO.selectAllNameEmployees());
            ObservableList<Customer> list1 = FXCollections.observableArrayList();
            Employee.setItems(list);
            Customer.setItems(list1);
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
            CustomDAO customDAO = new CustomDAO(Controller.connection);
        } catch (SQLException e) {
            ShowAlert(e);
            e.printStackTrace();
        }
    }
}
