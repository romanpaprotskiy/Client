package DAO;


import Connection.ConnectionInterface;
import Database.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomerDAO extends DAO {

    private Statement statement;

    public CustomerDAO(ConnectionInterface connect) throws SQLException {
        super(connect);

    }

    public void addCustomer(String perCus,String perRep,String address){

    }

    private Customer getCustomerFromResultSet(ResultSet result) throws SQLException {
        Customer customer = new Customer();
        customer.setId(result.getInt(ID_CUSTOMER));
        customer.setPerson_customer(result.getString(PER_CUS));
        customer.setPerson_representative(result.getString(PER_REP));
        customer.setAddress(result.getString(CUS_ADD));
        return customer;
    }

    private Customer getNameCustomer(ResultSet result) throws SQLException {
        Customer customer = new Customer();
        customer.setId(result.getInt(ID_CUSTOMER));
        customer.setPerson_customer(PER_CUS);
        return customer;
    }

    public ObservableList<Customer> selectAllCustomer() throws SQLException {
        String sql = "SELECT Customer.ID AS idCustomer,person_customer,person_representative,Address " +
                "FROM Customer;";
        statement = getConnection().createStatement();
        ResultSet result = statement.executeQuery(sql);
        ObservableList<Customer> list = FXCollections.observableArrayList();
        while (result.next()) {
            list.add(getCustomerFromResultSet(result));
        }
        statement.close();
        return list;
    }

    public ObservableList<Customer> selectAllNameCustomer() throws SQLException {
        String sql = "SELECT Customer.ID AS idCustomer,person_customer,person_representative,Address " +
                "FROM Customer;";
        statement = getConnection().createStatement();
        ResultSet result = statement.executeQuery(sql);
        ObservableList<Customer> list = FXCollections.observableArrayList();
        while (result.next()) {
            list.add(getNameCustomer(result));
        }
        statement.close();
        return list;
    }
}
