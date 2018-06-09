package Database;

import javafx.beans.property.*;

import java.io.Serializable;
import java.sql.Date;

public class Employee implements Serializable {

    private IntegerProperty id;
    private StringProperty name;
    private SimpleObjectProperty<Date> birthday, date;
    private DoubleProperty salary;
    private SimpleObjectProperty<Department> department;
    private StringProperty position;

    public Employee(){
        this.id = new SimpleIntegerProperty();
        this.name = new SimpleStringProperty();
        this.birthday = new SimpleObjectProperty<Date>();
        this.date = new SimpleObjectProperty<Date>();
        this.salary = new SimpleDoubleProperty();
        this.department = new SimpleObjectProperty<Department>();
        this.position = new SimpleStringProperty();
    }

    public Employee(int id, String name, Date birthday, Date date, double salary, Department department, String position) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.birthday = new SimpleObjectProperty<Date>(birthday);
        this.date = new SimpleObjectProperty<Date>(date);
        this.salary = new SimpleDoubleProperty(salary);
        this.department = new SimpleObjectProperty<Department>(department);
        this.position = new SimpleStringProperty(position);
    }

    public Employee(int id, String name, Department department, String position) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.department = new SimpleObjectProperty<Department>(department);
        this.position = new SimpleStringProperty(position);
    }

    public Employee(String name, double salary) {
        this.name = new SimpleStringProperty(name);
        this.salary = new SimpleDoubleProperty(salary);
    }

    public Employee(int id,String name,String position) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.position = new SimpleStringProperty(position);
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

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public Date getBirthday() {
        return birthday.get();
    }

    public SimpleObjectProperty<Date> birthdayProperty() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday.set(birthday);
    }

    public Date getDate() {
        return date.get();
    }

    public SimpleObjectProperty<Date> dateProperty() {
        return date;
    }

    public void setDate(Date date) {
        this.date.set(date);
    }

    public double getSalary() {
        return salary.get();
    }

    public DoubleProperty salaryProperty() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary.set(salary);
    }

    public Department getDepartment() {
        return department.get();
    }

    public SimpleObjectProperty<Department> departmentProperty() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department.set(department);
    }

    public String getPosition() {
        return position.get();
    }

    public StringProperty positionProperty() {
        return position;
    }

    public void setPosition(String position) {
        this.position.set(position);
    }
}