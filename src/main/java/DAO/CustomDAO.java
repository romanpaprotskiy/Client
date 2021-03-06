package DAO;

import Database.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.Year;

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
        custom.setApprovedAccountant(result.getDate(APP_ACC));
        custom.setApprovedDirection(result.getDate(APP_DIR));
        custom.setPrice(result.getDouble(PRICE));
        return custom;
    }

    private Custom getCustomEmployeeFromResultSet(ResultSet result) throws SQLException {
        Custom custom = new Custom();
        custom.setId(result.getInt(ID_CUSTOM));
        custom.setType(result.getString(TYPE));
        custom.setDate_custom(result.getDate(DATE_CUSTOM));
        custom.setDateOfExecution(result.getDate(DATE_EXC));
        return custom;
    }

    private Custom getCustomForEditFromResultSet(ResultSet result) throws SQLException {
        Custom custom = new Custom();
        custom.setDateOfExecution(result.getDate(DATE_EXC));
        custom.setApprovedAccountant(result.getDate(APP_ACC));
        custom.setApprovedDirection(result.getDate(APP_DIR));
        custom.setPrice(result.getDouble(PRICE));
        return custom;
    }


    //return all fields from the table "Custom"
    public ObservableList<Custom> selectAllCustom() throws SQLException {
        statement = getConnection().createStatement();
        ObservableList<Custom> list = FXCollections.observableArrayList();
        String sql = "SELECT id_custom,Custom.Type,CountOfQuestions,date_custom,dateOfExecution,ApprovedAccoutant,ApprovedDirector,Price, " +
                "  Employees.id AS idEmployee,Employees.Name,Employees.Position,Customer.id AS idCustomer," +
                " Customer.person_customer,Customer.person_representative,Customer.Address " +
                "FROM Custom " +
                "LEFT OUTER JOIN Employees ON Custom.Employee = Employees.id " +
                "LEFT OUTER JOIN Customer  ON Custom.Customer = Customer.id;";
        ResultSet result = statement.executeQuery(sql);
        while (result.next()){
            list.add(getCustomFromResultSet(result));
        }
        statement.close();
        return list;
    }

    public ObservableList<Custom> selectAllCustomForCustomerTable(int id) throws SQLException {
        ObservableList<Custom> list = FXCollections.observableArrayList();
        String sql = "SELECT id_custom,Custom.Type,CountOfQuestions,date_custom,dateOfExecution, " +
                "Customer.id AS idCustomer," +
                " Customer.person_customer,Customer.person_representative,Customer.Address " +
                "FROM Custom " +
                "LEFT OUTER JOIN Customer  ON Custom.Customer = Customer.id WHERE Customer = ?;";
        PreparedStatement statement = getConnection().prepareStatement(sql);
        statement.setInt(1,id);
        ResultSet result = statement.executeQuery();
        while (result.next()){
            Custom custom = new Custom();
            Customer customer = new Customer();
            customer.setId(result.getInt(ID_CUSTOMER));
            customer.setPerson_customer(result.getString(PER_CUS));
            customer.setPerson_representative(result.getString(PER_REP));
            custom.setId(result.getInt(ID_CUSTOM));
            custom.setType(result.getString(TYPE));
            custom.setCustomer(customer);
            custom.setCountOfQuestions(result.getInt(COUNT_OF_QUEST));
            custom.setDate_custom(result.getDate(DATE_CUSTOM));
            custom.setDateOfExecution(result.getDate(DATE_EXC));
            list.add(custom);
        }
        statement.close();
        return list;
    }

    public void DeleteCustom(int index) throws SQLException {
        statement = getConnection().createStatement();
        String sql = "DELETE FROM Custom WHERE id_custom = " + index;
        statement.executeUpdate(sql);
        statement.close();
    }

    public void addCustom(String type,int count,Date date,int employee,int customer) throws SQLException {
        String sql = "INSERT INTO Custom(Type, CountOfQuestions, date_custom, Employee, Customer)" +
                "VALUES (?,?,?,?,?);";
        PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
        preparedStatement.setNString(1,type);
        preparedStatement.setInt(2,count);
        preparedStatement.setDate(3,date);
        preparedStatement.setInt(4,employee);
        preparedStatement.setInt(5,customer);
        preparedStatement.executeUpdate();
    }

    public ObservableList<Custom> getCustomEmployee(int idEmployee) throws SQLException {
        String sql = "SELECT id_custom,Type,date_custom,dateOfExecution " +
                "FROM Custom " +
                "WHERE Employee = " +  idEmployee + ";";
        statement = getConnection().createStatement();
        ResultSet result = statement.executeQuery(sql);
        ObservableList<Custom> list = FXCollections.observableArrayList();
        while (result.next()) {
            list.add(getCustomEmployeeFromResultSet(result));
        }
        return list;
    }

    public void editCustom(int id,Date dateofExc,Date AppAcc,Date AppDir,Double price) throws SQLException {
        String sql = "UPDATE Custom " +
                "SET dateOfExecution = ?,ApprovedAccoutant = ?,ApprovedDirector = ?,Price = ? " +
                "WHERE id_custom = ?;";
        PreparedStatement statement = getConnection().prepareStatement(sql);
        statement.setDate(1,dateofExc);
        statement.setDate(2,AppAcc);
        statement.setDate(3,AppDir);
        statement.setDouble(4,price);
        statement.setInt(5,id);
        statement.executeUpdate();
        statement.close();
    }

    public void editCustomDateExe(int id,Date dateofExc) throws SQLException {
        String sql = "UPDATE Custom " +
                "SET dateOfExecution = ? " +
                "WHERE id_custom = ?;";
        PreparedStatement statement = getConnection().prepareStatement(sql);
        statement.setDate(1,dateofExc);
        statement.setInt(2,id);
        statement.executeUpdate();
        statement.close();
    }

    public void editCustomAppAcc(int id,Date appAcc) throws SQLException {
        String sql = "UPDATE Custom " +
                "SET ApprovedAccoutant = ? " +
                "WHERE id_custom = ?;";
        PreparedStatement statement = getConnection().prepareStatement(sql);
        statement.setDate(1,appAcc);
        statement.setInt(2,id);
        statement.executeUpdate();
        statement.close();
    }

    public void editCustomAppDir(int id,Date appDir) throws SQLException {
        String sql = "UPDATE Custom " +
                "SET ApprovedDirector = ? " +
                "WHERE id_custom = ?;";
        PreparedStatement statement = getConnection().prepareStatement(sql);
        statement.setDate(1,appDir);
        statement.setInt(2,id);
        statement.executeUpdate();
        statement.close();
    }

    public void editCustomPrice(int id,Double price) throws SQLException {
        String sql = "UPDATE Custom " +
                "SET Price = ? " +
                "WHERE id_custom = ?;";
        PreparedStatement statement = getConnection().prepareStatement(sql);
        statement.setDouble(1,price);
        statement.setInt(2,id);
        statement.executeUpdate();
        statement.close();
    }

    public Custom getCustomForEdit(int id) throws SQLException {
        String sql = "SELECT dateOfExecution,ApprovedAccoutant,ApprovedDirector,Price " +
                "FROM Custom  " +
                "WHERE id_custom = " + id + ";";
        statement = getConnection().createStatement();
        ResultSet result = statement.executeQuery(sql);
        statement.close();
        return getCustomForEditFromResultSet(result);
    }

    public ObservableList<Custom> searchID(int id) throws SQLException {
        String sql = "SELECT id_custom,Custom.Type,CountOfQuestions,date_custom,dateOfExecution,ApprovedAccoutant,ApprovedDirector,Price, " +
                "  Employees.id AS idEmployee,Employees.Name,Employees.Position,Customer.id AS idCustomer," +
                " Customer.person_customer,Customer.person_representative,Customer.Address " +
                "FROM Custom " +
                "LEFT OUTER JOIN Employees ON Custom.Employee = Employees.id " +
                "LEFT OUTER JOIN Customer  ON Custom.Customer = Customer.id WHERE id_custom = " + id + ";";
        statement = getConnection().createStatement();
        ResultSet result = statement.executeQuery(sql);
        ObservableList<Custom> list = FXCollections.observableArrayList();
        while (result.next()){
            list.add(getCustomFromResultSet(result));
        }
        statement.close();
        return list;
    }

    public ObservableList<Custom> searchType(String type) throws SQLException {
        String sql = "SELECT id_custom,Custom.Type,CountOfQuestions,date_custom,dateOfExecution,ApprovedAccoutant,ApprovedDirector,Price, " +
                "  Employees.id AS idEmployee,Employees.Name,Employees.Position,Customer.id AS idCustomer," +
                " Customer.person_customer,Customer.person_representative,Customer.Address " +
                "FROM Custom " +
                "LEFT OUTER JOIN Employees ON Custom.Employee = Employees.id " +
                "LEFT OUTER JOIN Customer  ON Custom.Customer = Customer.id WHERE Type LIKE ?";
        PreparedStatement statement = getConnection().prepareStatement(sql);
        statement.setNString(1,"%" + type + "%");
        ResultSet result = statement.executeQuery();
        ObservableList<Custom> list = FXCollections.observableArrayList();
        while (result.next()){
            list.add(getCustomFromResultSet(result));
        }
        statement.close();
        return list;
    }

    public int completeCustom() throws SQLException {
        String sql = "SELECT COUNT(id_custom) AS count FROM Custom WHERE dateOfExecution;";
        statement = getConnection().createStatement();
        ResultSet result  = statement.executeQuery(sql);
        int i = 0;
        while (result.next()){
           i =  result.getInt("count");
        }
        return i;
    }

    public int appliedCustom() throws SQLException {
        String sql = "SELECT COUNT(id_custom) AS count FROM Custom WHERE ApprovedDirector;";
        statement = getConnection().createStatement();
        ResultSet result  = statement.executeQuery(sql);
        int i = 0;
        while (result.next()){
            i =  result.getInt("count");
        }
        return i;
    }

    public int allCustom() throws SQLException {
        String sql = "SELECT COUNT(id_custom) AS count FROM Custom;";
        statement = getConnection().createStatement();
        ResultSet result  = statement.executeQuery(sql);
        int i = 0;
        while (result.next()){
            i =  result.getInt("count");
        }
        return i;
    }

    public Number getCountCustom(String month) throws SQLException {
        int year = LocalDate.now().getYear();
        String sql = "SELECT COUNT(id_custom) AS count FROM Custom WHERE date_custom LIKE '" + year + "-" + month + "-_%_%'";
        statement = getConnection().createStatement();
        ResultSet result = statement.executeQuery(sql);
        int i = 0;
        while (result.next()){
            i = result.getInt("count");
        }
        return i;
    }

    public Number getFinishedCustom(String month) throws SQLException {
        int year = LocalDate.now().getYear();
        String sql = "SELECT COUNT(id_custom) AS count FROM Custom WHERE dateOfExecution LIKE '" + year + "-" + month + "-_%_%'";
        statement = getConnection().createStatement();
        ResultSet result = statement.executeQuery(sql);
        int i = 0;
        while (result.next()){
            i = result.getInt("count");
        }
        return i;
    }

}
