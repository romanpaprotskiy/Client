package DAO;

import Database.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;
import Connection.*;

public class CustomDAO extends DAO {

    private Statement statement;

    public CustomDAO(ConnectionInterface connect) throws SQLException {
        super(connect);
    }

    private Custom getCustomFromResultSet(ResultSet result) throws SQLException {
        Custom custom = new Custom();
        Customer customer = new Customer();
        Employee employee = new Employee();
        employee.setId(result.getInt(ID_EMPLOYEE));
        employee.setName(result.getString(EMPL_NAME));
        employee.setPosition(result.getString(EMPL_POS));
        customer.setId(result.getInt(ID_CUSTOMER));
        customer.setPerson_customer(result.getString(PER_CUS));
        customer.setPerson_representative(result.getString(PER_REP));
        custom.setId(result.getInt(ID_CUSTOM));
        custom.setType(result.getString(TYPE));
        custom.setCustomer(customer);
        custom.setEmployee(employee);
        custom.setCountOfQuestions(result.getInt(COUNT_OF_QUEST));
        custom.setDate_custom(result.getDate(DATE_CUSTOM));
        custom.setDateOfExecution(result.getDate(DATE_EXC));
        custom.setApprovedAccoutant(result.getDate(APP_ACC));
        custom.setApprovedDirection(result.getDate(APP_DIR));
        custom.setPrice(result.getDouble(PRICE));
        return custom;
    }

    //return all fields from the table "Custom"
    public ObservableList<Custom> selectAllCustom() throws SQLException {
        statement = getConnection().createStatement();
        ObservableList<Custom> list = FXCollections.observableArrayList();
        String sql = "SELECT id_custom,Custom.Type,CountOfQuestions,date_custom,dateOfExecution,ApprovedAccoutant,ApprovedDirector, " +
                "  Employees.id AS idEmployee,Employees.Name,Employees.Position,Customer.id AS idCustomer," +
                " Customer.person_customer,Customer.person_representative,Customer.Address " +
                "FROM Custom " +
                "LEFT OUTER JOIN Employees ON Custom.Employee = Employees.id " +
                "LEFT OUTER JOIN Customer  ON Custom.Customer = Customer.id;";
        ResultSet result = statement.executeQuery(sql);
        while (result.next()){
            list.add(getCustomFromResultSet(result));
        }
        return list;
    }
}