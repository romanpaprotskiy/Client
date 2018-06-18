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
        employee.setBirthday(result.getDate(EMPL_BIRTHDAY));
        employee.setPosition(result.getString(EMPL_POS));
        employee.setDate(result.getDate(EMPL_DATE));
        employee.setSalary(result.getDouble(EMPL_SALARY));
        department.setID(result.getInt(ID_DEPARTMENT));
        department.setName(result.getString(NAME_DEP));
        employee.setDepartment(department);
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
                "FROM Employees " +
                "LEFT OUTER JOIN Department ON Employees.Department = Department.ID";
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

    public void addEmployee (String name,Date birthday,Date date,String position,Double salary,int idDep) throws SQLException {
        String sql = "INSERT INTO Employees(Name, BirthDay, Date, Salary, Position, Department) " +
                "VALUES(?,?,?,?,?,?)";
        PreparedStatement statement = getConnection().prepareStatement(sql);
        statement.setNString(1,name);
        statement.setDate(2,birthday);
        statement.setDate(3,date);
        statement.setDouble(4,salary);
        statement.setNString(5,position);
        statement.setInt(6,idDep);
        statement.executeUpdate();
        statement.close();
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

    public void EditEmployeeName(int id,String name) throws SQLException{
        String sql = "UPDATE Employees SET Name = ? WHERE id = ?";
        PreparedStatement statement = getConnection().prepareStatement(sql);
        statement.setNString(1,name);
        statement.setInt(2,id);
        statement.executeUpdate();
        statement.close();
    }

    public void EditEmployeeBirthday(int id,Date date) throws SQLException{
        String sql = "UPDATE Employees SET BirthDay = ? WHERE id = ?";
        PreparedStatement statement = getConnection().prepareStatement(sql);
        statement.setDate(1,date);
        statement.setInt(2,id);
        statement.executeUpdate();
        statement.close();
    }

    public void EditEmployeeDate(int id,Date date) throws SQLException{
        String sql = "UPDATE Employees SET Date = ? WHERE id = ?";
        PreparedStatement statement = getConnection().prepareStatement(sql);
        statement.setDate(1,date);
        statement.setInt(2,id);
        statement.executeUpdate();
        statement.close();
    }

    public void EditEmployeePosition(int id,String pos) throws SQLException{
        String sql = "UPDATE Employees SET Position = ? WHERE id = ?";
        PreparedStatement statement = getConnection().prepareStatement(sql);
        statement.setNString(1,pos);
        statement.setInt(2,id);
        statement.executeUpdate();
        statement.close();
    }

    public void EditEmployeeSalary(int id,Double salary) throws SQLException{
        String sql = "UPDATE Employees SET Salary = ? WHERE id = ?";
        PreparedStatement statement = getConnection().prepareStatement(sql);
        statement.setDouble(1,salary);
        statement.setInt(2,id);
        statement.executeUpdate();
        statement.close();
    }

    public void EditEmployeeDepartment(int id,int dep) throws SQLException{
        String sql = "UPDATE Employees SET Department = ? WHERE id = ?";
        PreparedStatement statement = getConnection().prepareStatement(sql);
        statement.setInt(1,dep);
        statement.setInt(2,id);
        statement.executeUpdate();
        statement.close();
    }

}
