package Controllers;

import DAO.CustomDAO;
import Database.Custom;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;


public class ControllerMain extends Controller{

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
    public ComboBox<String> typeSearch;
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
    public TableColumn<Custom,Double> price;
    @FXML
    public TextField searchField;
    @FXML
    private Label CusLabel;
    @FXML
    private Label RepLabel;
    @FXML
    private Label EmplLabel;
    @FXML
    private Label PosLabel;

    private CustomDAO customDAO;

    @FXML
    private void initialize() {
        cId.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        cType.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        cCQ.setCellValueFactory(cellData -> cellData.getValue().countOfQuestionsProperty().asObject());
        cDC.setCellValueFactory(cellData -> cellData.getValue().date_customProperty());
        cDE.setCellValueFactory(cellData -> cellData.getValue().dateOfExecutionProperty());
        cAA.setCellValueFactory(cellData -> cellData.getValue().approvedAccountantProperty());
        cAD.setCellValueFactory(cellData -> cellData.getValue().approvedDirectionProperty());
        price.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
        customTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> getEmployeeClick(newValue));
        try {
            customDAO = new CustomDAO(connection);
            ObservableList<Custom> list = FXCollections.observableArrayList();
            list.addAll(customDAO.selectAllCustom());
            customTable.setItems(list);
        } catch (SQLException e) {
            ShowAlert(e);
            e.printStackTrace();
        }
        ObservableList<String> list = FXCollections.observableArrayList("ID", "Тип");
        typeSearch.setItems(list);
    }

    @FXML
    public void Update(MouseEvent mouseEvent) {
        try {
            customTable.getItems().removeAll();
            ObservableList<Custom> list = FXCollections.observableArrayList();
            list.addAll(customDAO.selectAllCustom());
            customTable.setItems(list);
        } catch (SQLException e) {
            ShowAlert(e);
        }
    }

    private void getEmployeeClick(Custom custom) {
        CusLabel.setText(custom.getCustomer().getPerson_customer());
        RepLabel.setText(custom.getCustomer().getPerson_representative());
        EmplLabel.setText(custom.getEmployee().getName());
        PosLabel.setText(custom.getEmployee().getPosition());
    }


    @FXML
    public void ExitApp(MouseEvent mouseEvent) {
        ExitWin();
    }

    @FXML
    public void DeleteCustom(MouseEvent mouseEvent) {
        int indexDB = customTable.getSelectionModel().getSelectedItem().getId();
        int index = customTable.getSelectionModel().getSelectedIndex();
        try {
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

    @FXML
    public void addCustom(MouseEvent mouseEvent) {
        scene = primaryStage.getScene();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/Layouts/AddCustom.fxml"));
        } catch (IOException e) {
            ShowAlert(e);
        }
        primaryStage.setTitle("AddCustom");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    @FXML
    public void EditClick(MouseEvent mouseEvent) {
        scene = primaryStage.getScene();
        Controller.custom = customTable.getSelectionModel().getSelectedItem();
        if (Controller.custom == null) ShowAlert(new NullPointerException("Виберіть елемент для редагування"));
        else {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/Layouts/EditWindow.fxml"));
                primaryStage.setTitle("Edit");
                primaryStage.setScene(new Scene(root));
                primaryStage.setResizable(false);
                primaryStage.show();
            } catch (IOException e) {
                ShowAlert(e);
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void EmployeeClick(MouseEvent mouseEvent) {
        scene1 = primaryStage.getScene();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Layouts/Employee.fxml"));
            primaryStage.setTitle("Main");
            primaryStage.setScene(new Scene(root));
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (IOException e) {
            ShowAlert(e);
            e.printStackTrace();
        }
    }

    @FXML
    public void Search(MouseEvent mouseEvent) {
        try {
            switch (typeSearch.getValue()) {
                case "ID":
                    ObservableList<Custom> list = FXCollections.observableArrayList();
                    list.addAll(customDAO.searchID(Integer.parseInt(searchField.getText())));
                    customTable.setItems(list);
                    break;
                case "Тип":
                    ObservableList<Custom> list1 = FXCollections.observableArrayList();
                    list1.addAll(customDAO.searchType(searchField.getText()));
                    customTable.setItems(list1);
                    break;
            }
        } catch (SQLException e) {
            ShowAlert(e);
            e.printStackTrace();
        }
    }
}