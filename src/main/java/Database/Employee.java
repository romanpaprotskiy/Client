package Database;

import java.io.Serializable;
import java.sql.Date;

public class Employee implements Serializable {

    private int id;
    private String name;
    private Date birthday, date;
    private double salary;
    private Department department;
    private String position;

    public Employee(int id, String name, Date birthday, Date date, double salary, Department department, String position) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.date = date;
        this.salary = salary;
        this.department = department;
        this.position = position;
    }

    public Employee(int id, String name, Department department, String position) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.position = position;
    }

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }


    @Override
    public String toString() {
        return name + " " + birthday.toString() + " " + date.toString() + " " + salary + " " + department.toString() + " " + position.toString();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public Date getDate() {
        return date;
    }

    public double getSalary() {
        return salary;
    }

    public Department getDepartment() {
        return department;
    }



}