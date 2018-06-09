package Database;

import javafx.beans.property.*;

import java.io.*;
import java.sql.*;

public class Custom implements Serializable {

    private IntegerProperty id;
    private SimpleObjectProperty<Customer> customer;
    private StringProperty type;
    private IntegerProperty CountOfQuestions;
    private SimpleObjectProperty<Employee> employee;
    private SimpleObjectProperty<Date> date_custom, dateOfExecution, ApprovedDirection, ApprovedAccoutant;
    private DoubleProperty price;

    public Custom(){
        this.id = new SimpleIntegerProperty();
        this.customer = new SimpleObjectProperty<Customer>();
        this.type = new SimpleStringProperty();
        this.CountOfQuestions = new SimpleIntegerProperty();
        this.employee = new SimpleObjectProperty<Employee>();
        this.date_custom = new SimpleObjectProperty<Date>();
        this.dateOfExecution = new SimpleObjectProperty<Date>();
        this.ApprovedDirection = new SimpleObjectProperty<Date>();
        this.ApprovedAccoutant = new SimpleObjectProperty<Date>();
        this.price = new SimpleDoubleProperty();
    }

    public Custom(int id, Customer customer, String typeCustom, int countOfQuestions, Employee employee,
                  Date date_custom, Date dateOfExecution, Date ApprovedDirection, Date ApprovedAccoutant) {
        this.id = new SimpleIntegerProperty(id);
        this.customer = new SimpleObjectProperty<Customer>(customer);
        this.type = new SimpleStringProperty(typeCustom);
        this.CountOfQuestions = new SimpleIntegerProperty(countOfQuestions);
        this.employee = new SimpleObjectProperty<Employee>(employee);
        this.date_custom = new SimpleObjectProperty<Date>(date_custom);
        this.dateOfExecution = new SimpleObjectProperty<Date>(dateOfExecution);
        this.ApprovedDirection = new SimpleObjectProperty<Date>(ApprovedAccoutant);
        this.ApprovedAccoutant = new SimpleObjectProperty<Date>(ApprovedDirection);
    }
    public Custom(int id, Customer customer, String typeCustom, int countOfQuestions, Employee employee,
                  Date date_custom, Date dateOfExecution, Date ApprovedAccoutant, Date ApprovedDirection, double price) {
        this.id = new SimpleIntegerProperty(id);
        this.customer = new SimpleObjectProperty<Customer>(customer);
        this.type = new SimpleStringProperty(typeCustom);
        this.CountOfQuestions = new SimpleIntegerProperty(countOfQuestions);
        this.employee = new SimpleObjectProperty<Employee>(employee);
        this.date_custom = new SimpleObjectProperty<Date>(date_custom);
        this.dateOfExecution = new SimpleObjectProperty<Date>(dateOfExecution);
        this.ApprovedDirection = new SimpleObjectProperty<Date>(ApprovedAccoutant);
        this.ApprovedAccoutant = new SimpleObjectProperty<Date>(ApprovedDirection);
        this.price = new SimpleDoubleProperty(price);
    }

    public Custom(Customer customer, String typeCustom, int countOfQuestions, Employee employee) {
        this.customer = new SimpleObjectProperty<Customer>(customer);
        this.type = new SimpleStringProperty(typeCustom);
        this.CountOfQuestions = new SimpleIntegerProperty(countOfQuestions);
        this.employee = new SimpleObjectProperty<Employee>(employee);
    }

    public Custom(Customer customer, String typeCustom, int countOfQuestions) {
        this.customer = new SimpleObjectProperty<Customer>(customer);
        this.type = new SimpleStringProperty(typeCustom);
        this.CountOfQuestions = new SimpleIntegerProperty(countOfQuestions);
    }


    public double getPrice() {
        return price.get();
    }

    public DoubleProperty priceProperty() {
        return price;
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public Employee getEmployee() {
        return employee.get();
    }

    public SimpleObjectProperty<Employee> employeeProperty() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee.set(employee);
    }

    public String getType() {
        return type.get();
    }

    public StringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public int getCountOfQuestions() {
        return CountOfQuestions.get();
    }

    public IntegerProperty countOfQuestionsProperty() {
        return CountOfQuestions;
    }

    public void setCountOfQuestions(int countOfQuestions) {
        this.CountOfQuestions.set(countOfQuestions);
    }

    public Customer getCustomer() {
        return customer.get();
    }

    public SimpleObjectProperty<Customer> customerProperty() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer.set(customer);
    }

    public Date getDate_custom() {
        return date_custom.get();
    }

    public SimpleObjectProperty<Date> date_customProperty() {
        return date_custom;
    }

    public void setDate_custom(Date date_custom) {
        this.date_custom.set(date_custom);
    }

    public Date getDateOfExecution() {
        return dateOfExecution.get();
    }

    public SimpleObjectProperty<Date> dateOfExecutionProperty() {
        return dateOfExecution;
    }

    public void setDateOfExecution(Date dateOfExecution) {
        this.dateOfExecution.set(dateOfExecution);
    }

    public Date getApprovedDirection() {
        return ApprovedDirection.get();
    }

    public SimpleObjectProperty<Date> approvedDirectionProperty() {
        return ApprovedDirection;
    }

    public void setApprovedDirection(Date approvedDirection) {
        this.ApprovedDirection.set(approvedDirection);
    }

    public Date getApprovedAccoutant() {
        return ApprovedAccoutant.get();
    }

    public SimpleObjectProperty<Date> approvedAccoutantProperty() {
        return ApprovedAccoutant;
    }

    public void setApprovedAccoutant(Date approvedAccoutant) {
        this.ApprovedAccoutant.set(approvedAccoutant);
    }
}