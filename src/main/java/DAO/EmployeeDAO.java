package DAO;

import Connection.*;
import Database.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class EmployeeDAO extends DAO {

    private Statement statement;

    public EmployeeDAO(ConnectionInterface connect) throws SQLException {
        super(connect);
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
}
