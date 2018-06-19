package DAO;


import Connection.ConnectionInterface;
import Database.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomerDAO extends DAO {

    private Statement statement;

    public CustomerDAO(ConnectionInterface connect) throws SQLException {
        super(connect);

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

    public void addCustomer(String perCus,String perRep,String addr) throws SQLException {
        String sql ="INSERT INTO Customer(person_customer, person_representative, Address) " +
                "VALUES (?,?,?)";
        PreparedStatement statement = getConnection().prepareStatement(sql);
        statement.setNString(1,perCus);
        statement.setNString(2,perRep);
        statement.setNString(3,addr);
        statement.executeUpdate();
        statement.close();
    }

    public void deleteCustomer(int id) throws SQLException {
        String sql = "DELETE FROM Customer WHERE id = ?";
        PreparedStatement statement = getConnection().prepareStatement(sql);
        statement.setInt(1,id);
        statement.executeUpdate();
        statement.close();
    }

    public void updatePerCus(int id,String perCus) throws SQLException {
        String sql = "UPDATE Customer set person_customer = ? where id = ?";
        PreparedStatement statement = getConnection().prepareStatement(sql);
        statement.setNString(1,perCus);
        statement.setInt(2,id);
        statement.executeUpdate();
        statement.close();
    }

    public void updatePerRep(int id,String perRep) throws SQLException {
        String sql = "UPDATE Customer set person_representative = ? where id = ?";
        PreparedStatement statement = getConnection().prepareStatement(sql);
        statement.setNString(1,perRep);
        statement.setInt(2,id);
        statement.executeUpdate();
        statement.close();
    }

    public void updatePerAddress(int id,String address) throws SQLException {
        String sql = "UPDATE Customer set Address = ? where id = ?";
        PreparedStatement statement = getConnection().prepareStatement(sql);
        statement.setNString(1,address);
        statement.setInt(2,id);
        statement.executeUpdate();
        statement.close();
    }

    public void updateCustomer(int id,String perCus,String perRep,String address) throws SQLException {
        String sql = "UPDATE Customer SET person_customer = ?,person_representative = ?,Address = ? where id = ?";
        PreparedStatement statement = getConnection().prepareStatement(sql);
        statement.setNString(1,perCus);
        statement.setNString(2,perRep);
        statement.setNString(3,address);
        statement.setInt(4,id);
        statement.executeUpdate();
        statement.close();
    }
}
