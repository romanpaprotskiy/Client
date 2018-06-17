package DAO;

import Connection.*;
import Database.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class EmployeeDAO extends DAO {

    private Statement statement;

    public EmployeeDAO(ConnectionInterface connect) throws SQLException {
        super(connect);
    }

    private Employee getEmployeesFromResultSet(ResultSet result) throws SQLException {
        Employee employee = new Employee();
        Department department = new Department();
        employee.setId(result.getInt(ID_EMPLOYEE));
        employee.setName(result.getString(EMPL_NAME));
        if (result.getDate(EMPL_BIRTHDAY) != null) employee.setBirthday(result.getDate(EMPL_BIRTHDAY));
        employee.setPosition(result.getString(EMPL_POS));
        employee.setDate(result.getDate(EMPL_DATE));
        employee.setSalary(result.getDouble(EMPL_SALARY));
        if (result.getInt(ID_DEPARTMENT) != 0) department.setID(result.getInt(ID_DEPARTMENT));
        if (result.getString(NAME_DEP) != null) department.setName(result.getString(NAME_DEP));
        if ((result.getInt(ID_DEPARTMENT) != 0) && (result.getString(NAME_DEP) != null)) employee.setDepartment(department);
        return employee;
    }

    private Employee getNameEmployee(ResultSet result) throws SQLException {
        Employee employee = new Employee();
        employee.setId(result.getInt(ID_EMPLOYEE));
        employee.setName(result.getString(EMPL_NAME));
        return employee;
    }

    public Employee searchEmployee(int EmpId) throws SQLException {
        String sql = "SELECT Employees.id,Employees.Name,Employees.Position " +
                "FROM Employees WHERE id = " + EmpId;
        statement = getConnection().createStatement();
        ResultSet result = statement.executeQuery(sql);
        Employee employee = new Employee();
        if (result.next()){
            employee.setId(result.getInt(ID_EMPLOYEE));
            employee.setName(result.getString(EMPL_NAME));
            employee.setPosition(result.getString(EMPL_POS));
        }
        statement.close();
        return employee;
    }

    public ObservableList<Employee> selectAllEmployees() throws SQLException {
        String sql = "SELECT Employees.id AS idEmployee,Name,BirthDay,Date,Salary,Position,Department.ID AS idDep,NameDep " +
                "FROM Employees,Department " +
                "WHERE Employees.Department = Department.ID;";
        statement = getConnection().createStatement();
        ResultSet result = statement.executeQuery(sql);
        ObservableList<Employee> list = FXCollections.observableArrayList();
        while (result.next()){
            list.add(getEmployeesFromResultSet(result));
        }
        statement.close();
        return list;
    }

    public ObservableList<Employee> selectAllNameEmployees() throws SQLException {
        String sql = "SELECT Employees.id AS idEmployee,Name FROM Employees;";
        statement = getConnection().createStatement();
        ResultSet result = statement.executeQuery(sql);
        ObservableList<Employee> list = FXCollections.observableArrayList();
        while (result.next()){
            list.add(getNameEmployee(result));
        }
        statement.close();
        return list;
    }

    public void addEmployee (){
        String sql = "";
    }

    public void DeleteEmployee(int index) throws SQLException {
        statement = getConnection().createStatement();
        String sql = "DELETE FROM Employees WHERE Employees.id = " + index;
        statement.executeUpdate(sql);
        statement.close();
    }

    public void addDepartment(String str) throws SQLException {
        String sql = "INSERT INTO Department(NameDep) VALUES (?)";
        PreparedStatement statement = getConnection().prepareStatement(sql);
        statement.setNString(1,str);
        statement.executeUpdate();
        statement.close();
    }

    public ObservableList<Department> selectAllDepartment() throws SQLException {
        String sql = "SELECT ID AS idDep,NameDep FROM Department";
        statement = getConnection().createStatement();
        ResultSet result = statement.executeQuery(sql);
        ObservableList<Department> list = FXCollections.observableArrayList();
        while (result.next()) {
            Department department = new Department();
            department.setID(result.getInt(ID_DEPARTMENT));
            department.setName(result.getString(NAME_DEP));
            list.add(department);
        }
        statement.close();
        return list;
    }

    public void deletedepartment(int id) throws SQLException {
        String sql = "DELETE FROM Department WHERE Department.ID = " + id + ";";
        statement = getConnection().createStatement();
        statement.executeUpdate(sql);
        statement.close();
    }

}
