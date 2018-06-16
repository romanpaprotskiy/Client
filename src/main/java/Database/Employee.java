package Database;

import javafx.beans.NamedArg;
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

    @Override
    public String toString(){
        return name.toString();
    }
}